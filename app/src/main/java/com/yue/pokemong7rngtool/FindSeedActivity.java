package com.yue.pokemong7rngtool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FindSeedActivity extends AppCompatActivity {

    private TextView btnStart;
    private TextView txtResult;
    private EditText txtMagikarpSeries1;
    private EditText txtMagikarpSeries2;
    private EditText txtMagikarpSeries3;
    private EditText txtMagikarpSeries4;
    private EditText txtMagikarpSeries5;
    private EditText txtMagikarpSeries6;
    private EditText txtMagikarpSeries7;
    private EditText txtMagikarpSeries8;
    private EditText txtMagikarpSeries9;
    private EditText txtMagikarpSeries10;
    private EditText txtMagikarpSeries11;
    private EditText txtMagikarpSeries12;
    private EditText txtMagikarpSeries13;

    String series1 = "0100011101";
    String series2 = "0000000111";
    String series3 = "0110101110";
    String series4 = "1000001001";
    String series5 = "1001011010";
    String series6 = "0011011010";
    String series7 = "1101111101";
    String series8 = "0101101001";
    String series9 = "0010111101";
    String series10 = "0001001000";
    String series11 = "1100111011";
    String series12 = "1101001110";
    String series13 = "1001010";
    String series;
    int[] result = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_seed);

        btnStart = findViewById(R.id.btn_start_find_seed);
        txtResult = findViewById(R.id.find_seed_result);
        txtMagikarpSeries1 = findViewById(R.id.magikarp_series_1to10);
        txtMagikarpSeries2 = findViewById(R.id.magikarp_series_11to20);
        txtMagikarpSeries3 = findViewById(R.id.magikarp_series_21to30);
        txtMagikarpSeries4 = findViewById(R.id.magikarp_series_31to40);
        txtMagikarpSeries5 = findViewById(R.id.magikarp_series_41to50);
        txtMagikarpSeries6 = findViewById(R.id.magikarp_series_51to60);
        txtMagikarpSeries7 = findViewById(R.id.magikarp_series_61to70);
        txtMagikarpSeries8 = findViewById(R.id.magikarp_series_71to80);
        txtMagikarpSeries9 = findViewById(R.id.magikarp_series_81to90);
        txtMagikarpSeries10 = findViewById(R.id.magikarp_series_91to100);
        txtMagikarpSeries11 = findViewById(R.id.magikarp_series_101to110);
        txtMagikarpSeries12 = findViewById(R.id.magikarp_series_111to120);
        txtMagikarpSeries13 = findViewById(R.id.magikarp_series_121to127);

        txtMagikarpSeries1.setText(series1);
        txtMagikarpSeries2.setText(series2);
        txtMagikarpSeries3.setText(series3);
        txtMagikarpSeries4.setText(series4);
        txtMagikarpSeries5.setText(series5);
        txtMagikarpSeries6.setText(series6);
        txtMagikarpSeries7.setText(series7);
        txtMagikarpSeries8.setText(series8);
        txtMagikarpSeries9.setText(series9);
        txtMagikarpSeries10.setText(series10);
        txtMagikarpSeries11.setText(series11);
        txtMagikarpSeries12.setText(series12);
        txtMagikarpSeries13.setText(series13);

        series = series1 + series2 + series3 + series4 + series5 + series6 + series7 + series8 + series9 + series10 + series11 + series12 + series13;

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result = MagikarpMethod.calc(series);

                String statusString = Integer.toHexString(result[3]) + ", " +
                        Integer.toHexString(result[2]) + ", " +
                        Integer.toHexString(result[1]) + ", " +
                        Integer.toHexString(result[0]);
                txtResult.setText(statusString);
            }
        });
    }
}
