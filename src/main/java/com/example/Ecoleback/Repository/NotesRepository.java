package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.NotifInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface NotesRepository extends JpaRepository<NotifInfo,Long> {
    List <NotifInfo>findAllByLevelIdOrderByIdDesc(Long id);
    public void deleteById(Long id);
    List <NotifInfo>findAllByDate(String date);
    @Query(value = "SELECT * FROM notif_info ORDER BY date DESC ",  nativeQuery = true)
    List<NotifInfo>findAllDate();
    List<NotifInfo>findAllByTypeOrderByIdDesc(String type);


}
