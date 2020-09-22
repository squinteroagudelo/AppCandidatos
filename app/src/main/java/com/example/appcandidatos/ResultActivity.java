package com.example.appcandidatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    TextView tvvotos1, tvvotos2, tvvotos3, tvvotost, tvganador;
    int votos1, votos2, votos3, suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Resultado votaciones");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvvotos1 = findViewById(R.id.tvvotos1);
        tvvotos2 = findViewById(R.id.tvvotos2);
        tvvotos3 = findViewById(R.id.tvvotos3);
        tvvotost = findViewById(R.id.tvvotost);
        tvganador = findViewById(R.id.tvganador);

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
        int c1 = Integer.parseInt(tvvotos1.getText().toString());
        int c2 = Integer.parseInt(tvvotos2.getText().toString());
        int c3 = Integer.parseInt(tvvotos3.getText().toString());
        suma = c1 + c2 + c3;
        tvvotost.setText(String.valueOf(suma));

        if ((c1 == c2 || c1 == c3) && c2 == c3){
            tvganador.setText("Triple empate");
        }else if (c1 > c2){
            if (c1 > c3){
                tvganador.setText("Candidato 1");
            }else if(c1 < c3){
                tvganador.setText("Candidato 3");
            }else{
                tvganador.setText("Empate\nCandidato 1 / Candidato 3");
            }
        }else if (c1 < c2){
            if (c2 > c3){
                tvganador.setText("Candidato 2");
            }else if (c2 < c3){
                tvganador.setText("Candidato 3");
            }else{
                tvganador.setText("Empate\nCandidato 2 / Candidato 3");
            }
        }else{
            tvganador.setText("Empate\nCandidato 1 / Candidato 2");
        }
    }
}