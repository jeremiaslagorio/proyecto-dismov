package com.junrrein.proyectofinal.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.junrrein.proyectofinal.ModeloUsuario;

public class SeccionesPagerAdapter extends FragmentPagerAdapter {

    private static final String[] nombreSecciones = {"Interesado", "Todos"};

    public SeccionesPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();

        if (position == 0) {
            fragment = new ListaEventosFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable(ListaEventosFragment.TIPO_LISTA, ListaEventosFragment.TipoLista.INTERESADOS);
            fragment.setArguments(arguments);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nombreSecciones[position];
    }
}
