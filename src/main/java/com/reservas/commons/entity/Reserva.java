package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.EstadoReserva;
import com.reservas.commons.exception.OperacionInvalidaException;
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
@Table(name = "reserva")
@NoArgsConstructor
public class Reserva extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno alumno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_franja", nullable = false)
    private FranjaHoraria franja;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private EstadoReserva estado = EstadoReserva.PENDIENTE;

    @ToString.Exclude
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleReserva> detalles = new ArrayList<>();

    public static Reserva crearConfirmada(Alumno alumno, FranjaHoraria franja) {
        franja.ocupar();
        Reserva reserva = new Reserva();
        reserva.setAlumno(alumno);
        reserva.setFranja(franja);
        reserva.estado = EstadoReserva.CONFIRMADA;
        return reserva;
    }

    public void agregarDetalle(TipoConsulta tipoConsulta, String comentario) {
        DetalleReserva detalle = new DetalleReserva();
        detalle.setReserva(this);
        detalle.setTipoConsulta(tipoConsulta);
        detalle.setComentario(comentario);
        detalles.add(detalle);
    }

    public void confirmar() {
        if (estado != EstadoReserva.PENDIENTE) {
            throw new OperacionInvalidaException("Solo se puede confirmar una reserva pendiente");
        }
        franja.ocupar();
        estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (estado == EstadoReserva.COMPLETADA) {
            throw new OperacionInvalidaException("No se puede cancelar una reserva completada");
        }
        if (estado == EstadoReserva.CANCELADA) {
            return;
        }
        estado = EstadoReserva.CANCELADA;
        if (franja != null && franja.getEstado() != null) {
            franja.liberar();
        }
    }

    public void completar() {
        if (estado != EstadoReserva.CONFIRMADA) {
            throw new OperacionInvalidaException("Solo se puede completar una reserva confirmada");
        }
        estado = EstadoReserva.COMPLETADA;
    }
}
