package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Absence;
import com.example.Ecoleback.Repository.AbsenceRepository;
import com.example.Ecoleback.Service.AbsenceService;
import com.example.Ecoleback.Service.IAbsenceService;
import com.example.Ecoleback.Util.AbsU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class AbsenceController {
    @Autowired IAbsenceService service;
    @RequestMapping(path = "add/Abs",method = RequestMethod.POST)
    public Absence add(@RequestBody AbsU absU) throws ParseException {


        return service.addAbs(absU);

    }
    @RequestMapping(path = "all/Abs/{id}",method = RequestMethod.GET)
    public List <Absence> allAbs(@PathVariable  String id){
        return service.allAbs(id);
    }
    @RequestMapping(path = "all/AbsByDate",method = RequestMethod.GET)
    public List <Absence> allAbsByDate(@RequestParam  String id,@RequestParam  String date){
        return service.allAbsbyDate(id,date);
    }



}
