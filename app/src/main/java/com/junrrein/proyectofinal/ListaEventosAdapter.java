package com.junrrein.proyectofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaEventosAdapter
        extends RecyclerView.Adapter<ListaEventosAdapter.ViewHolder> {

    interface ItemClickListener {
        void onClick(String idEvento);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Evento evento;

        ViewHolder(View view, ItemClickListener itemClickListener) {
            super(view);

            this.textView = view.findViewById(R.id.texto_elemento);
            view.setOnClickListener(v -> itemClickListener.onClick(evento.getId()));
        }
    }

    private List<Evento> eventos;
    private ItemClickListener itemClickListener;

    ListaEventosAdapter(List<Evento> eventos,
                        ItemClickListener itemClickListener) {
        this.eventos = eventos;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.evento_elemento_lista, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
        holder.evento = eventos.get(position);
        holder.textView.setText(holder.evento.getNombre());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}
