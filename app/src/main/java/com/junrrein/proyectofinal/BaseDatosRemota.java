package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaseDatosRemota {

    private static final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static final String nodoUsuarios = "usuarios";

    static private Task<Boolean> existeNodo(String nodo) {
        TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();

        database.child(nodo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskCompletionSource.setResult(dataSnapshot.exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                taskCompletionSource.setException(databaseError.toException());
            }
        });

        return taskCompletionSource.getTask();
    }

    static Task<Boolean> existeUsuario(String id) {
        return existeNodo(nodoUsuarios + "/" + id);
    }

    static void setearUsuario(String id, Usuario usuario) {
        database.child(nodoUsuarios).child(id).setValue(usuario);
    }

    static void eliminarUsuario(String id) {
        database.child(nodoUsuarios).child(id).removeValue();
    }
}
