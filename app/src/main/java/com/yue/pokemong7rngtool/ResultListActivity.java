package com.yue.pokemong7rngtool;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ResultListActivity extends AppCompatActivity {

    private ListView mListView;
    private ListViewAdapter adapter;
    private String[] natureList = new String[25];

    int[] RNGResult = new int[4];
    int[] seed = new int[4];

    // EggSearchSettingInit
    int[] pre_parent = new int[6]; // male parent
    int[] post_parent = new int[6]; // female parent
    int pre_item, post_item;
    int pre_ability, post_ability;
    boolean pre_ditto_checked, post_ditto_checked;
    int sex_threshold; // gender ratio
    int selectedIndex; // random or male or female or genderless

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        mListView = findViewById(R.id.list_results);

        adapter = new ListViewAdapter(ResultListActivity.this);

        natureList[0] = getString(R.string.nature0);
        natureList[1] = getString(R.string.nature1);
        natureList[2] = getString(R.string.nature2);
        natureList[3] = getString(R.string.nature3);
        natureList[4] = getString(R.string.nature4);
        natureList[5] = getString(R.string.nature5);
        natureList[6] = getString(R.string.nature6);
        natureList[7] = getString(R.string.nature7);
        natureList[8] = getString(R.string.nature8);
        natureList[9] = getString(R.string.nature9);
        natureList[10] = getString(R.string.nature10);
        natureList[11] = getString(R.string.nature11);
        natureList[12] = getString(R.string.nature12);
        natureList[13] = getString(R.string.nature13);
        natureList[14] = getString(R.string.nature14);
        natureList[15] = getString(R.string.nature15);
        natureList[16] = getString(R.string.nature16);
        natureList[17] = getString(R.string.nature17);
        natureList[18] = getString(R.string.nature18);
        natureList[19] = getString(R.string.nature19);
        natureList[20] = getString(R.string.nature20);
        natureList[21] = getString(R.string.nature21);
        natureList[22] = getString(R.string.nature22);
        natureList[23] = getString(R.string.nature23);
        natureList[24] = getString(R.string.nature24);
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.clear();

        // EggSearchSetting
        SharedPreferences prefs = getSharedPreferences("packet", MODE_PRIVATE);
        seed[0] = prefs.getInt("seed0", 1);
        seed[1] = prefs.getInt("seed1", 1);
        seed[2] = prefs.getInt("seed2", 1);
        seed[3] = prefs.getInt("seed3", 1);

        pre_parent[0] = prefs.getInt("pre_parent0", 31); // male parent
        pre_parent[1] = prefs.getInt("pre_parent1", 31);
        pre_parent[2] = prefs.getInt("pre_parent2", 31);
        pre_parent[3] = prefs.getInt("pre_parent3", 31);
        pre_parent[4] = prefs.getInt("pre_parent4", 31);
        pre_parent[5] = prefs.getInt("pre_parent5", 31);
        post_parent[0] = prefs.getInt("post_parent0", 0); // female parent
        post_parent[1] = prefs.getInt("post_parent1", 0);
        post_parent[2] = prefs.getInt("post_parent2", 0);
        post_parent[3] = prefs.getInt("post_parent3", 0);
        post_parent[4] = prefs.getInt("post_parent4", 0);
        post_parent[5] = prefs.getInt("post_parent5", 0);
        sex_threshold = 126; // male:female=1:1
        selectedIndex = 0; // male:female=1:1

        TinyMT tiny = new TinyMT(seed, new TinyMTParameter(0x8f7011ee, 0xfc78ff1f, 0x3793fdff));

        pre_item = prefs.getInt("pre_item", 0);
        pre_ability = prefs.getInt("pre_ability", 0);
        pre_ditto_checked = prefs.getBoolean("pre_ditto", false);
        post_item = prefs.getInt("post_item", 0);
        post_ability = prefs.getInt("post_ability", 0);
        post_ditto_checked = prefs.getBoolean("post_ditto", false);
        // give the values
        EggRNGSearch rng = new EggRNGSearch();
        rng.GenderRatio = sex_threshold;
        rng.GenderRandom = selectedIndex < 4;
        rng.GenderMale = selectedIndex == 4;
        rng.GenderFemale = selectedIndex == 5;
        rng.International = prefs.getBoolean("masuda_method", false);
        rng.ShinyCharm = prefs.getBoolean("shiny_charm", false);
        rng.Heterogeneous = prefs.getBoolean("diff_species", false); // not same species
        rng.Both_Everstone = pre_item == 1 && post_item == 1;
        rng.Everstone = pre_item == 1 || post_item == 1;
        rng.DestinyKnot = pre_item == 2 || post_item == 2;
        rng.PowerItems = pre_item > 2 || post_item > 2;
        rng.Both_PowerItems = pre_item > 2 && post_item > 2;
        rng.MalePowerStat = pre_item - 3;
        rng.FemalePowerStat = post_item - 3;
        rng.ParentAbility = !post_ditto_checked ? post_ability : pre_ability;
        rng.ConciderTSV = true;
//        rng.SearchOtherTSV = ??;

        rng.TSV = 386;
        rng.pre_parent = pre_parent;
        rng.post_parent = post_parent;

        rng.Initialize();

        for (int i = 0; i < 100; i++, tiny.nextState()) {

            // status update
            for (int j = 0; j < 4; j++) {
                RNGResult[j] = tiny.status[j];
            }
            EggRNGSearch.EggRNGResult result = rng.Generate(RNGResult);

            // output
            int frameAdv = result.FramesUsed;
//                    String seed = Integer.toHexString(tiny.status[3]) + ", " +
//                            Integer.toHexString(tiny.status[2]) + ", " +
//                            Integer.toHexString(tiny.status[1]) + ", " +
//                            Integer.toHexString(tiny.status[0]);
            int hp = result.IVs[0];
            int atk = result.IVs[1];
            int def = result.IVs[2];
            int spa = result.IVs[3];
            int spd = result.IVs[4];
            int spe = result.IVs[5];

            String gender;
            int genderCode = result.Gender;
            if (genderCode >= 126) gender = getString(R.string.male);
            else gender = getString(R.string.female);

            String ability;
            int abilityCode = result.Ability;
            if (abilityCode < 2) ability = String.valueOf(abilityCode + 1);
            else ability = getString(R.string.hidden_ability);

            int natureCode = result.Nature;

            adapter.add(new ListViewAdapter.SampleItem(i, frameAdv, hp, atk, def, spa, spd, spe, gender, ability, natureList[natureCode]));
        }
        mListView.setAdapter(adapter);
    }
}
