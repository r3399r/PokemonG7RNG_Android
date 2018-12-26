package com.yue.pokemong7rngtool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EggRNGActivity extends AppCompatActivity {

    private EditText txtStatus0;
    private EditText txtStatus1;
    private EditText txtStatus2;
    private EditText txtStatus3;
    private TextView txtRNGResult;

    int[] RNGResult = new int[4];
    int[] status = { 0xbd6225ee, 0x3a2edfb4, 0xb620b514, 0x73dbdf1f };
    TinyMT tiny = new TinyMT(status, new TinyMTParameter(0x8f7011ee, 0xfc78ff1f, 0x3793fdff));

    // EggSearchSettingInit
    int[] pre_parent = new int[6]; // male parent
    int[] post_parent = new int[6]; // female parent
    int sex_threshold; // gender ratio
    int selectedIndex; // random or male or female or genderless

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_rng);

        txtStatus0 = (EditText) findViewById(R.id.status_0);
        txtStatus1 = (EditText) findViewById(R.id.status_1);
        txtStatus2 = (EditText) findViewById(R.id.status_2);
        txtStatus3 = (EditText) findViewById(R.id.status_3);
        txtRNGResult = (TextView) findViewById(R.id.RNG_result);

        txtStatus0.setText(Integer.toHexString(status[0]));
        txtStatus1.setText(Integer.toHexString(status[1]));
        txtStatus2.setText(Integer.toHexString(status[2]));
        txtStatus3.setText(Integer.toHexString(status[3]));

        // EggSearchSetting
        pre_parent[0] = 0; // male parent
        pre_parent[1] = 0;
        pre_parent[2] = 0;
        pre_parent[3] = 0;
        pre_parent[4] = 0;
        pre_parent[5] = 0;
        post_parent[0] = 31; // female parent
        post_parent[1] = 31;
        post_parent[2] = 31;
        post_parent[3] = 31;
        post_parent[4] = 31;
        post_parent[5] = 31;
        sex_threshold = 126; // male:female=1:1
        selectedIndex = 0; // male:female=1:1

        // give the values
        EggRNGSearch rng = new EggRNGSearch();
        rng.GenderRatio = sex_threshold;
        rng.GenderRandom = selectedIndex < 4;
        rng.GenderMale = selectedIndex == 4;
        rng.GenderFemale = selectedIndex == 5;
        rng.International = false;
        rng.ShinyCharm = true;
        rng.Heterogeneous = true; // not same species
        rng.Both_Everstone = false;
        rng.DestinyKnot = false;
        rng.PowerItems = false;
        rng.Both_PowerItems = false;
        rng.MalePowerStat = -3;
        rng.FemalePowerStat = -1;
        rng.ParentAbility = 2;
        rng.ConciderTSV = true;

        rng.TSV = 386;
        rng.pre_parent = pre_parent;
        rng.post_parent = post_parent;

        rng.Initialize();

        tiny.nextState();
        // status update
        for (int j = 0; j < 4; j++) {
            RNGResult[j] = tiny.status[j];
        }
        EggRNGSearch.EggRNGResult result = rng.Generate(RNGResult);

        // output
        String statusString =
                Integer.toHexString(tiny.status[3])+", "+
                Integer.toHexString(tiny.status[2])+", "+
                Integer.toHexString(tiny.status[1])+", "+
                Integer.toHexString(tiny.status[0]);
        txtRNGResult.setText(statusString);

    }
}
