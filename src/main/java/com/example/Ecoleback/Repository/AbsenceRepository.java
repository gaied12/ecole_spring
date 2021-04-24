package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence,Long> {
    List<Absence>findAllBySonIdOrderByDateDesc(String id);
    List<Absence>findAllBySonIdAndDateOrderByIdDesc(String id,String date);


}
