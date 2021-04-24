package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.TimeTable;
import com.example.Ecoleback.Repository.TimeTableRepository;
import com.example.Ecoleback.Service.TtableService;
import com.example.Ecoleback.Util.Ttable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class TtableController {
    @Autowired
    TtableService ttableService ;
    @Autowired
    TimeTableRepository tableRepository;
    @RequestMapping( value= "add/ttable",method = RequestMethod.POST)

    public TimeTable addTtable(@RequestParam ("file") MultipartFile file, @RequestParam String idLevel,@RequestParam String title,@RequestParam String desc){
        return ttableService.addTtable(file,idLevel,title,desc);

    }
    @RequestMapping( value= "del/ttable/{id}",method = RequestMethod.DELETE)

    public void delTtable(@PathVariable Long id){
        ttableService.delTtable(id);
    }
    @RequestMapping( value= "gel/ttable/{id}",method = RequestMethod.GET)
    public TimeTable getTtable(@PathVariable Long id){
        TimeTable timeTable=tableRepository.findById(id).get();
        return  timeTable;

    }


}
