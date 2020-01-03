package com.junrrein.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String id = "10";
        Usuario usuario = new Usuario("Pepe Botellas", "pepe@españa.com");
        BaseDatosRemota.crearUsuario(id, usuario);
    }
}
