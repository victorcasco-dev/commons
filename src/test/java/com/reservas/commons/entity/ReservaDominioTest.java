package com.reservas.commons.entity;

import com.reservas.commons.enums.EstadoFranja;
import com.reservas.commons.enums.EstadoReserva;
import com.reservas.commons.exception.HorarioNoDisponibleException;
import com.reservas.commons.exception.OperacionInvalidaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaDominioTest {

	@Test
	void crearConfirmadaOcupaLaFranja() {
		FranjaHoraria franja = franjaDisponible();
		Alumno alumno = new Alumno();

		Reserva reserva = Reserva.crearConfirmada(alumno, franja);

		assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
		assertEquals(EstadoFranja.OCUPADA, franja.getEstado());
	}

	@Test
	void noPermiteDosOcupacionesSobreLaMismaFranja() {
		FranjaHoraria franja = franjaDisponible();
		franja.ocupar();

		assertThrows(HorarioNoDisponibleException.class, franja::ocupar);
	}

	@Test
	void cancelarLiberaLaFranja() {
		FranjaHoraria franja = franjaDisponible();
		Reserva reserva = Reserva.crearConfirmada(new Alumno(), franja);

		reserva.cancelar();

		assertEquals(EstadoReserva.CANCELADA, reserva.getEstado());
		assertEquals(EstadoFranja.DISPONIBLE, franja.getEstado());
	}

	@Test
	void noSePuedeCancelarUnaReservaCompletada() {
		FranjaHoraria franja = franjaDisponible();
		Reserva reserva = Reserva.crearConfirmada(new Alumno(), franja);
		reserva.completar();

		assertThrows(OperacionInvalidaException.class, reserva::cancelar);
	}

	private static FranjaHoraria franjaDisponible() {
		FranjaHoraria franja = new FranjaHoraria();
		franja.setFecha(LocalDate.now().plusDays(1));
		franja.setHoraInicio(LocalTime.of(10, 0));
		franja.setHoraFin(LocalTime.of(11, 0));
		return franja;
	}
}
