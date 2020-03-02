package com.junrrein.proyectofinal.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.junrrein.proyectofinal.databinding.DetalleEventoBinding;

import java.time.LocalDate;
import java.time.LocalTime;

public class CrearEventoActivity extends AppCompatActivity {

    DetalleEventoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetalleEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.meInteresaButton.setVisibility(View.GONE);
        binding.noMeInteresaButton.setVisibility(View.GONE);
        binding.asistireButton.setVisibility(View.GONE);
        binding.desconfirmarAsistenciaButton.setVisibility(View.GONE);
        binding.dislikeButton.setVisibility(View.GONE);
        binding.cancelarDislikeButton.setVisibility(View.GONE);
        binding.eliminarEventoButton.setVisibility(View.GONE);

        binding.mapaButton.setEnabled(false);

        binding.nombreEvento.setText("Nombre del evento");
        binding.descripcionEvento.setText("Descripcion del evento");
        binding.organizadorEvento.setText("Organizador del evento");
        binding.fechaEvento.setText(LocalDate.now().toString());
        binding.horaEvento.setText(LocalTime.now().toString());
        binding.latidudEvento.setText("?");
        binding.longitudEvento.setText("?");
    }
}
