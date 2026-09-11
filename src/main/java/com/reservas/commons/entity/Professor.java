package com.reservas.commons.entity;

import com.reservas.commons.enums.ExperienceLevel;
import com.reservas.commons.enums.UserType;
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
@Table(name = "professor")
@DiscriminatorValue("PROFESSOR")
@PrimaryKeyJoinColumn(name = "id")
@NoArgsConstructor
public class Professor extends User {

    @Column(name = "academic_title", length = 150)
    private String academicTitle;

    @ToString.Exclude
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfessorResearchLine> researchLines = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public void addResearchLine(ResearchLine researchLine, ExperienceLevel level) {
        ProfessorResearchLine detail = new ProfessorResearchLine();
        detail.setProfessor(this);
        detail.setResearchLine(researchLine);
        detail.setExperienceLevel(level);
        researchLines.add(detail);
    }

    public void removeResearchLine(ProfessorResearchLine detail) {
        researchLines.remove(detail);
        detail.setProfessor(null);
    }

    @Override
    public UserType getType() {
        return UserType.PROFESSOR;
    }

    @Override
    public boolean canReserve() {
        return false;
    }
}
