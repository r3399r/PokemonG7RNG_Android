package com.yue.pokemong7rngtool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EggRNGActivity extends AppCompatActivity {

    private EditText txtStatus0;
    private EditText txtStatus1;
    private EditText txtStatus2;
    private EditText txtStatus3;
    private TextView btnStart;
    private ListView mListView;
    private ListViewAdapter adapter;

    int[] RNGResult = new int[4];
    int[] status = {0xbd6225ee, 0x3a2edfb4, 0xb620b514, 0x73dbdf1f};

    // EggSearchSettingInit
    int[] pre_parent = new int[6]; // male parent
    int[] post_parent = new int[6]; // female parent
    int sex_threshold; // gender ratio
    int selectedIndex; // random or male or female or genderless

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_rng);

        txtStatus0 = findViewById(R.id.status_0);
        txtStatus1 = findViewById(R.id.status_1);
        txtStatus2 = findViewById(R.id.status_2);
        txtStatus3 = findViewById(R.id.status_3);
        btnStart = findViewById(R.id.btn_start_calc);
        mListView = findViewById(R.id.list_results);

        txtStatus0.setText(Integer.toHexString(status[0]));
        txtStatus1.setText(Integer.toHexString(status[1]));
        txtStatus2.setText(Integer.toHexString(status[2]));
        txtStatus3.setText(Integer.toHexString(status[3]));

        adapter = new ListViewAdapter(EggRNGActivity.this);
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.clear();


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                status[0] = (int) Long.parseLong(txtStatus0.getText().toString(), 16);
                status[1] = (int) Long.parseLong(txtStatus1.getText().toString(), 16);
                status[2] = (int) Long.parseLong(txtStatus2.getText().toString(), 16);
                status[3] = (int) Long.parseLong(txtStatus3.getText().toString(), 16);
                TinyMT tiny = new TinyMT(status, new TinyMTParameter(0x8f7011ee, 0xfc78ff1f, 0x3793fdff));

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

                for (int k = 0; k < 5; k++) {
                    tiny.nextState();

                    // status update
                    for (int j = 0; j < 4; j++) {
                        RNGResult[j] = tiny.status[j];
                    }
                    EggRNGSearch.EggRNGResult result = rng.Generate(RNGResult);

                    // output
                    String statusString = Integer.toHexString(tiny.status[3]) + ", " +
                            Integer.toHexString(tiny.status[2]) + ", " +
                            Integer.toHexString(tiny.status[1]) + ", " +
                            Integer.toHexString(tiny.status[0]);
                    adapter.add(new ListViewAdapter.SampleItem(statusString));

                }
                mListView.setAdapter(adapter);
            }
        });
    }
}
