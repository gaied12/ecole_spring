package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.Level;
import com.example.Ecoleback.Model.Prof;
import com.example.Ecoleback.Model.TimeTable;
import com.example.Ecoleback.Repository.FileRepository;
import com.example.Ecoleback.Repository.LevelRepository;
import com.example.Ecoleback.Repository.ProfRepository;
import com.example.Ecoleback.Repository.TimeTableRepository;
import com.example.Ecoleback.Util.Ttable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class TtableService implements ItimeTableService {
    @Autowired
    FileRepository fileRepository ;
    @Autowired
    TimeTableRepository tableRepository;
    @Autowired
    IFileSytemStorage fileSytemStorage ;
    @Autowired
    LevelRepository levelRepository ;
    @Autowired
    ProfRepository profRepository;


    @Override
    public TimeTable addTtable(MultipartFile multipartFile,String idLevel,String title,String desc) {
        File file= fileSytemStorage.saveFile(multipartFile);
        Level level=null;

        Optional<Level> levell=levelRepository.findById(Long.valueOf(idLevel));

     level=levell.get();
     TimeTable timeTable=new TimeTable() ;

     timeTable.setLevel(level);
     timeTable.setTitle(title);
       timeTable.setDesc(desc);
       timeTable.setFile(file);

      timeTable= tableRepository.save(timeTable) ;
      level.setTimeTable(timeTable);
      levelRepository.save(level);
      return timeTable;



    }

    @Override
    public Prof addTtableToUser(MultipartFile multipartFile, String idProf, String title, String desc) {
        File file= fileSytemStorage.saveFile(multipartFile);
        Prof prof=null;
        Optional<Prof>prof1=profRepository.findById(Long.valueOf(idProf));
        prof=prof1.get();
        TimeTable timeTable=new TimeTable() ;
        timeTable.setTitle(title);
        timeTable.setDesc(desc);
        timeTable.setFile(file);


        timeTable= tableRepository.save(timeTable) ;
        prof.setTimeTable(timeTable);
        profRepository.save(prof);
        return prof;

    }

    @Override
    public void delTtable(Long id) {

      Optional<Level> level= levelRepository.findByTimeTableId(id);
long x=level.get().getTimeTable().getId();
        level.get().setTimeTable(null);
        Level Llevel=level.get();
        levelRepository.save(Llevel);

        tableRepository.deleteById(id);



    }

    @Override
    public TimeTable getTtable(Long id) {
        return null;
    }

    @Override
    public String OwnerTtable(Long id) {
        String type="";
        Optional<Prof>prof=profRepository.findByTimeTableId(id);
        Optional<Level>level=levelRepository.findByTimeTableId(id);
        if (prof.isPresent()){
            type="ENSEIGNANT";

        }
        if (level.isPresent()){
            type="CLASSE";

        }
        return type;



    }
}
