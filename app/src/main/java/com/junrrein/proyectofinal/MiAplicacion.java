package com.junrrein.proyectofinal;

import android.app.Application;

import androidx.room.Room;

public class MiAplicacion extends Application {

    static private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "base-datos-local")
                .allowMainThreadQueries()
                .build();
    }

    static AppDatabase getDatabase() {
        return database;
    }
}
