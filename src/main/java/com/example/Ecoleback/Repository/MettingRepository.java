package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.MeetState;
import com.example.Ecoleback.Model.Metting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MettingRepository extends JpaRepository<Metting,Long> {
    List<Metting>findAllByProfIdAndState(Long profId, MeetState meetState);
    List<Metting>findAllByParentIdAndSonId(Long parentId,String studId);
    public  void deleteById(Long id);
    List<Metting>findAllBySonId(String id);



}
