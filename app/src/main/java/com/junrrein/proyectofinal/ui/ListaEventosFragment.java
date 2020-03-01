package com.junrrein.proyectofinal.ui;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.junrrein.proyectofinal.ModeloUsuario;
import com.junrrein.proyectofinal.backend.Evento;
import com.junrrein.proyectofinal.R;
import com.junrrein.proyectofinal.backend.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListaEventosFragment extends Fragment {

    static final String TIPO_LISTA = "com.junrrein.proyectofinal.ui.tipo-lista";

    enum TipoLista {
        CREADOS, INTERESADO, TODOS
    }

    private ModeloUsuario modeloUsuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_eventos, container, false);

        RecyclerView listaEventosRecyclerView = view.findViewById(R.id.lista_eventos_recyclerview);

        listaEventosRecyclerView.setHasFixedSize(true);
        listaEventosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(new ArrayList<>(), mostradorEvento, mostradorMapa));

        modeloUsuario = new ViewModelProvider(requireActivity()).get(ModeloUsuario.class);

        Bundle arguments = requireArguments();
        TipoLista tipoLista = (TipoLista) arguments.getSerializable(TIPO_LISTA);
        assert (tipoLista != null);

        switch (tipoLista) {
            case CREADOS: {
                Repositorio.getEventosParaUsuarioCreador(modeloUsuario.idUsuario).observe(getViewLifecycleOwner(),
                        eventos -> listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(eventos, mostradorEvento, mostradorMapa)));
                break;
            }

            case INTERESADO: {
                Repositorio.getEventosParaUsuarioInteresado(modeloUsuario.idUsuario).observe(getViewLifecycleOwner(),
                        eventos -> listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(eventos, mostradorEvento, mostradorMapa)));

                FloatingActionButton fab = view.findViewById(R.id.fab);
                fab.setVisibility(View.GONE);
                break;
            }

            case TODOS: {
                Repositorio.getEventos().observe(getViewLifecycleOwner(),
                        eventos -> listaEventosRecyclerView.setAdapter(new ListaEventosAdapter(eventos, mostradorEvento, mostradorMapa))
                );

                FloatingActionButton fab = view.findViewById(R.id.fab);
                fab.setVisibility(View.GONE);
                break;
            }
        }

        return view;
    }

    private Consumer<String> mostradorEvento = idEvento -> {
        Intent intent = new Intent(requireActivity(), EventoActivity.class);
        intent.putExtra(EventoActivity.ID_USUARIO, modeloUsuario.idUsuario);
        intent.putExtra(EventoActivity.ID_EVENTO, idEvento);
        startActivity(intent);
    };

    private Consumer<List<Evento>> mostradorMapa = eventos -> {
        Intent intent = new Intent(requireActivity(), MapaActivity.class);
        intent.putExtra(MapaActivity.EVENTOS, (ArrayList<Evento>) eventos);
        startActivity(intent);
    };
}
