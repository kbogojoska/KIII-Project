package mk.ukim.finki.kiiii.kiiiproject.service;

import mk.ukim.finki.kiiii.kiiiproject.model.Employee;
import mk.ukim.finki.kiiii.kiiiproject.model.EmployeeType;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> listAll();
    Employee findById(Long id);
    Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate);
    Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate);
    Employee delete(Long id);
    List<Employee> filter(Long skillId, Integer yearsOfService);
}
