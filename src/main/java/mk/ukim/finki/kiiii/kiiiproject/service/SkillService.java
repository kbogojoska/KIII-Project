package mk.ukim.finki.kiiii.kiiiproject.service;

import mk.ukim.finki.kiiii.kiiiproject.model.Skill;
import java.util.List;

public interface SkillService {
    Skill findById(Long id);
    List<Skill> listAll();
    Skill create(String name);
}
