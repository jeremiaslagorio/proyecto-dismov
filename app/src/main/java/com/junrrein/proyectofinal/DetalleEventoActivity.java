package com.junrrein.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class DetalleEventoActivity extends AppCompatActivity {

    public static final String ID_EVENTO = "com.junrrein.proyectofinal.ID_EVENTO";

    private DetalleEventoModelo modelo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_evento);

        TextView nombreEventoView = findViewById(R.id.nombreEvento);
        TextView organizadorEventoView = findViewById(R.id.organizadorEvento);
        TextView fechaEventoView = findViewById(R.id.fechaEvento);
        TextView horaEventoView = findViewById(R.id.horaEvento);
        TextView descripcionEventoView = findViewById(R.id.descripcionEvento);

        Intent intent = getIntent();
        String idEvento = intent.getStringExtra(ID_EVENTO);
        modelo = new ViewModelProvider(this).get(DetalleEventoModelo.class);
        modelo.setEvento(idEvento);

        modelo.getEvento().observe(this, evento -> {
            nombreEventoView.setText(evento.getNombre());
            organizadorEventoView.setText(evento.getOrganizador());
            fechaEventoView.setText(evento.getFechaInicio().toString());
            horaEventoView.setText(evento.getHoraInicio().toString());
            descripcionEventoView.setText(evento.getDescripcion());
        });
    }

    public void onEditarNombreButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment("Nombre",
                string -> {
                    Evento evento = modelo.getEvento().getValue();
                    assert(evento != null);
                    evento.setNombre(string);
                    modelo.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarDescripcionButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment("Descripción",
                string -> {
                    Evento evento = modelo.getEvento().getValue();
                    assert(evento != null);
                    evento.setDescripcion(string);
                    modelo.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }

    public void onEditarOrganizadorButtonClick(View view) {
        EditarCampoDialogFragment dialog = new EditarCampoDialogFragment("Organizador",
                string -> {
                    Evento evento = modelo.getEvento().getValue();
                    assert(evento != null);
                    evento.setOrganizador(string);
                    modelo.guardarEvento(evento);
                });

        dialog.show(getSupportFragmentManager(), "EditarCampoFragment");
    }
}
