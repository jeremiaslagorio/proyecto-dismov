package com.junrrein.proyectofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class DetalleEventoFragment extends Fragment {

    private TextView nombreEventoView;
    private TextView organizadorEventoView;
    private TextView fechaEventoView;
    private TextView horaEventoView;
    private TextView descripcionEventoView;

    static DetalleEventoFragment newInstance(String idEvento) {
        DetalleEventoFragment fragment = new DetalleEventoFragment();

        Bundle arguments = new Bundle();
        arguments.putString("idEvento", idEvento);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_evento, container, false);

        nombreEventoView = view.findViewById(R.id.nombreEvento);
        organizadorEventoView = view.findViewById(R.id.organizadorEvento);
        fechaEventoView = view.findViewById(R.id.fechaEvento);
        horaEventoView = view.findViewById(R.id.horaEvento);
        descripcionEventoView = view.findViewById(R.id.descripcionEvento);

        assert(getArguments() != null);
        String idEvento = getArguments().getString("idEvento");

        DetalleEventoModelo modelo = ViewModelProviders.of(this).get(DetalleEventoModelo.class);
        modelo.setEvento(idEvento);

        modelo.getEvento().observe(getViewLifecycleOwner(), evento -> {
            nombreEventoView.setText(evento.getNombre());
            organizadorEventoView.setText(evento.getOrganizador());
            fechaEventoView.setText(evento.getFechaInicio().toString());
            horaEventoView.setText(evento.getHoraInicio().toString());
            descripcionEventoView.setText(evento.getDescripcion());
        });

        return view;
    }
}
