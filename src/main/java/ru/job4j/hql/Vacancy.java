package ru.job4j.hql;

import javax.persistence.*;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_base_id", referencedColumnName = "id")
    private VacancyBase vacancyBase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VacancyBase getVacancyBase() {
        return vacancyBase;
    }

    public void setVacancyBase(VacancyBase vacancyBase) {
        this.vacancyBase = vacancyBase;
    }

    @Override
    public String toString() {
        return name;
    }
}
