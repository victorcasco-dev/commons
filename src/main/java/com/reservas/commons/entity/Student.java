package com.reservas.commons.entity;

import com.reservas.commons.enums.UserType;
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
@Table(name = "student")
@DiscriminatorValue("STUDENT")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Student extends User {

    @Column(name = "student_code", nullable = false, unique = true, length = 32)
    private String studentCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_professor_id")
    private Professor tutor;

    @Override
    public UserType getType() {
        return UserType.STUDENT;
    }

    @Override
    public boolean canReserve() {
        return isActive();
    }
}
