package com.example.appcandidatos;

/*Implementar una aplicación en Android que permita determinar cual fue el candidato ganador de tres
aspirantes. El numero de electores lo debe ingresar el usuario por teclado, el sistema deberá validar
que un elector si pueda votar, es decir que sea mayor de edad.*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText etcantidad, etnombre, etedad;
    RadioButton rdbcandidato1, rdbcandidato2, rdbcandidato3;
    int cant, candidato1 = 0, candidato2 = 0, candidato3 = 0, turno = 0;
    String nombre;
    ArrayList<String> electores = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Registro de votos");

        etcantidad = findViewById(R.id.etcantidad);
        etnombre = findViewById(R.id.etnombre);
        etedad = findViewById(R.id.etedad);
        rdbcandidato1 = findViewById(R.id.rdbcandidato1);
        rdbcandidato2 = findViewById(R.id.rdbcandidato2);
        rdbcandidato3 = findViewById(R.id.rdbcandidato3);

        rdbcandidato1.setEnabled(false);
        rdbcandidato2.setEnabled(false);
        rdbcandidato3.setEnabled(false);
    }

    public void votar(View v) {
        if (!etcantidad.getText().toString().isEmpty()) {
            cant = Integer.parseInt(etcantidad.getText().toString());
            if (cant > 0) {
                etcantidad.setEnabled(false);
                if (!etnombre.getText().toString().isEmpty()) {
                    if (!etedad.getText().toString().isEmpty()) {
                        int edad = Integer.parseInt(etedad.getText().toString());
                        nombre = etnombre.getText().toString();
                        if (edad >= 18) {
                            toggleEnable();
                            inflateToast(R.layout.toast_success, R.id.toast_container_success, "Bienvenido, " + nombre);
                        } else {
                            inflateToast(R.layout.toast_failed, R.id.toast_container_failed, "No autorizado");
                            limpiar();
                            etnombre.requestFocus();
                        }
                    } else {
                        inflateToast(R.layout.toast_failed, R.id.toast_container_failed, "La edad es requerida");
                        etedad.requestFocus();
                    }
                } else {
                    inflateToast(R.layout.toast_failed, R.id.toast_container_failed, "El nombre es requerido");
                    etnombre.requestFocus();
                }
            } else {
                inflateToast(R.layout.toast_failed, R.id.toast_container_failed, "Cantidad no válida");
                etcantidad.setText("");
                etcantidad.requestFocus();
            }
        } else {
            inflateToast(R.layout.toast_failed, R.id.toast_container_failed, "Ingrese la cantidad de electores");
            limpiar();
            etcantidad.requestFocus();
        }
    }

    public void confirmar(View v) {
        if (rdbcandidato1.isChecked()) {
            electores.add(nombre);
            candidato1++;
            turno++;
            toggleEnable();
            stored();
            limpiar();
        } else if (rdbcandidato2.isChecked()) {
            electores.add(nombre);
            candidato2++;
            turno++;
            toggleEnable();
            stored();
            limpiar();
        } else if (rdbcandidato3.isChecked()) {
            electores.add(nombre);
            candidato3++;
            turno++;
            toggleEnable();
            stored();
            limpiar();
        } else {
            inflateToast(R.layout.toast_failed, R.id.toast_container_failed, "Debe elegir un candidato");
        }

        if (turno == cant) {
            Intent result = new Intent(this, ResultActivity.class);
            result.putExtra("c1", candidato1);
            result.putExtra("c2", candidato2);
            result.putExtra("c3", candidato3);
            limpiar();
            etcantidad.setText("");
            etcantidad.setEnabled(true);
            etcantidad.requestFocus();
            candidato1 = 0;
            candidato2 = 0;
            candidato3 = 0;
            turno = 0;
            limpiar();
            startActivity(result);
        }
    }

    public void limpiar() {
        etnombre.setText("");
        etedad.setText("");
        rdbcandidato1.setChecked(false);
        rdbcandidato2.setChecked(false);
        rdbcandidato3.setChecked(false);
        etnombre.requestFocus();
    }

    public void stored() {
        Toast toast = Toast.makeText(getApplicationContext(), "Su voto ha sido guardado", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 30);
        toast.show();
    }

    public void toggleEnable() {
        if (etnombre.isEnabled()) {
            etnombre.setEnabled(false);
            etedad.setEnabled(false);
            rdbcandidato1.setEnabled(true);
            rdbcandidato2.setEnabled(true);
            rdbcandidato3.setEnabled(true);
        } else {
            etnombre.setEnabled(true);
            etedad.setEnabled(true);
            rdbcandidato1.setEnabled(false);
            rdbcandidato2.setEnabled(false);
            rdbcandidato3.setEnabled(false);
        }
    }

    //Procesamiento toast personalizados
    public void inflateToast(int id_toast, int id_layout, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(id_toast,
                (ViewGroup) findViewById(id_layout));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}