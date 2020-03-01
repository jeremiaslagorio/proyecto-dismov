package com.junrrein.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.junrrein.proyectofinal.databinding.ActivityMainBinding;
import com.junrrein.proyectofinal.ui.SeccionesPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ModeloUsuario modeloUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        modeloUsuario = modeloUsuario = new ViewModelProvider(this).get(ModeloUsuario.class);
        modeloUsuario.setUsuario("10");

        SeccionesPagerAdapter pagerAdapter = new SeccionesPagerAdapter(getSupportFragmentManager(), this);
        ViewPager pager = binding.viewPager;
        pager.setAdapter(pagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(pager);

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
