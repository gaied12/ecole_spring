package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Level;
import com.example.Ecoleback.Model.Son;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SonRepository extends JpaRepository<Son,String> {
    List<Son>findAllByLevel(Level level);
    public void deleteById(String id);
    List<Son>findAllByUserId(Long id);

}
