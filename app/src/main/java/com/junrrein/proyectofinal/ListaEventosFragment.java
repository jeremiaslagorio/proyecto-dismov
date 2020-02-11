package com.junrrein.proyectofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaEventosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_eventos, container, false);

        Modelo modelo = new ViewModelProvider(this).get(Modelo.class);

        RecyclerView listaEventosRecyclerView = view.findViewById(R.id.lista_eventos_recyclerview);

        listaEventosRecyclerView.setHasFixedSize(true);
        listaEventosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(new ArrayList<>(), this.itemClickListener));

        modelo.getEventos().observe(getViewLifecycleOwner(), eventos ->
                listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(eventos, this.itemClickListener))
        );

        return view;
    }

    private ListaEventosAdapter.ItemClickListener itemClickListener = idEvento -> {
        Activity activity = getActivity();
        assert (activity != null);

        Intent intent = new Intent(activity, DetalleEventoActivity.class);
        intent.putExtra(DetalleEventoActivity.ID_EVENTO, idEvento);
        startActivity(intent);
    };
}
