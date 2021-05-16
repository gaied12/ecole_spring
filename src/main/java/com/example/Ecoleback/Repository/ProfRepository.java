package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Prof;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfRepository extends JpaRepository<Prof,Long> {
    List<Prof>findAllBySubjectId(Long id);
    List<Prof>findAllByLevelId(Long id);
    Optional<Prof>findByTimeTableId(Long Prof);


}
