package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.imageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<imageUser,Long> {
}
