package com.reservas.commons.entity;

import com.reservas.commons.entity.base.Catalog;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "consultation_type")
@NoArgsConstructor
public class ConsultationType extends Catalog {
}
