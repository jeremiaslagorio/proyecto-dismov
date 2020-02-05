package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
abstract class UsuarioRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void save(UsuarioRoom usuario);

    @Query("DELETE FROM usuarios WHERE id=:idUsuario")
    abstract void delete(String idUsuario);

    @Query("SELECT * FROM usuarios WHERE id=:idUsuario")
    abstract LiveData<UsuarioRoom> loadById(String idUsuario);

    @Query("SELECT COUNT(*) FROM usuarios WHERE id=:idUsuario")
    abstract int exists(String idUsuario);

    @Query("SELECT ultimaActualizacion FROM usuarios WHERE id=:idUsuario")
    abstract long ultimaActualizacion(String idUsuario);
}
