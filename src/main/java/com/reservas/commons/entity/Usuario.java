package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING, length = 32)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public abstract class Usuario extends BaseEntity {

    @Column(nullable = false, unique = true, length = 255)
    @ToString.Include
    private String email;

    @ToString.Exclude
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 100)
    @ToString.Include
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false)
    private boolean activo = true;

    public abstract TipoUsuario getTipo();

    public abstract boolean puedeReservar();

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
