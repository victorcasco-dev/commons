package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "plantilla_mail")
@NoArgsConstructor
public class PlantillaMail extends BaseEntity {

    @Column(nullable = false, unique = true, length = 80)
    private String codigo;

    @Column(nullable = false, length = 255)
    private String asunto;

    @Column(name = "cuerpo_html", nullable = false, columnDefinition = "TEXT")
    private String cuerpoHtml;

    @Column(nullable = false)
    private boolean activa = true;
}
