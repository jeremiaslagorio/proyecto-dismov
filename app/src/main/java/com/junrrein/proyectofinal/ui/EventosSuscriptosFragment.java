package com.junrrein.proyectofinal.ui;

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

import com.junrrein.proyectofinal.ModeloUsuario;
import com.junrrein.proyectofinal.R;

public class EventosSuscriptosFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_eventos, container, false);

        RecyclerView listaEventosRecyclerView = view.findViewById(R.id.lista_eventos_recyclerview);

        listaEventosRecyclerView.setHasFixedSize(true);
        listaEventosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        assert(getActivity() != null);
        ModeloUsuario modeloUsuario = new ViewModelProvider(getActivity()).get(ModeloUsuario.class);

        modeloUsuario.getEventosSuscriptos().observe(getViewLifecycleOwner(),
                eventosLiveData ->
                        listaEventosRecyclerView.setAdapter(new EventosSuscriptosAdapter(eventosLiveData,
                                getViewLifecycleOwner(),
                                itemClickListener))
        );

        return view;
    }

    private EventosSuscriptosAdapter.ItemClickListener itemClickListener = idEvento -> {
        Activity activity = getActivity();
        assert (activity != null);

        Intent intent = new Intent(activity, EventoActivity.class);
        intent.putExtra(EventoActivity.ID_EVENTO, idEvento);
        startActivity(intent);
    };
}
