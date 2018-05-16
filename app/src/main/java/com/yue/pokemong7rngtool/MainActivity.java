package com.yue.pokemong7rngtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView btnEggRNG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEggRNG = (TextView) findViewById(R.id.btn_eggRNG);

//        btnEggRNG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(MainActivity.this, ViewHistoricalDataActivity.class);
//                startActivity(it);
//            }
//        });

    }
}
