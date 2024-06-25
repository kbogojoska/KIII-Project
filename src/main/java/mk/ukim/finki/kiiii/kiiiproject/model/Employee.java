package mk.ukim.finki.kiiii.kiiiproject.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
public class Employee {

    public Employee() {
    }

    public Employee(String name, String email, String password, EmployeeType type, List<Skill> skills, LocalDate employmentDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.skills = skills;
        this.employmentDate = employmentDate;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate employmentDate;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skills;
}
