package com.yue.pokemong7rngtool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EggRNGActivity extends AppCompatActivity {

    private TextView txtSeed0;
    private TextView txtSeed1;
    private TextView txtSeed2;
    private TextView txtSeed3;
    private EditText txtStatsPreHp;
    private EditText txtStatsPreAtk;
    private EditText txtStatsPreDef;
    private EditText txtStatsPreSpA;
    private EditText txtStatsPreSpD;
    private EditText txtStatsPreSpe;
    private EditText txtStatsPostHp;
    private EditText txtStatsPostAtk;
    private EditText txtStatsPostDef;
    private EditText txtStatsPostSpA;
    private EditText txtStatsPostSpD;
    private EditText txtStatsPostSpe;
    private CheckBox boxMasuda;
    private CheckBox boxShinyCharm;
    private CheckBox boxDiffSpecies;
    private CheckBox boxPreDitto;
    private Spinner spinnerPreItem;
    private Spinner spinnerPreAbility;
    private CheckBox boxPostDitto;
    private Spinner spinnerPostItem;
    private Spinner spinnerPostAbility;
    private TextView btnStart;

    private int[] seed = {0xbd6225ee, 0x3a2edfb4, 0xb620b514, 0x73dbdf1f};
    private int seed0;
    private int seed1;
    private int seed2;
    private int seed3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_rng);

        txtSeed0 = findViewById(R.id.status_0);
        txtSeed1 = findViewById(R.id.status_1);
        txtSeed2 = findViewById(R.id.status_2);
        txtSeed3 = findViewById(R.id.status_3);
        btnStart = findViewById(R.id.btn_start_calc);

        txtStatsPreHp = findViewById(R.id.pre_parent_hp);
        txtStatsPreAtk = findViewById(R.id.pre_parent_atk);
        txtStatsPreDef = findViewById(R.id.pre_parent_def);
        txtStatsPreSpA = findViewById(R.id.pre_parent_spa);
        txtStatsPreSpD = findViewById(R.id.pre_parent_spd);
        txtStatsPreSpe = findViewById(R.id.pre_parent_spe);
        txtStatsPostHp = findViewById(R.id.post_parent_hp);
        txtStatsPostAtk = findViewById(R.id.post_parent_atk);
        txtStatsPostDef = findViewById(R.id.post_parent_def);
        txtStatsPostSpA = findViewById(R.id.post_parent_spa);
        txtStatsPostSpD = findViewById(R.id.post_parent_spd);
        txtStatsPostSpe = findViewById(R.id.post_parent_spe);

        spinnerPreItem = findViewById(R.id.spinner_pre_item);
        spinnerPreAbility = findViewById(R.id.spinner_pre_ability);
        boxPreDitto = findViewById(R.id.checkBox_pre_Ditto);
        spinnerPostItem = findViewById(R.id.spinner_post_item);
        spinnerPostAbility = findViewById(R.id.spinner_post_ability);
        boxPostDitto = findViewById(R.id.checkBox_post_Ditto);

        boxMasuda = findViewById(R.id.checkBox_MasudaMethod);
        boxShinyCharm = findViewById(R.id.checkBox_ShinyCharm);
        boxDiffSpecies = findViewById(R.id.checkBox_DiffSpecies);

        txtStatsPreHp.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPreAtk.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPreDef.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPreSpA.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPreSpD.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPreSpe.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPostHp.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPostAtk.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPostDef.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPostSpA.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPostSpD.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});
        txtStatsPostSpe.setFilters(new InputFilter[]{new InputFilterMinMax("0", "31")});

        txtSeed3.setText(Integer.toHexString(seed[3]));
        txtSeed2.setText(Integer.toHexString(seed[2]));
        txtSeed1.setText(Integer.toHexString(seed[1]));
        txtSeed0.setText(Integer.toHexString(seed[0]));
        txtStatsPreHp.setText("31");
        txtStatsPreAtk.setText("31");
        txtStatsPreDef.setText("31");
        txtStatsPreSpA.setText("31");
        txtStatsPreSpD.setText("31");
        txtStatsPreSpe.setText("31");
        txtStatsPostHp.setText("0");
        txtStatsPostAtk.setText("0");
        txtStatsPostDef.setText("0");
        txtStatsPostSpA.setText("0");
        txtStatsPostSpD.setText("0");
        txtStatsPostSpe.setText("0");

        ArrayAdapter<CharSequence> itemList = ArrayAdapter.createFromResource(EggRNGActivity.this,
                R.array.spinner_item, android.R.layout.simple_spinner_dropdown_item);
        spinnerPreItem.setAdapter(itemList);
        spinnerPostItem.setAdapter(itemList);

        ArrayAdapter<CharSequence> abilityList = ArrayAdapter.createFromResource(EggRNGActivity.this,
                R.array.spinner_ability, android.R.layout.simple_spinner_dropdown_item);
        spinnerPreAbility.setAdapter(abilityList);
        spinnerPostAbility.setAdapter(abilityList);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                seed0 = (int) Long.parseLong(txtSeed0.getText().toString(), 16);
                seed1 = (int) Long.parseLong(txtSeed1.getText().toString(), 16);
                seed2 = (int) Long.parseLong(txtSeed2.getText().toString(), 16);
                seed3 = (int) Long.parseLong(txtSeed3.getText().toString(), 16);

                SharedPreferences.Editor packet = getSharedPreferences("packet", MODE_PRIVATE).edit();
                packet.putInt("seed0", seed0);
                packet.putInt("seed1", seed1);
                packet.putInt("seed2", seed2);
                packet.putInt("seed3", seed3);

                packet.putInt("pre_parent0", Integer.valueOf(txtStatsPreHp.getText().toString()));
                packet.putInt("pre_parent1", Integer.valueOf(txtStatsPreAtk.getText().toString()));
                packet.putInt("pre_parent2", Integer.valueOf(txtStatsPreDef.getText().toString()));
                packet.putInt("pre_parent3", Integer.valueOf(txtStatsPreSpA.getText().toString()));
                packet.putInt("pre_parent4", Integer.valueOf(txtStatsPreSpD.getText().toString()));
                packet.putInt("pre_parent5", Integer.valueOf(txtStatsPreSpe.getText().toString()));
                packet.putInt("post_parent0", Integer.valueOf(txtStatsPostHp.getText().toString()));
                packet.putInt("post_parent1", Integer.valueOf(txtStatsPostAtk.getText().toString()));
                packet.putInt("post_parent2", Integer.valueOf(txtStatsPostDef.getText().toString()));
                packet.putInt("post_parent3", Integer.valueOf(txtStatsPostSpA.getText().toString()));
                packet.putInt("post_parent4", Integer.valueOf(txtStatsPostSpD.getText().toString()));
                packet.putInt("post_parent5", Integer.valueOf(txtStatsPostSpe.getText().toString()));

                packet.putInt("pre_item", spinnerPreItem.getSelectedItemPosition());
                packet.putInt("pre_ability", spinnerPreAbility.getSelectedItemPosition());
                packet.putBoolean("pre_ditto", boxPreDitto.isChecked());
                packet.putInt("post_item", spinnerPostItem.getSelectedItemPosition());
                packet.putInt("post_ability", spinnerPostAbility.getSelectedItemPosition());
                packet.putBoolean("post_ditto", boxPostDitto.isChecked());

                packet.putBoolean("masuda_method", boxMasuda.isChecked());
                packet.putBoolean("shiny_charm", boxShinyCharm.isChecked());
                packet.putBoolean("diff_species", boxDiffSpecies.isChecked());
                packet.apply();

                Intent it = new Intent(EggRNGActivity.this, ResultListActivity.class);
                startActivity(it);
                overridePendingTransition(0, 0);
            }
        });

        boxPreDitto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    boxPostDitto.setChecked(false);
                    boxDiffSpecies.setChecked(true);
                    boxDiffSpecies.setEnabled(false); // disable checkbox
                } else {
                    boxDiffSpecies.setEnabled(true); // enable checkbox
                }
            }
        });

        boxPostDitto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    boxPreDitto.setChecked(false);
                    boxDiffSpecies.setChecked(true);
                    boxDiffSpecies.setEnabled(false); // disable checkbox
                } else {
                    boxDiffSpecies.setEnabled(true); // enable checkbox
                }
            }
        });
    }
}
