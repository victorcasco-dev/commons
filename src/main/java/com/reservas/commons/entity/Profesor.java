package com.reservas.commons.entity;

import com.reservas.commons.enums.NivelExperiencia;
import com.reservas.commons.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profesor")
@DiscriminatorValue("PROFESOR")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Profesor extends Usuario {

    @Column(name = "titulo_academico", length = 150)
    private String tituloAcademico;

    @ToString.Exclude
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfesorLinea> lineas = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FranjaHoraria> franjas = new ArrayList<>();

    public void agregarLinea(LineaInvestigacion linea, NivelExperiencia nivel) {
        ProfesorLinea detalle = new ProfesorLinea();
        detalle.setProfesor(this);
        detalle.setLineaInvestigacion(linea);
        detalle.setNivelExperiencia(nivel);
        lineas.add(detalle);
    }

    public void quitarLinea(ProfesorLinea detalle) {
        lineas.remove(detalle);
        detalle.setProfesor(null);
    }

    @Override
    public TipoUsuario getTipo() {
        return TipoUsuario.PROFESOR;
    }

    @Override
    public boolean puedeReservar() {
        return false;
    }
}
