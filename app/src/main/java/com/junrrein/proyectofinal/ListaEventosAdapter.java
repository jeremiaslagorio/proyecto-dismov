package com.junrrein.proyectofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListaEventosAdapter
        extends RecyclerView.Adapter<ListaEventosAdapter.ListaEventosViewHolder> {

    private String[] datos;

    public static class ListaEventosViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ListaEventosViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.texto_elemento);
        }
    }

    public ListaEventosAdapter(String[] datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public ListaEventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.evento_elemento_lista, parent, false);

        return new ListaEventosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEventosViewHolder holder,
                                 int position) {
        holder.textView.setText(datos[position]);
    }

    @Override
    public int getItemCount() {
        return datos.length;
    }
}
