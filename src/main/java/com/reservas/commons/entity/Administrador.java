package com.reservas.commons.entity;

import com.reservas.commons.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrador")
@DiscriminatorValue("ADMINISTRADOR")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Administrador extends Usuario {

    @Column(length = 150)
    private String cargo;

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.ADMINISTRADOR;
    }

    @Override
    public boolean puedeReservar() {
        return false;
    }
}
