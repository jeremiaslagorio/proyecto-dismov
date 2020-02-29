package com.junrrein.proyectofinal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.junrrein.proyectofinal.Evento;
import com.junrrein.proyectofinal.R;
import com.junrrein.proyectofinal.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListaEventosFragment extends Fragment {
    private String idUsuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_eventos, container, false);

        Bundle arguments = requireArguments();
        idUsuario = arguments.getString(DetalleEventoActivity.ID_USUARIO);

        RecyclerView listaEventosRecyclerView = view.findViewById(R.id.lista_eventos_recyclerview);

        listaEventosRecyclerView.setHasFixedSize(true);
        listaEventosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(new ArrayList<>(), mostradorEvento, mostradorMapa));

        Repositorio.getEventos().observe(getViewLifecycleOwner(), eventos ->
                listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(eventos, mostradorEvento, mostradorMapa))
        );

        return view;
    }

    private Consumer<String> mostradorEvento = idEvento -> {
        Intent intent = new Intent(requireActivity(), DetalleEventoActivity.class);
        intent.putExtra(DetalleEventoActivity.ID_USUARIO, idUsuario);
        intent.putExtra(DetalleEventoActivity.ID_EVENTO, idEvento);
        startActivity(intent);
    };

    private Consumer<List<Evento>> mostradorMapa = eventos -> {
        Intent intent = new Intent(requireActivity(), MapaActivity.class);
        intent.putExtra(MapaActivity.EVENTOS, (ArrayList<Evento>) eventos);
        startActivity(intent);
    };
}
