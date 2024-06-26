package mk.ukim.finki.kiiii.kiiiproject.service.impl;

import mk.ukim.finki.kiiii.kiiiproject.model.Employee;
import mk.ukim.finki.kiiii.kiiiproject.model.EmployeeType;
import mk.ukim.finki.kiiii.kiiiproject.model.Skill;
import mk.ukim.finki.kiiii.kiiiproject.model.exceptions.InvalidSkillIdException;
import mk.ukim.finki.kiiii.kiiiproject.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.kiiii.kiiiproject.repository.EmployeeRepository;
import mk.ukim.finki.kiiii.kiiiproject.service.EmployeeService;
import mk.ukim.finki.kiiii.kiiiproject.service.SkillService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    private final EmployeeRepository employeeRepository;
    private final SkillService skillService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillService skillService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.skillService = skillService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        return employeeRepository.save(new Employee(name,email,password,type,skillId.stream().map(skillService::findById).collect(Collectors.toList()),employmentDate));
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = findById(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setType(type);
        employee.setSkills(skillId.stream().map(skillService::findById).collect(Collectors.toList()));
        employee.setEmploymentDate(employmentDate);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = findById(id);
        employeeRepository.deleteById(id);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if (skillId == null && yearsOfService == null) {
            return employeeRepository.findAll();
        } else if (yearsOfService == null) {
            Skill skill = skillService.findById(skillId);
            return employeeRepository.findEmployeesBySkillsContaining(skill);
        } else if (skillId == null) {
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            return employeeRepository.findEmployeesByEmploymentDateBefore(employmentBefore);
        } else {
            Skill skill = skillService.findById(skillId);
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            return employeeRepository.findEmployeesBySkillsContainingAndEmploymentDateBefore(skill, employmentBefore);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(username);
        return new User(
                employee.getEmail(),
                passwordEncoder.encode(employee.getPassword()),
                Collections.singleton(employee.getType())
        );
    }
}
