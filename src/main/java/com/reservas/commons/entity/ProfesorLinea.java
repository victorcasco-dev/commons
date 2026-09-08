package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.NivelExperiencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
        name = "profesor_linea",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_profesor_linea",
                columnNames = {"id_profesor", "id_linea_investigacion"}
        )
)
@NoArgsConstructor
public class ProfesorLinea extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_linea_investigacion", nullable = false)
    private LineaInvestigacion lineaInvestigacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_experiencia", nullable = false, length = 32)
    private NivelExperiencia nivelExperiencia = NivelExperiencia.BASICO;
}
