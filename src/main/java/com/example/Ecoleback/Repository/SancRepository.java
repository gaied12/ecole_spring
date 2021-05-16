package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Sanc;
import com.example.Ecoleback.Model.SancType;
import com.example.Ecoleback.Model.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SancRepository extends JpaRepository<SancType,Long> {
    Optional<SancType>findById(Long id);
    Optional<SancType>findByType(Sanc sanc);
}
