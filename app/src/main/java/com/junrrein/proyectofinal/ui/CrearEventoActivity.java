package com.junrrein.proyectofinal.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.junrrein.proyectofinal.databinding.DetalleEventoBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CrearEventoActivity extends AppCompatActivity {

    DetalleEventoBinding binding;

    @SuppressLint("SetTextI18n")
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
        binding.horaEvento.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        binding.latidudEvento.setText("?");
        binding.longitudEvento.setText("?");
    }

    public void onEditarNombreButtonClick(View view) {
        DialogFragment dialogFragment = new EditarCampoDialogFragment(
                "Nombre",
                binding.nombreEvento.getText().toString(),
                binding.nombreEvento::setText);

        dialogFragment.show(getSupportFragmentManager(), "EditarCampoDialogFragment");
    }

    public void onEditarDescripcionButtonClick(View view) {
        DialogFragment dialogFragment = new EditarCampoDialogFragment(
                "Descripción",
                binding.descripcionEvento.getText().toString(),
                binding.descripcionEvento::setText);

        dialogFragment.show(getSupportFragmentManager(), "EditarCampoDialogFragment");
    }

    public void onEditarOrganizadorButtonClick(View view) {
        DialogFragment dialogFragment = new EditarCampoDialogFragment(
                "Organizador",
                binding.organizadorEvento.getText().toString(),
                binding.organizadorEvento::setText);

        dialogFragment.show(getSupportFragmentManager(), "EditarCampoDialogFragment");
    }

    public void onEditarFechaButtonClick(View view) {
        DialogFragment dialogFragment = new FechaPickerDialogFragment((view1, year, month, dayOfMonth) -> {
            LocalDate date = LocalDate.of(year, month + 1, dayOfMonth);
            binding.fechaEvento.setText(date.toString());
        });

        dialogFragment.show(getSupportFragmentManager(), "FechaPickerDialogFragment");
    }

    public void onEditarHoraButtonClick(View view) {
        DialogFragment dialogFragment = new HoraPickerDialogFragment((view1, hourOfDay, minute) -> {
            LocalTime time = LocalTime.of(hourOfDay, minute);
            binding.horaEvento.setText(time.toString());
        });

        dialogFragment.show(getSupportFragmentManager(), "HoraPickerDialogFragment");
    }
}
