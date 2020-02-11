package com.junrrein.proyectofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventosSuscriptosAdapter
        extends RecyclerView.Adapter<EventosSuscriptosAdapter.ViewHolder> {

    interface ItemClickListener {
        void onClick(String idEvento);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Evento evento;

        ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            textView = itemView.findViewById(R.id.texto_elemento);
            itemView.setOnClickListener(v -> itemClickListener.onClick(evento.getId()));
        }
    }

    private List<LiveData<Evento>> eventos;
    private LifecycleOwner lifeCycleOwner;
    private ItemClickListener itemClickListener;

    EventosSuscriptosAdapter(List<LiveData<Evento>> eventos,
                                    LifecycleOwner lifeCycleOwner,
                                    ItemClickListener itemClickListener) {
        this.eventos = eventos;
        this.lifeCycleOwner = lifeCycleOwner;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.evento_elemento_lista, parent, false);

        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        eventos.get(position).observe(lifeCycleOwner, evento -> {
            holder.evento = evento;
            holder.textView.setText(evento.getNombre());
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}
