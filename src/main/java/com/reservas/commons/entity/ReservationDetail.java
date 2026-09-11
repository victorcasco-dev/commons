package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "reservation_detail",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_reservation_detail",
                columnNames = {"reservation_id", "consultation_type_id"}
        )
)
@NoArgsConstructor
public class ReservationDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consultation_type_id", nullable = false)
    private ConsultationType consultationType;

    @Column(length = 500)
    private String comment;
}
