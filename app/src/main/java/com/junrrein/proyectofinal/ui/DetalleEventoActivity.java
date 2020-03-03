package com.junrrein.proyectofinal.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.junrrein.proyectofinal.backend.Evento;
import com.junrrein.proyectofinal.backend.Repositorio;
import com.junrrein.proyectofinal.backend.Ubicacion;
import com.junrrein.proyectofinal.databinding.DetalleEventoBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class DetalleEventoActivity extends AppCompatActivity {

    public static final String ID_USUARIO = "com.junrrein.proyectofinal.ID_USUARIO";
    public static final String ID_EVENTO = "com.junrrein.proyectofinal.ID_EVENTO";

    static private final int EDITAR_UBICACION_REQUEST = 1;

    private DetalleEventoBinding binding;
    private String idUsuario;
    private Evento evento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetalleEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Detalles del evento");

        Intent intent = getIntent();
        idUsuario = intent.getStringExtra(ID_USUARIO);
        String idEvento = intent.getStringExtra(ID_EVENTO);

        Repositorio.getEvento(idEvento).observe(this, this::actualizarVista);

        binding.cancelarButton.setVisibility(View.GONE);
        binding.crearEventoButton.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    private void actualizarVista(Evento evento) {
        this.evento = evento;

        binding.nombreEvento.setText(evento.getNombre());
        binding.descripcionEvento.setText(evento.getDescripcion());
        binding.organizadorEvento.setText(evento.getOrganizador());
        binding.fechaEvento.setText(evento.getFechaInicio().toString());
        binding.horaEvento.setText(evento.getHoraInicio().toString());

        if (!evento.getIdUsuarioCreador().equals(idUsuario)) {
            binding.editarNombreButton.setVisibility(View.GONE);
            binding.editarDescripcionButton.setVisibility(View.GONE);
            binding.editarOrganizadorButton.setVisibility(View.GONE);
            binding.editarFechaButton.setVisibility(View.GONE);
            binding.editarHoraButton.setVisibility(View.GONE);
            binding.editarUbicacionButon.setVisibility(View.GONE);
            binding.eliminarEventoButton.setVisibility(View.GONE);
        }

        binding.meInteresaButton.setEnabled(!evento.estaInteresado(idUsuario));
        binding.noMeInteresaButton.setEnabled(evento.estaInteresado(idUsuario));
        binding.asistireButton.setEnabled(!evento.asiste(idUsuario));
        binding.desconfirmarAsistenciaButton.setEnabled(evento.asiste(idUsuario));
        binding.dislikeButton.setEnabled(!evento.noLeGusta(idUsuario));
        binding.cancelarDislikeButton.setEnabled(evento.noLeGusta(idUsuario));

        actualizarListaUsuarios();
    }

    @SuppressLint("SetTextI18n")
    private void actualizarListaUsuarios() {
        RecyclerView listaUsuarios = binding.listaAsistentes;
        listaUsuarios.setHasFixedSize(true);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        Repositorio.getUsuariosAsistentesParaEvento(evento).observe(this, usuarios -> {
            if (usuarios.isEmpty()) {
                binding.headerAsisten.setText("Nadie confirmó asistencia aún");
                binding.listaAsistentes.setVisibility(View.GONE);
            } else {
                binding.headerAsisten.setText("Asisten");
                binding.listaAsistentes.setVisibility(View.VISIBLE);
                listaUsuarios.setAdapter(new ListaUsuariosAdapter(usuarios));
            }
        });
    }

    public void onEditarNombreButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment(
                "Nombre",
                evento.getNombre(),
                string -> {
                    evento.setNombre(string);
                    Repositorio.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarDescripcionButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment(
                "Descripción",
                evento.getDescripcion(),
                string -> {
                    evento.setDescripcion(string);
                    Repositorio.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarOrganizadorButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment(
                "Organizador",
                evento.getOrganizador(),
                string -> {
                    evento.setOrganizador(string);
                    Repositorio.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarFechaButtonClick(View view) {
        DialogFragment dialogFragment = new FechaPickerDialogFragment(evento.getFechaInicio(),
                (view1, year, month, dayOfMonth) -> {
                    LocalDate fecha = LocalDate.of(year, month + 1, dayOfMonth);
                    evento.setFechaInicio(fecha);
                    Repositorio.guardarEvento(evento);
                });

        dialogFragment.show(getSupportFragmentManager(), "FechaPickerDialogFragment");
    }

    public void onEditarHoraButtonClick(View view) {
        DialogFragment dialogFragment = new HoraPickerDialogFragment(evento.getHoraInicio(),
                (view1, hourOfDay, minute) -> {
                    LocalTime hora = LocalTime.of(hourOfDay, minute);
                    evento.setHoraInicio(hora);
                    Repositorio.guardarEvento(evento);
                });

        dialogFragment.show(getSupportFragmentManager(), "HoraPickerDialogFragment");
    }

    public void onMeInteresaClick(View view) {
        evento.agregarUsuarioInteresado(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onNoMeInteresaClick(View view) {
        evento.quitarUsuarioInteresado(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onAsistireClick(View view) {
        evento.agregarUsuarioAsistente(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onDesconfirmarAsistenciaClick(View view) {
        evento.quitarUsuarioAsistente(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onDislikeClick(View view) {
        evento.agregarUsuarioDislike(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onCancelarDislike(View view) {
        evento.quitarUsuarioDislike(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onEditarUbicacionClick(View view) {
        Intent intent = new Intent(this, ElegirUbicacionActivity.class);
        intent.putExtra(ElegirUbicacionActivity.UBICACION_ACTUAL, evento.getUbicacion());
        startActivityForResult(intent, EDITAR_UBICACION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDITAR_UBICACION_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                assert (data != null);

                Ubicacion ubicacion = (Ubicacion) data.getSerializableExtra(ElegirUbicacionActivity.UBICACION_NUEVA);
                evento.setUbicacion(ubicacion);
                Repositorio.guardarEvento(evento);
            }
        }
    }

    public void onVerEnMapaClick(View view) {
        ArrayList<EventoMapa> eventosMapa = new ArrayList<>();
        eventosMapa.add(new EventoMapa(evento));

        Intent intent = new Intent(this, MapaActivity.class);
        intent.putExtra(MapaActivity.ID_USUARIO, idUsuario);
        intent.putExtra(MapaActivity.EVENTOS_MAPA, eventosMapa);
        startActivity(intent);
    }

    public void onEliminarEventoClick(View view) {
        ConfirmacionDialogFragment dialogFragment = new ConfirmacionDialogFragment(
                "¿Eliminar evento?",
                "Esta acción no se puede deshacer",
                "Eliminar",
                () -> {
                    Repositorio.eliminarEvento(evento.getId());
                    finish();
                });

        dialogFragment.show(getSupportFragmentManager(), "EliminarEventoDialogFragment");
    }

    public void onCrearRecordatorioClick(View view) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(
                evento.getFechaInicio().getYear(),
                evento.getFechaInicio().getMonthValue() - 1,
                evento.getFechaInicio().getDayOfMonth(),
                evento.getHoraInicio().getHour(),
                evento.getHoraInicio().getSecond()
        );

        Intent calendarIntent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, evento.getNombre())
                .putExtra(CalendarContract.Events.DESCRIPTION, evento.getDescripcion());
        startActivity(calendarIntent);
    }
}
