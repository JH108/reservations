package me.jessehill.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import me.jessehill.models.Reservation
import java.util.UUID

interface ReservationDatabase {
    suspend fun createReservation(reservation: Reservation): Flow<Reservation>

    suspend fun getReservation(id: UUID): Flow<Reservation>

    suspend fun getReservations(): Flow<List<Reservation>>

    suspend fun updateReservation(reservation: Reservation): Flow<Reservation>

    suspend fun deleteReservation(reservation: Reservation): Int
}

class SimpleReservationDatabase: ReservationDatabase {
    private var _store = MutableStateFlow(emptyList<Reservation>())

    override suspend fun createReservation(reservation: Reservation): Flow<Reservation> {
        withContext(Dispatchers.IO) {
            _store.update {
                it + reservation
            }
        }

        return flow {
            emit(reservation)
        }
    }

    override suspend fun getReservation(id: UUID): Flow<Reservation> {
        return _store.map { reservations ->
            reservations.find { it.id == id } ?: throw NoSuchElementException("Reservation not found")
        }
    }

    override suspend fun getReservations(): Flow<List<Reservation>> {
        return _store
    }

    override suspend fun updateReservation(reservation: Reservation): Flow<Reservation> {
        withContext(Dispatchers.IO) {
            _store.update { reservations ->
                reservations.map {
                    if (it.id == reservation.id) {
                        reservation
                    } else {
                        it
                    }
                }
            }
        }

        return flow {
            emit(reservation)
        }
    }

    override suspend fun deleteReservation(reservation: Reservation): Int {
        return withContext(Dispatchers.IO) {
            _store.update { reservations ->
                reservations.filter { it.id != reservation.id }
            }

            1
        }
    }
}
