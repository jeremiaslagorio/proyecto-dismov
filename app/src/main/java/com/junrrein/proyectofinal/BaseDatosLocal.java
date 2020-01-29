package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;

import java.time.Instant;
import java.util.List;

class BaseDatosLocal {

    static private AppDatabase database = MiAplicacion.getDatabase();
    static private EventoRoomDao eventoRoomDao = database.eventoRoomDao();

    static void guardarEvento(Evento evento) {
        eventoRoomDao.save(new EventoRoom(evento));
    }

    static void eliminarEvento(Evento evento) {
        eventoRoomDao.delete(new EventoRoom(evento));
    }

    static LiveData<EventoRoom> getEvento(String idEvento) {
        return eventoRoomDao.loadById(idEvento);
    }

    static LiveData<List<EventoRoom>> getEventos() {
        return eventoRoomDao.loadAll();
    }

    static boolean existeEvento(String idEvento) {
        return eventoRoomDao.exists(idEvento) != 0;
    }

    static Instant ultimaActualizacionEvento(String idEvento) {
        return Instant.ofEpochSecond(eventoRoomDao.ultimaActualizacion(idEvento));
    }
}
