package com.junrrein.proyectofinal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {EventoRoom.class, UsuarioRoom.class},
        version = 4,
        exportSchema = false
)
abstract class AppDatabase extends RoomDatabase {

    abstract EventoRoomDao eventoRoomDao();
    abstract UsuarioRoomDao usuarioRoomDao();
}
