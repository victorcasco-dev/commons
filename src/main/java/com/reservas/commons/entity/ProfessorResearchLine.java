package com.reservas.commons.entity;

import com.reservas.commons.entity.base.BaseEntity;
import com.reservas.commons.enums.ExperienceLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "professor_research_line",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_professor_research_line",
                columnNames = {"professor_id", "research_line_id"}
        )
)
@NoArgsConstructor
public class ProfessorResearchLine extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "research_line_id", nullable = false)
    private ResearchLine researchLine;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience_level", nullable = false, length = 32)
    private ExperienceLevel experienceLevel = ExperienceLevel.BASIC;
}
