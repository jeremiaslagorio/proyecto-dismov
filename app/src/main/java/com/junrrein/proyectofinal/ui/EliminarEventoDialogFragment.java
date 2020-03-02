package com.junrrein.proyectofinal.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EliminarEventoDialogFragment extends DialogFragment {

    private Runnable onSuccessFunction;

    EliminarEventoDialogFragment(Runnable onSuccessFunction) {
        this.onSuccessFunction = onSuccessFunction;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        builder.setTitle("¿Eliminar evento?");
        builder.setMessage("Esta acción no se puede deshacer");
        builder.setPositiveButton("Eliminar",
                (dialog, which) -> onSuccessFunction.run());
        builder.setNegativeButton("Cancelar",
                (dialog, which) -> dialog.cancel());

        return builder.create();
    }
}
