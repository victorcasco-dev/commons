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
        name = "detalle_reserva",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_detalle_reserva",
                columnNames = {"id_reserva", "id_tipo_consulta"}
        )
)
@NoArgsConstructor
public class DetalleReserva extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_consulta", nullable = false)
    private TipoConsulta tipoConsulta;

    @Column(length = 500)
    private String comentario;
}
