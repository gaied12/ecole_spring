package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.Result;
import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Repository.ResultRepository;
import com.example.Ecoleback.Repository.SonRepository;
import com.example.Ecoleback.Service.IFileSytemStorage;
import com.example.Ecoleback.Service.IPushNotifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class ResultController {
    @Autowired
    IFileSytemStorage fileSytemStorage;
    @Autowired
    SonRepository sonRepository;
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    IPushNotifService pushNotifService;

    @RequestMapping(value = "/add/result",method = RequestMethod.POST)

    public Result addRes(@RequestParam MultipartFile file,@RequestParam String title,@RequestParam String studId){
        Result result=new Result();

        File file1=fileSytemStorage.saveFile(file);
        result.setFile(file1);
result.setDate(new Date());
        Optional<Son>son=sonRepository.findById(studId);
        result.setSon(son.get());
        result.setTitle(title);
        pushNotifService.sendNotifToUser("veuillez Consulter le résulat de "+ son.get().getFirstName(),son.get().getId());
        return resultRepository.save(result);




    }
    @RequestMapping(value = "/all/result/{id}",method = RequestMethod.GET)
    public List <Result> allResult(@PathVariable String id){
        return  resultRepository.findAllBySonId(id);
    }


}
