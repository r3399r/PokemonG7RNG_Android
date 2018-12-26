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
    private EditText txtMagikarpSeries;
    String series = "0100011101000000011101101011101000001001100101101000110110101101111101010110100100101111010001001000110011101111010011101001010";
    int[] result = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_seed);

        btnStart  = findViewById(R.id.btn_start_find_seed);
        txtResult = findViewById(R.id.find_seed_result);
        txtMagikarpSeries = findViewById(R.id.magikarp_series);

        txtMagikarpSeries.setText(series);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result = MagikarpMethod.calc(series);

                String statusString =
                        Integer.toHexString(result[3])+", "+
                                Integer.toHexString(result[2])+", "+
                                Integer.toHexString(result[1])+", "+
                                Integer.toHexString(result[0]);
                txtResult.setText(statusString);
            }
        });
    }
}
