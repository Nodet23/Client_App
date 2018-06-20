package com.example.croxas.wow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    TextView textViewNombre;
    TextView textViewAtaque;
    TextView textViewDefensa;
    TextView textViewResistencia;
    TextView textViewNivel;
    TextView textViewExperiencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        textViewNombre = (TextView) findViewById(R.id.textViewNombre);
        textViewAtaque = (TextView) findViewById(R.id.textViewAtaque);
        textViewDefensa = (TextView) findViewById(R.id.textViewDefensa);
        textViewResistencia = (TextView) findViewById(R.id.textViewResistencia);
        textViewNivel = (TextView) findViewById(R.id.textViewNivel);
        textViewExperiencia = (TextView) findViewById(R.id.textViewExperiencia);


        Bundle extras = getIntent().getBundleExtra("bundle");
        Personaje personaje = (Personaje) extras.getSerializable("stats");

        textViewNombre.setText(personaje.getNombre());
        textViewAtaque.setText(String.valueOf(personaje.getAtaque()));
        textViewDefensa.setText(String.valueOf(personaje.getDefensa()));
        textViewResistencia.setText(String.valueOf(personaje.getResistencia()));
        textViewNivel.setText(String.valueOf(personaje.getNivel()));
        textViewExperiencia.setText(String.valueOf(personaje.getExperiencia()));
    }
}
