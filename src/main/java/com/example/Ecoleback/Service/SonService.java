package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.*;
import com.example.Ecoleback.Repository.*;
import com.example.Ecoleback.Util.SonU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SonService implements ISonService {
    public static Integer student=1 ;

    @Autowired
    SonRepository sonRepository ;
    @Autowired
    LevelRepository levelRepository ;
    @Autowired
    AbsenceRepository absenceRepository;
    @Autowired
    CodeRepository codeRepository;
    @Autowired
    MettingRepository mettingRepository;
    @Autowired
    SanctionRepository sanctionRepository ;


    @Override
    public Son addSon(SonU sonU) {
Level level=null ;
        Son son=new Son();
       Optional<Level> level1=levelRepository.findByName(sonU.getAclass());

         level= level1.get();
          level.setNumStud(level.getNumStud()+1);




       son.setLevel(level);
       son.setSexe(sonU.getSexe());

        son.setFirstName(sonU.getFirstName());
        son.setDateBirth(sonU.getDateBirth());
        son.setLastName(sonU.getLastName());
        son.setImageUser(null);
        return  sonRepository.save(son);




    }
    public void deletStud(String id){
        Optional<Son> sonOptional=sonRepository.findById(id);
      List<Absence>absenceList=absenceRepository.findAllBySonId(id);
        if (absenceList.size()!=0){
            for(Absence s : absenceList){
                s.getId();
                absenceRepository.delete(s);

            }


        }
Optional<Code>codeOptional=codeRepository.findCodeBySonId(id);
if (codeOptional.isPresent()){
    codeOptional.get().setSon(null);
    codeRepository.delete(codeOptional.get());



}
List<Metting>mettingList=mettingRepository.findAllBySonId(id);
        if (mettingList.size()!=0){
            for(Metting m : mettingList){
                mettingRepository.deleteById(m.getId());

            }


        }
        List<Sanction>sanctions=sanctionRepository.findAllBySonId(id);
        if (sanctions.size()!=0){
            for(Sanction s : sanctions){
                s.setType(null);
               sanctionRepository.deleteById(s.getId());

            }


        }

        Son son=sonOptional.get();

       Level level= son.getLevel();
       level.setNumStud(level.getNumStud()-1);
       levelRepository.save(level);
       son.setUser(null);





        sonRepository.delete(son);

    }

    @Override
    public Long levelId(String idStud) {
     Optional<Son>son=   sonRepository.findById(idStud);
     Long levelId=son.get().getLevel().getId();
     return levelId ;

    }

    @Override
    public Son updStud(String id, SonU sonU) {
        Son son=null;
        Optional<Son>sonOptional=sonRepository.findById(id);
        son=sonOptional.get();
        son.setSexe(sonU.getSexe());
        Optional<Level>levelOptional=levelRepository.findByName(sonU.getAclass());
        levelOptional.get().setNumStud(levelOptional.get().getNumStud()+1);
       Level level= levelRepository.save(levelOptional.get());
        son.setLevel(level);
       son.setDateBirth(sonU.getDateBirth());
       son.setLastName(sonU.getLastName());
       son.setFirstName(sonU.getFirstName());
       son=sonRepository.save(son);
       return son ;








    }

    @Override
    public List<Son> allSonbyParent(Long id) {
        return sonRepository.findAllByUserId(id);
    }

}
