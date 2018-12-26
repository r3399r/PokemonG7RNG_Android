package com.yue.pokemong7rngtool;

public class TinyMT {

    int[] status;
    TinyMTParameter param;

    private final int TINYMT32_MASK = 0x7FFFFFFF;
    private final int TINYMT32_SH0 = 1;
    private final int TINYMT32_SH1 = 10;
    private final int TINYMT32_SH8 = 8;
    private final int MIN_LOOP = 8;
    private final int PRE_LOOP = 8;

    public TinyMT(int[] status, TinyMTParameter param) {
        this.status = new int[4];
        for (int i = 0; i < 4; i++) {
            this.status[i] = status[i];
        }
        this.param = param;
    }

    public void nextState() {
        int y = status[3];
        int x = (status[0] & TINYMT32_MASK) ^ status[1] ^ status[2];
        x ^= (x << TINYMT32_SH0);
        y ^= (y >>> TINYMT32_SH0) ^ x;
        status[0] = status[1];
        status[1] = status[2];
        status[2] = x ^ (y << TINYMT32_SH1);
        status[3] = y;

        if ((y & 1) == 1) {
            status[1] ^= param.mat1;
            status[2] ^= param.mat2;
        }
    }

    public int temper() {
        int t0 = status[3];
        int t1 = status[0] + (status[2] >>> TINYMT32_SH8);

        t0 ^= t1;
        if ((t1 & 1) == 1) {
            t0 ^= param.tmat;
        }
        return t0;
    }

    public void tinymt32_init(int seed) {
        status[0] = seed;
        status[1] = param.mat1;
        status[2] = param.mat2;
        status[3] = param.tmat;
        for (int i = 1; i < MIN_LOOP; i++) {
            status[i & 3] ^= i + 1812433253 * (status[(i - 1) & 3] ^ (status[(i - 1) & 3] >>> 30));
        }
        period_certification();
        for (int i = 0; i < PRE_LOOP; i++) {
            nextState();
        }
    }

    public void period_certification() {
        if ((status[0] & TINYMT32_MASK) == 0 && status[1] == 0 && status[2] == 0 && status[3] == 0) {
            status[0] = 'T';
            status[1] = 'I';
            status[2] = 'N';
            status[3] = 'Y';
        }
    }
}
