package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotesRepository extends JpaRepository<Event,Long> {
    List <Event>findAllByLevelIdOrderByIdDesc(Long id);
    public void deleteById(Long id);
    List <Event>findAllByDate(String date);
    @Query(value = "SELECT * FROM event ORDER BY date DESC ",  nativeQuery = true)
    List<Event>findAllDate();
    List<Event>findAllByTypeOrderByIdDesc(String type);


}
