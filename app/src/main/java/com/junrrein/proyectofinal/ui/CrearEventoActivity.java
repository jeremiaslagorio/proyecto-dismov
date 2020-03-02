package com.junrrein.proyectofinal.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.junrrein.proyectofinal.backend.Evento;
import com.junrrein.proyectofinal.backend.Repositorio;
import com.junrrein.proyectofinal.backend.Ubicacion;
import com.junrrein.proyectofinal.databinding.DetalleEventoBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CrearEventoActivity extends AppCompatActivity {

    private DetalleEventoBinding binding;
    private String idUsuario;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetalleEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        idUsuario = intent.getStringExtra(DetalleEventoActivity.ID_USUARIO);

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
        DialogFragment dialogFragment = new FechaPickerDialogFragment(LocalDate.now(),
                (view1, year, month, dayOfMonth) -> {
                    LocalDate date = LocalDate.of(year, month + 1, dayOfMonth);
                    binding.fechaEvento.setText(date.toString());
                });

        dialogFragment.show(getSupportFragmentManager(), "FechaPickerDialogFragment");
    }

    public void onEditarHoraButtonClick(View view) {
        DialogFragment dialogFragment = new HoraPickerDialogFragment(LocalTime.now(),
                (view1, hourOfDay, minute) -> {
                    LocalTime time = LocalTime.of(hourOfDay, minute);
                    binding.horaEvento.setText(time.toString());
                });

        dialogFragment.show(getSupportFragmentManager(), "HoraPickerDialogFragment");
    }

    public void onCancelarClick(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void onCrearEventoClick(View view) {
        String idNuevoEvento = Repositorio.crearIdDeEvento();
        Evento nuevoEvento = new Evento(idNuevoEvento,
                binding.nombreEvento.getText().toString(),
                idUsuario,
                binding.organizadorEvento.getText().toString(),
                new Ubicacion(-31.6414142, -60.7063523),
                LocalDate.parse(binding.fechaEvento.getText()),
                LocalTime.parse(binding.horaEvento.getText()));
        nuevoEvento.setDescripcion(binding.descripcionEvento.getText().toString());

        Intent resultIntent = new Intent();
        resultIntent.putExtra(ListaEventosFragment.EVENTO, nuevoEvento);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
