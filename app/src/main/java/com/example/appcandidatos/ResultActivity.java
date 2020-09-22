package com.example.appcandidatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    TextView tvvotos1, tvvotos2, tvvotos3, tvvotost;
    int votos1, votos2, votos3, suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Resultado votaciones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvvotos1 = (TextView) findViewById(R.id.tvvotos1);
        tvvotos2 = (TextView) findViewById(R.id.tvvotos2);
        tvvotos3 = (TextView) findViewById(R.id.tvvotos3);
        tvvotost = (TextView) findViewById(R.id.tvvotost);

        votos1 = getIntent().getIntExtra("c1", 0);
        votos2 = getIntent().getIntExtra("c2", 0);
        votos3 = getIntent().getIntExtra("c3", 0);

        tvvotos1.setText(String.valueOf(votos1));
        tvvotos2.setText(String.valueOf(votos2));
        tvvotos3.setText(String.valueOf(votos3));

        result();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void result() {
        suma = Integer.parseInt(tvvotos1.getText().toString()) + Integer.parseInt(tvvotos2.getText().toString()) + Integer.parseInt(tvvotos3.getText().toString());
        tvvotost.setText(String.valueOf(suma));
    }
}