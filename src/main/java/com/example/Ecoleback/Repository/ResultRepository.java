package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    List<Result>findAllBySonId(String studId);
}
