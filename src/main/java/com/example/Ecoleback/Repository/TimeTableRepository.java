package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable,Long> {
    public void deleteById(Long id);

}
