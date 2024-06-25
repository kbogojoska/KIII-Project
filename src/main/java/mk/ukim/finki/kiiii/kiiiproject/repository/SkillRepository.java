package mk.ukim.finki.kiiii.kiiiproject.repository;

import mk.ukim.finki.kiiii.kiiiproject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
