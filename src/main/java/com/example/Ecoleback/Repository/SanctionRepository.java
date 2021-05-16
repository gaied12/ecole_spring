package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SanctionRepository extends JpaRepository<Sanction,Long> {
    List <Sanction>findAllBySonId(String id);
}
