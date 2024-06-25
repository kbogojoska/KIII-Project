package mk.ukim.finki.kiiii.kiiiproject.repository;

import mk.ukim.finki.kiiii.kiiiproject.model.Employee;
import mk.ukim.finki.kiiii.kiiiproject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByEmploymentDateBefore(LocalDate date);
    List<Employee> findEmployeesBySkillsContaining(Skill skill);
    List<Employee> findEmployeesBySkillsContainingAndEmploymentDateBefore(Skill skill, LocalDate date);

    Employee findByEmail(String username);
}
