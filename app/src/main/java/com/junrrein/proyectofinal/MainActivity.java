package com.junrrein.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String id = "10";
//        Usuario usuario = new Usuario("Pepe Botellas");
//        usuario.agregarEventoCreado("5");
//        usuario.agregarEventoSuscripto("5");
//        usuario.agregarEventoSuscripto("7");
//        BaseDatosRemota.crearUsuario(id, usuario);
//        BaseDatosRemota.eliminarUsuario(id);
//        BaseDatosRemota.actualizarUsuario(id, usuario);
//        BaseDatosRemota.getUsuario(id)
//                .addOnSuccessListener(usuario -> Log.d("Bien", usuario.toString()))
//                .addOnFailureListener(exception -> {
//                    Log.d("Error", exception.toString());
//                    exception.printStackTrace();
//                });


        String id = "15";
//        Evento evento = new Evento("Peña folclórica buenísima",
//                "1",
//                -32.0,
//                -60.0,
//                new Date().getTime(),
//                "Ponele que está más o menos buena");
//        evento.agregarUsuarioSuscripto("1");
//        evento.agregarUsuarioSuscripto("10");
//        evento.agregarUsuarioDislike("2");

//        BaseDatosRemota.crearEvento(id, evento)
//                .addOnSuccessListener(aVoid -> Log.d("Bien", "Evento creado con éxito"))
//                .addOnFailureListener(exception -> {
//                    Log.d("Error", exception.toString());
//                    exception.printStackTrace();
//                });
//        BaseDatosRemota.actualizarEvento(id, evento);
        BaseDatosRemota.getEvento(id)
                .addOnSuccessListener(evento -> Log.d("Bien", evento.toString()))
                .addOnFailureListener(exception -> {
                    Log.d("Error", exception.toString());
                    exception.printStackTrace();
                });
    }
}
