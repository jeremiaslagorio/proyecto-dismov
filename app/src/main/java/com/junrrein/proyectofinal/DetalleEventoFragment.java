package com.junrrein.proyectofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class DetalleEventoFragment extends Fragment {

    private TextView nombreEventoView;
    private TextView creadorEventoView;
    private TextView fechaHoraEventoView;
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
        creadorEventoView = view.findViewById(R.id.creadorEvento);
        fechaHoraEventoView = view.findViewById(R.id.fechaHoraEvento);
        descripcionEventoView = view.findViewById(R.id.descripcionEvento);

        Modelo modelo = ViewModelProviders.of(this).get(Modelo.class);

        assert(getArguments() != null);
        String idEvento = getArguments().getString("idEvento");

        modelo.getEvento(idEvento).observe(getViewLifecycleOwner(), evento -> {
            nombreEventoView.setText(evento.getNombre());
            creadorEventoView.setText(evento.getIdUsuarioCreador());
            fechaHoraEventoView.setText(evento.getFechaHoraInicio().toString());
            descripcionEventoView.setText(evento.getDescripcion());
        });

        return view;
    }
}
