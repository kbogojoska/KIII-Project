package mk.ukim.finki.kiiii.kiiiproject.web;

import mk.ukim.finki.kiiii.kiiiproject.model.Employee;
import mk.ukim.finki.kiiii.kiiiproject.model.EmployeeType;
import mk.ukim.finki.kiiii.kiiiproject.service.EmployeeService;
import mk.ukim.finki.kiiii.kiiiproject.service.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService service;
    private final SkillService skillService;

    public EmployeeController(EmployeeService service, SkillService skillService) {
        this.service = service;
        this.skillService = skillService;
    }

    @GetMapping(value = {"/", "/employees"})
    public String showList(@RequestParam(required = false) Long skillId,
                           @RequestParam(required = false) Integer yearsOfService,
                           Model model) {
        List<Employee> employees;
        if (skillId == null && yearsOfService == null) {
            employees = this.service.listAll();
        } else {
            employees = this.service.filter(skillId, yearsOfService);
        }
        model.addAttribute("employees", employees);
        model.addAttribute("skills", skillService.listAll());
        return "list";
    }

    @GetMapping("/employees/add")
    public String showAdd(Model model) {
        model.addAttribute("skills", skillService.listAll());
        model.addAttribute("types", EmployeeType.values());
        return "form";
    }

    @GetMapping("/employees/{id}/edit")
    public String showEdit(@PathVariable Long id,
                           Model model) {
        model.addAttribute("employee", service.findById(id));
        model.addAttribute("skills", skillService.listAll());
        model.addAttribute("types", EmployeeType.values());
        return "form";
    }

    @PostMapping("/employees")
    public String create(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam EmployeeType type,
                         @RequestParam List<Long> skillId,
                         @RequestParam LocalDate employmentDate) {
        this.service.create(name, email, password, type, skillId, employmentDate);
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam EmployeeType type,
                         @RequestParam List<Long> skillId,
                         @RequestParam LocalDate employmentDate) {
        this.service.update(id, name, email, password, type, skillId, employmentDate);
        return "redirect:/employees";
    }

    @PostMapping("/employees/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/employees";
    }
}