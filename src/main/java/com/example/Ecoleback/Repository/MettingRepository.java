package com.example.Ecoleback.Repository;

import com.example.Ecoleback.Model.MeetState;
import com.example.Ecoleback.Model.Metting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MettingRepository extends JpaRepository<Metting,Long> {
    List<Metting>findAllByLevelIdAndProfId(Long levelId,Long Prof);
    List<Metting>findAllByProfIdAndState(Long profId, MeetState meetState);

}
