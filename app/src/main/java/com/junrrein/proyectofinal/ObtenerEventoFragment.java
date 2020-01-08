package com.junrrein.proyectofinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ObtenerEventoFragment extends Fragment {

    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.obtener_evento, container, false);

        editText = view.findViewById(R.id.selectorEventoEditText);
        Button button = view.findViewById(R.id.buttonMostrar);

        button.setOnClickListener(this::onButtonClick);

        return view;
    }

    private void onButtonClick(View view) {
        String idEvento = editText.getText().toString();
        Fragment detalleFragment = DetalleEventoFragment.newInstance(idEvento);

        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_fragment, detalleFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
