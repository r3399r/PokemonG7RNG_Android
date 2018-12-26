package com.yue.pokemong7rngtool;

public class EggRNGSearch {

    public TinyMT tiny;

    // Search settings
    public int GenderRatio;
    public boolean GenderRandom, GenderMale, GenderFemale;
    public boolean DestinyKnot;
    public boolean International;
    public boolean ShinyCharm;
    public boolean SearchOtherTSV;
    public boolean ConciderTSV;

    public boolean Heterogeneous;
    public int ParentAbility;
    public boolean Both_Everstone;
    public boolean Everstone;
    public boolean Both_PowerItems;
    public boolean PowerItems;
    public int MalePowerStat, FemalePowerStat;

    public int TSV;
    private boolean Homogeneous;
    private int InheritIVs;
    public int PIDRerolls;

    private int FramesUsed;
    public int[] pre_parent;
    public int[] post_parent;

    public void Initialize() {
        InheritIVs = DestinyKnot ? 5 : 3;
        if (International)
            PIDRerolls += 6;
        if (ShinyCharm)
            PIDRerolls += 2;

        Homogeneous = !Heterogeneous;
    }

    // inner class
    public class EggRNGResult {
        public int[] Seed = new int[4];
        public int[] BaseIV = new int[6];
        public int[] InheritStats;
        public int[] InheritParents;
        public int Gender, Ability, Nature, Ball, BE_InheritParents;
        public int PID, EC;
        public boolean Shiny;
        public int FramesUsed;
        public int PSV;
        public int row_r;
        // public string Seed128 => string.Join(",", Seed.Select(v =>
        // v.ToString("X8")).Reverse());
        public int[] IVs = new int[6];

        public void InheritIVs(int[] pre_parent, int[] post_parent) {

            // IVs = (int[])BaseIV.Clone();
            for (int i = 0; i < 6; i++) {
                IVs[i] = BaseIV[i];
            }

            for (int j = 0; j < InheritStats.length; j++) {
                int stat = (int) InheritStats[j];
                int parent = (int) InheritParents[j];
                IVs[stat] = parent == 0 ? pre_parent[stat] : post_parent[stat];
            }
        }
    }

    public EggRNGResult Generate(int[] seed) {

        boolean flagOfIvInheritance;
        tiny = new TinyMT(seed, new TinyMTParameter(0x8f7011ee, 0xfc78ff1f, 0x3793fdff));
        EggRNGResult egg = new EggRNGResult();
        for (int i = 0; i < 4; i++) {
            egg.Seed[i] = seed[i]; // CopyTo
        }
        FramesUsed = 0; // Reset Frame Counter

        // Rand number
        egg.row_r = tiny.temper();

        // Gender
        if (GenderRandom) {
            // egg.Gender = getRand() % 252 >= GenderRatio ? 0 : 1;
            egg.Gender = Integer.remainderUnsigned(getRand(), 252);
        } else if (GenderMale)
            egg.Gender = 0;
        else if (GenderFemale)
            egg.Gender = 1;
        else
            egg.Gender = 2;

        // Nature
        // egg.Nature = (int) (getRand() % 25);
        egg.Nature = Integer.remainderUnsigned(getRand(), 25);

        // parents both own Everstone
        if (Both_Everstone) {
            egg.BE_InheritParents = (int) (getRand() & 1);
            egg.Nature = egg.BE_InheritParents;
        }

        // Ability
        // egg.Ability = getRandomAbility(ParentAbility, getRand() % 100);
        egg.Ability = getRandomAbility(ParentAbility, Integer.remainderUnsigned(getRand(), 100));

        egg.InheritStats = new int[InheritIVs];
        egg.InheritParents = new int[InheritIVs];

        // parents both own powerItem
        // Choose which parent if necessary
        if (Both_PowerItems) {
            egg.InheritParents[0] = (getRand() & 1) == 0 ? 0 : 1;
            egg.InheritStats[0] = egg.InheritParents[0] == 0 ? MalePowerStat : FemalePowerStat;
        } else if (PowerItems) {
            egg.InheritParents[0] = MalePowerStat > -1 ? 0 : 1;
            egg.InheritStats[0] = egg.InheritParents[0] == 0 ? MalePowerStat : FemalePowerStat;
        }

        // IV Inheritance
        for (int i = 0; i < InheritIVs; i++) {
            if ((i == 0) && PowerItems)
                continue;

            // repeat:
            for (;;) {
                flagOfIvInheritance = false;
                // egg.InheritStats[i] = getRand() % 6;
                egg.InheritStats[i] = Integer.remainderUnsigned(getRand(), 6);

                // Scan for duplicate IV
                for (int k = 0; k < i; k++)
                    if (egg.InheritStats[k] == egg.InheritStats[i])
                        flagOfIvInheritance = true;

                if (flagOfIvInheritance == true)
                    continue;
                else
                    break;
                // goto repeat;
            }
            egg.InheritParents[i] = getRand() & 1;
        }

        // Base IVs
        for (int j = 0; j < 6; j++)
            egg.BaseIV[j] = (int) (getRand() & 0x1F);
        egg.InheritIVs(pre_parent, post_parent);

        // Encryption Constant
        egg.EC = getRand();

        // PID Rerolls
        for (int i = 0; i < PIDRerolls; i++) {
            egg.PID = getRand();
            egg.PSV = ((egg.PID >>> 16) ^ (egg.PID & 0xFFFF)) >>> 4;

            if (!SearchOtherTSV && ConciderTSV && egg.PSV == TSV) {
                egg.Shiny = true;
                break;
            }
        }

        // Ball Inheritance
        if (Homogeneous) {// Same Species (no Ditto)
            // egg.Ball = getRand() % 100 >= 50 ? 0 : 1;
            egg.Ball = Integer.remainderUnsigned(getRand(), 100) >= 50 ? 0 : 1;
        }

        // Something unknown
        getRand();
        getRand();

        egg.FramesUsed = FramesUsed;
        return egg;
    }

    private int getRand() {
        tiny.nextState();
        int r = tiny.temper();
        ++FramesUsed;
        return r;
    }

    private static int getRandomAbility(int ability, int value) {
        switch (ability) {
            case 0: // Ability 0
                return value < 80 ? 0 : 1;
            case 1:
                return value < 20 ? 0 : 1;
            case 2:
                if (value < 20)
                    return 0;
                if (value < 40)
                    return 1;
                return 2;
        }
        return 0;
    }
}
