package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class ModeloVista extends ViewModel {

    private static final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static final String nodoUsuarios = "usuarios";

    static private void existeNodoAsync(String nodo, Consumer<Boolean> consumidor) {
        database.child(nodo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                consumidor.accept(dataSnapshot.exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    static void existeUsuario(String id, Consumer<Boolean> consumidor) {
        existeNodoAsync(nodoUsuarios + "/" + id, consumidor);
    }

    static void crearUsuario(String id, Usuario usuario) {
        existeUsuario(id, existe -> {
            if (!existe)
                database.child(nodoUsuarios).child(id).setValue(usuario);
        });
    }

    static void actualizarUsuario(String id, Usuario usuario) {
        existeUsuario(id, existe -> {
            if (existe)
                database.child(nodoUsuarios).child(id).setValue(usuario);
        });
    }

    static void eliminarUsuario(String id) {
        database.child(nodoUsuarios).child(id).removeValue();
    }
}
