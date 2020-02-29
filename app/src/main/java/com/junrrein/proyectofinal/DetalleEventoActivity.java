package com.junrrein.proyectofinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.junrrein.proyectofinal.databinding.DetalleEventoBinding;

public class DetalleEventoActivity extends AppCompatActivity {

    public static final String ID_USUARIO = "com.junrrein.proyectofinal.ID_USUARIO";
    public static final String ID_EVENTO = "com.junrrein.proyectofinal.ID_EVENTO";

    private DetalleEventoBinding binding;
    private String idUsuario;
    private String idEvento;
    private Usuario usuario;
    private Evento evento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetalleEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        idUsuario = intent.getStringExtra(ID_USUARIO);
        idEvento = intent.getStringExtra(ID_EVENTO);

        Repositorio.getUsuario(idUsuario).observe(this, usuario -> this.usuario = usuario);
        Repositorio.getEvento(idEvento).observe(this, this::actualizarVista);
    }

    @SuppressLint("SetTextI18n")
    private void actualizarVista(Evento evento) {
        this.evento = evento;

        binding.nombreEvento.setText(evento.getNombre());
        binding.descripcionEvento.setText(evento.getDescripcion());
        binding.organizadorEvento.setText(evento.getOrganizador());
        binding.fechaEvento.setText(evento.getFechaInicio().toString());
        binding.horaEvento.setText(evento.getHoraInicio().toString());
        binding.latidudEvento.setText(evento.getUbicacion().latitud.toString());
        binding.longitudEvento.setText(evento.getUbicacion().longitud.toString());

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

    public void onMeInteresaClick(View view) {
        evento.agregarUsuarioInteresado(idUsuario);
        usuario.agregarEventoInteresado(idEvento);
        Repositorio.guardarEvento(evento);
        Repositorio.guardarUsuario(usuario);
    }

    public void onNoMeInteresaClick(View view) {
        evento.quitarUsuarioInteresado(idUsuario);
        usuario.quitarEventoInteresado(idEvento);
        Repositorio.guardarEvento(evento);
        Repositorio.guardarUsuario(usuario);
    }

    public void onAsistireClick(View view) {
        evento.agregarUsuarioAsistente(idUsuario);
        usuario.agregarEventoAsiste(idEvento);
        Repositorio.guardarEvento(evento);
        Repositorio.guardarUsuario(usuario);
    }

    public void onDesconfirmarAsistenciaClick(View view) {
        evento.quitarUsuarioAsistente(idUsuario);
        usuario.quitarEventoAsiste(idEvento);
        Repositorio.guardarEvento(evento);
        Repositorio.guardarUsuario(usuario);
    }

    public void onDislikeClick(View view) {
        evento.agregarUsuarioDislike(idUsuario);
        Repositorio.guardarEvento(evento);
    }

    public void onCancelarDislike(View view) {
        evento.quitarUsuarioDislike(idUsuario);
        Repositorio.guardarEvento(evento);
    }
}
