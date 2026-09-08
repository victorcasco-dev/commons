package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.EstadoFranja;
import com.reservas.commons.exception.HorarioNoDisponibleException;
import com.reservas.commons.exception.OperacionInvalidaException;
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
        name = "franja_horaria",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_franja_profesor_fecha_inicio",
                columnNames = {"id_profesor", "fecha", "hora_inicio"}
        )
)
@NoArgsConstructor
public class FranjaHoraria extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private EstadoFranja estado = EstadoFranja.DISPONIBLE;

    public void ocupar() {
        if (estado != EstadoFranja.DISPONIBLE) {
            throw new HorarioNoDisponibleException("La franja horaria no está disponible");
        }
        estado = EstadoFranja.OCUPADA;
    }

    public void liberar() {
        if (estado == EstadoFranja.CANCELADA) {
            throw new OperacionInvalidaException("No se puede liberar una franja cancelada");
        }
        estado = EstadoFranja.DISPONIBLE;
    }

    public void cancelar() {
        if (estado == EstadoFranja.OCUPADA) {
            throw new OperacionInvalidaException("No se puede cancelar una franja ocupada");
        }
        estado = EstadoFranja.CANCELADA;
    }

    public boolean estaDisponible() {
        return estado == EstadoFranja.DISPONIBLE;
    }
}
