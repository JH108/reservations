package me.jessehill.controllers

import me.jessehill.database.ReservationDatabase
import me.jessehill.network.dto.ReservationDto
import me.jessehill.network.dto.toReservation

interface ReservationController {
    suspend fun createReservation(reservation: ReservationDto)
}

class SimpleReservationController(
    private val database: ReservationDatabase
) : ReservationController {
    override suspend fun createReservation(reservation: ReservationDto) {
        database.createReservation(reservation.toReservation())
    }
}
