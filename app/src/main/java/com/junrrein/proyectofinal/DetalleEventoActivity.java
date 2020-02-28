package com.junrrein.proyectofinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.junrrein.proyectofinal.databinding.DetalleEventoBinding;

public class DetalleEventoActivity extends AppCompatActivity {

    public static final String ID_EVENTO = "com.junrrein.proyectofinal.ID_EVENTO";

    private DetalleEventoBinding binding;
    private ModeloEvento modeloEvento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetalleEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String idEvento = intent.getStringExtra(ID_EVENTO);
        modeloEvento = new ViewModelProvider(this).get(ModeloEvento.class);
        modeloEvento.setEvento(idEvento);

        modeloEvento.getEvento().observe(this, this::actualizarVista);
    }

    @SuppressLint("SetTextI18n")
    private void actualizarVista(Evento evento) {
        binding.nombreEvento.setText(evento.getNombre());
        binding.descripcionEvento.setText(evento.getDescripcion());
        binding.organizadorEvento.setText(evento.getOrganizador());
        binding.fechaEvento.setText(evento.getFechaInicio().toString());
        binding.horaEvento.setText(evento.getHoraInicio().toString());
        binding.latidudEvento.setText(evento.getUbicacion().latitud.toString());
        binding.longitudEvento.setText(evento.getUbicacion().longitud.toString());
    }

    public void onEditarNombreButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment("Nombre",
                string -> {
                    Evento evento = modeloEvento.getEvento().getValue();
                    assert(evento != null);
                    evento.setNombre(string);
                    modeloEvento.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarDescripcionButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment("Descripción",
                string -> {
                    Evento evento = modeloEvento.getEvento().getValue();
                    assert(evento != null);
                    evento.setDescripcion(string);
                    modeloEvento.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarOrganizadorButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment("Organizador",
                string -> {
                    Evento evento = modeloEvento.getEvento().getValue();
                    assert(evento != null);
                    evento.setOrganizador(string);
                    modeloEvento.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }
}
