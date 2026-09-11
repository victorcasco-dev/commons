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
@Table(name = "setting")
@NoArgsConstructor
public class Setting extends BaseEntity {

    @Column(name = "setting_key", nullable = false, unique = true, length = 100)
    private String settingKey;

    @Column(nullable = false, length = 500)
    private String value;

    @Column(length = 255)
    private String description;
}
