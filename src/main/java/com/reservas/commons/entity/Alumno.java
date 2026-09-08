package com.reservas.commons.entity;

import com.reservas.commons.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alumno")
@DiscriminatorValue("ALUMNO")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Alumno extends Usuario {

    @Column(nullable = false, unique = true, length = 32)
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor_tutor")
    private Profesor tutor;

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.ALUMNO;
    }

    @Override
    public boolean puedeReservar() {
        return isActivo();
    }
}
