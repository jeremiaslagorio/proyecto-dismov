package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventoRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(EventoRoom evento);

    @Delete
    void delete(EventoRoom evento);

    @Query("SELECT * FROM eventos")
    LiveData<List<EventoRoom>> loadAll();

    @Query("SELECT * FROM eventos WHERE id=:idEvento")
    LiveData<EventoRoom> loadById(String idEvento);
}
