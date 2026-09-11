package com.reservas.commons.entity;

import com.reservas.commons.enums.UserType;
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
@Table(name = "administrator")
@DiscriminatorValue("ADMINISTRATOR")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Administrator extends User {

    @Column(length = 150)
    private String position;

    @Override
    public UserType getType() {
        return UserType.ADMINISTRATOR;
    }

    @Override
    public boolean canReserve() {
        return false;
    }
}
