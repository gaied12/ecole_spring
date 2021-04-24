package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Code;
import com.example.Ecoleback.Model.Son;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code,Long> {
    Optional<Son>findBySonId(String id);
Optional<Code>findCodeBySonId(String id);
}
