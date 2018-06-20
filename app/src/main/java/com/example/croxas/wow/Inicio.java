package com.example.croxas.wow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import junit.framework.Test;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void iniciarPartida(View view) {
        Intent intent = new Intent(getApplicationContext(), TestCanvas.class);
        intent.putExtra("opcion", 0);
        startActivity(intent);
    }

    public void cargarPartida(View view) {
        Intent intent = new Intent(getApplicationContext(), TestCanvas.class);
        intent.putExtra("opcion", 1);
        startActivity(intent);
    }
}
