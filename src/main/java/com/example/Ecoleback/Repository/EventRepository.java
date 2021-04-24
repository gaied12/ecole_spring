package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

}
