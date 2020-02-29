package com.junrrein.proyectofinal.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.junrrein.proyectofinal.backend.Evento;
import com.junrrein.proyectofinal.R;

import java.util.List;
import java.util.function.Consumer;

public class ListaEventosAdapter
        extends RecyclerView.Adapter<ListaEventosAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Evento evento;

        ViewHolder(View view) {
            super(view);

            this.textView = view.findViewById(R.id.texto_elemento);
        }

        void setClickListener(Runnable listener) {
            itemView.setOnClickListener(v -> listener.run());
        }
    }

    private List<Evento> eventos;
    private Consumer<String> mostradorEvento;
    private Consumer<List<Evento>> mostradorMapa;

    ListaEventosAdapter(List<Evento> eventos,
                        Consumer<String> mostradorEvento,
                        Consumer<List<Evento>> mostradorMapa) {
        this.eventos = eventos;
        this.mostradorEvento = mostradorEvento;
        this.mostradorMapa = mostradorMapa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.evento_elemento_lista, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        if (position != 0) {
            holder.evento = eventos.get(position - 1);
            holder.textView.setText(holder.evento.getNombre());
            holder.setClickListener(() -> mostradorEvento.accept(holder.evento.getId()));
        } else {
            holder.textView.setText("Mostrar en el mapa");
            holder.setClickListener(() -> mostradorMapa.accept(eventos));
        }
    }

    @Override
    public int getItemCount() {
        if (eventos.size() != 0)
            return eventos.size() + 1;

        return 0;
    }
}
