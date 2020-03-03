package com.junrrein.proyectofinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.junrrein.proyectofinal.backend.Repositorio;
import com.junrrein.proyectofinal.backend.Usuario;
import com.junrrein.proyectofinal.databinding.ActivityMainBinding;
import com.junrrein.proyectofinal.ui.SeccionesPagerAdapter;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static private final int AUTENTICAR_REQUEST = 1;

    ActivityMainBinding binding;
    ModeloUsuario modeloUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Eventoline");

        modeloUsuario = new ViewModelProvider(this).get(ModeloUsuario.class);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            lanzarActividadAutenticacion();
        }
        else {
            modeloUsuario.setUsuario(firebaseUser.getUid());
            armarPantallaPrincipal();
        }
    }

    private void armarPantallaPrincipal() {
        SeccionesPagerAdapter pagerAdapter = new SeccionesPagerAdapter(getSupportFragmentManager());
        ViewPager pager = binding.viewPager;
        binding.viewPager.setAdapter(pagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(pager);
    }

    private void lanzarActividadAutenticacion() {
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.EmailBuilder().build());
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        startActivityForResult(intent, AUTENTICAR_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTENTICAR_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                assert (firebaseUser != null);

                String idUsuario = firebaseUser.getUid();
                Usuario usuario = new Usuario(idUsuario, firebaseUser.getDisplayName());
                Repositorio.guardarUsuario(usuario);
                modeloUsuario.setUsuario(idUsuario);

                armarPantallaPrincipal();
            } else {
                finish();
            }
        }
    }
}
