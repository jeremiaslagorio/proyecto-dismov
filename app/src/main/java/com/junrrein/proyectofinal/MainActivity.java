package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ViewModelProvider(this).get(Modelo.class).setUsuario("10");

        if (savedInstanceState != null)
            return;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedor_fragment, new ListaEventosFragment())
                .commit();
//        ejemplosFirebase();
    }

    void ejemplosFirebase() {
//        String idUsuario = "10";
//        Usuario usuario = new Usuario(idUsuario, "Josecito Benavidez");
//        usuario.agregarEventoCreado("5");
//        usuario.agregarEventoSuscripto("5");
//        usuario.agregarEventoSuscripto("7");
//        BaseDatosRemota.eliminarUsuario(id);
//        BaseDatosRemota.guardarUsuario(usuario);
//        BaseDatosRemota.getUsuario(idUsuario)
//                .addOnSuccessListener(usuario -> Log.d("Bien", usuario.toString()))
//                .addOnFailureListener(exception -> {
//                    Log.d("Error", exception.toString());
//                    exception.printStackTrace();
//                });


//        String idUsuario = "10";
//        String idEvento = "17";
//        Evento evento = new Evento(
//                idEvento,
//                "Peña folclórica buenísima",
//                idUsuario,
//                "Agrupación Folclórica Reconquista",
//                new Ubicacion(-32.0, -60.0),
//                LocalDate.now(),
//                LocalTime.now());
//        evento.setDescripcion("El mejor espectáculo tradicional de la provincia");
//        evento.agregarUsuarioSuscripto("1");
//        evento.agregarUsuarioSuscripto("10");
//        evento.agregarUsuarioDislike("2");
//
//        BaseDatosRemota.guardarEvento(evento);

//        Evento evento = new Evento(
//                "17",
//                "Rockeando con los Rockeros",
//                "Judas Priest",
//                "10",
//                new Ubicacion(-32.0, -60.0),
//                LocalDate.now(),
//                LocalTime.now());
//        evento.setDescripcion("Revoleá la cabeza como loco");
//        evento.agregarUsuarioSuscripto("1");
//        evento.agregarUsuarioSuscripto("10");
//        evento.agregarUsuarioDislike("2");
//
//        BaseDatosRemota.crearEvento(evento)
//                .addOnSuccessListener(aVoid -> Log.d("Bien", "Evento creado con éxito"))
//                .addOnFailureListener(exception -> {
//                    Log.d("Error", exception.toString());
//                    exception.printStackTrace();
//                });
//        BaseDatosRemota.guardarEvento(idEvento, evento);
//        BaseDatosRemota.eliminarEvento(idEvento);
//        BaseDatosRemota.getEvento(idEvento)
//                .addOnSuccessListener(evento -> Log.d("Bien", evento.toString()))
//                .addOnFailureListener(exception -> {
//                    Log.d("Error", exception.toString());
//                    exception.printStackTrace();
//                });
    }
}
