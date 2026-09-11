package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.TimeSlotStatus;
import com.reservas.commons.exception.InvalidOperationException;
import com.reservas.commons.exception.TimeSlotUnavailableException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(
        name = "time_slot",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_time_slot_professor_date_start",
                columnNames = {"professor_id", "slot_date", "start_time"}
        )
)
@NoArgsConstructor
public class TimeSlot extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(name = "slot_date", nullable = false)
    private LocalDate slotDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private TimeSlotStatus status = TimeSlotStatus.AVAILABLE;

    public void book() {
        if (status != TimeSlotStatus.AVAILABLE) {
            throw new TimeSlotUnavailableException("The time slot is not available");
        }
        status = TimeSlotStatus.BOOKED;
    }

    public void release() {
        if (status == TimeSlotStatus.CANCELLED) {
            throw new InvalidOperationException("A cancelled time slot cannot be released");
        }
        status = TimeSlotStatus.AVAILABLE;
    }

    public void cancel() {
        if (status == TimeSlotStatus.BOOKED) {
            throw new InvalidOperationException("A booked time slot cannot be cancelled");
        }
        status = TimeSlotStatus.CANCELLED;
    }

    public boolean isAvailable() {
        return status == TimeSlotStatus.AVAILABLE;
    }
}
