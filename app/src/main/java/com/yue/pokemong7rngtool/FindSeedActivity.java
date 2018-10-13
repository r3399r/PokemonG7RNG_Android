package com.yue.pokemong7rngtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FindSeedActivity extends AppCompatActivity {

    private TextView btnStart;
    private TextView txtResult1;
    private TextView txtResult2;
    private TextView txtResult3;
    private TextView txtResult4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_seed);

        btnStart   = findViewById(R.id.btn_start_find_seed);
        txtResult1 = findViewById(R.id.text_result1);
        txtResult2 = findViewById(R.id.text_result2);
        txtResult3 = findViewById(R.id.text_result3);
        txtResult4 = findViewById(R.id.text_result4);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }
}
