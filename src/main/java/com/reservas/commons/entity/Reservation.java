package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.ReservationStatus;
import com.reservas.commons.exception.InvalidOperationException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservation")
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private ReservationStatus status = ReservationStatus.PENDING;

    @ToString.Exclude
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationDetail> details = new ArrayList<>();

    public static Reservation createConfirmed(Student student, TimeSlot timeSlot) {
        timeSlot.book();
        Reservation reservation = new Reservation();
        reservation.setStudent(student);
        reservation.setTimeSlot(timeSlot);
        reservation.status = ReservationStatus.CONFIRMED;
        return reservation;
    }

    public void addDetail(ConsultationType consultationType, String comment) {
        ReservationDetail detail = new ReservationDetail();
        detail.setReservation(this);
        detail.setConsultationType(consultationType);
        detail.setComment(comment);
        details.add(detail);
    }

    public void confirm() {
        if (status != ReservationStatus.PENDING) {
            throw new InvalidOperationException("Only a pending reservation can be confirmed");
        }
        timeSlot.book();
        status = ReservationStatus.CONFIRMED;
    }

    public void cancel() {
        if (status == ReservationStatus.COMPLETED) {
            throw new InvalidOperationException("A completed reservation cannot be cancelled");
        }
        if (status == ReservationStatus.CANCELLED) {
            return;
        }
        status = ReservationStatus.CANCELLED;
        if (timeSlot != null && timeSlot.getStatus() != null) {
            timeSlot.release();
        }
    }

    public void complete() {
        if (status != ReservationStatus.CONFIRMED) {
            throw new InvalidOperationException("Only a confirmed reservation can be completed");
        }
        status = ReservationStatus.COMPLETED;
    }
}
