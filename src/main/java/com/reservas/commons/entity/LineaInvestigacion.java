package com.reservas.commons.entity;

import com.reservas.commons.entity.base.Catalogo;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "linea_investigacion")
@NoArgsConstructor
public class LineaInvestigacion extends Catalogo {
}
