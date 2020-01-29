package com.junrrein.proyectofinal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EventoRoom.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EventoRoomDao eventoRoomDao();
}
