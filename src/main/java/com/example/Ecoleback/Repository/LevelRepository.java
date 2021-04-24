package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level,Long> {

    Optional<Level> findByName(String name);
    Optional<Level> findByTimeTableId(Long id);



}
