package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
