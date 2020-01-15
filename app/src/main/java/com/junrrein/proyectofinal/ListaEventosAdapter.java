package com.junrrein.proyectofinal;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaEventosAdapter
        extends RecyclerView.Adapter<ListaEventosAdapter.ListaEventosViewHolder> {

    interface ItemClickListener {
        void onClick(String idEvento);
    }

    public static class ListaEventosViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Evento evento;

        public ListaEventosViewHolder(View view, ItemClickListener itemClickListener) {
            super(view);

            this.textView = view.findViewById(R.id.texto_elemento);
            view.setOnClickListener(v -> itemClickListener.onClick(evento.getId()));
        }
    }

    private ArrayList<Evento> eventos;
    private ItemClickListener itemClickListener;

    public ListaEventosAdapter(ArrayList<Evento> eventos,
                               ItemClickListener itemClickListener) {
        this.eventos = eventos;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ListaEventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.evento_elemento_lista, parent, false);

        return new ListaEventosViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEventosViewHolder holder,
                                 int position) {
        holder.evento = eventos.get(position);
        holder.textView.setText(holder.evento.getNombre());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}
