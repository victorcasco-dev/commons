package com.reservas.commons.entity;

import com.reservas.commons.enums.ReservationStatus;
import com.reservas.commons.enums.TimeSlotStatus;
import com.reservas.commons.exception.InvalidOperationException;
import com.reservas.commons.exception.TimeSlotUnavailableException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationDomainTest {

	@Test
	void createConfirmedBooksTheTimeSlot() {
		TimeSlot timeSlot = availableSlot();
		Student student = new Student();

		Reservation reservation = Reservation.createConfirmed(student, timeSlot);

		assertEquals(ReservationStatus.CONFIRMED, reservation.getStatus());
		assertEquals(TimeSlotStatus.BOOKED, timeSlot.getStatus());
	}

	@Test
	void doesNotAllowBookingTheSameSlotTwice() {
		TimeSlot timeSlot = availableSlot();
		timeSlot.book();

		assertThrows(TimeSlotUnavailableException.class, timeSlot::book);
	}

	@Test
	void cancelReleasesTheTimeSlot() {
		TimeSlot timeSlot = availableSlot();
		Reservation reservation = Reservation.createConfirmed(new Student(), timeSlot);

		reservation.cancel();

		assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
		assertEquals(TimeSlotStatus.AVAILABLE, timeSlot.getStatus());
	}

	@Test
	void completedReservationCannotBeCancelled() {
		TimeSlot timeSlot = availableSlot();
		Reservation reservation = Reservation.createConfirmed(new Student(), timeSlot);
		reservation.complete();

		assertThrows(InvalidOperationException.class, reservation::cancel);
	}

	private static TimeSlot availableSlot() {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setSlotDate(LocalDate.now().plusDays(1));
		timeSlot.setStartTime(LocalTime.of(10, 0));
		timeSlot.setEndTime(LocalTime.of(11, 0));
		return timeSlot;
	}
}
