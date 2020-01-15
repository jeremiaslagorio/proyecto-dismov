package com.junrrein.proyectofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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

        Modelo modelo = ViewModelProviders.of(this).get(Modelo.class);

        RecyclerView listaEventosRecyclerView = view.findViewById(R.id.lista_eventos_recyclerview);

        listaEventosRecyclerView.setHasFixedSize(true);
        listaEventosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(new ArrayList<>(), this.itemClickListener));

        modelo.getEventos().observe(getViewLifecycleOwner(), eventos -> {
            listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(eventos, this.itemClickListener));
        });

        return view;
    }

    private ListaEventosAdapter.ItemClickListener itemClickListener = idEvento -> {
        Fragment detalleFragment = DetalleEventoFragment.newInstance(idEvento);

        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_fragment, detalleFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };
}
