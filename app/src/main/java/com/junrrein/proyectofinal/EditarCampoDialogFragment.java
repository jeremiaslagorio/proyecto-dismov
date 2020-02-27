package com.junrrein.proyectofinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.function.Consumer;

public class EditarCampoDialogFragment extends DialogFragment {
    private String nombreCampo;
    private Consumer<String> onSuccessFunction;

    EditarCampoDialogFragment(String nombreCampo, Consumer<String> onSuccessFunction) {
        this.nombreCampo = nombreCampo;
        this.onSuccessFunction = onSuccessFunction;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.editar_campo, null);
        EditText campo = view.findViewById(R.id.campo);
        campo.setHint(nombreCampo);
        campo.requestFocus();

        builder.setView(view);
        builder.setTitle("Editar " + nombreCampo);
        builder.setPositiveButton("Editar", (dialog, which) -> {
            onSuccessFunction.accept(campo.getText().toString());
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            assert (this.getDialog() != null);
            this.getDialog().cancel();
        });

        return builder.create();
    }
}
