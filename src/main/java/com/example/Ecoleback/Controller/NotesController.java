package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.Level;
import com.example.Ecoleback.Model.NotifInfo;
import com.example.Ecoleback.Model.imageUser;
import com.example.Ecoleback.Repository.FileRepository;
import com.example.Ecoleback.Repository.ImageRepository;
import com.example.Ecoleback.Repository.LevelRepository;
import com.example.Ecoleback.Repository.NotesRepository;
import com.example.Ecoleback.Service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class NotesController {
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    LevelRepository levelRepository;
    @Autowired
    FileSystemStorageService fileSystemStorageService;
    @Autowired
    ImageRepository imageRepository;


    @RequestMapping(value = "add/notes",method = RequestMethod.POST)


    public void addNotes( @RequestParam(required = false) MultipartFile file, @RequestParam String title, @RequestParam String des, @RequestParam String type, @RequestParam(required = false) ArrayList<String>levelsId) throws IOException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String Date = simpleDateFormat.format(new Date());


        if (type.contentEquals("c")) {

            for (int i = 0; i < levelsId.size(); i++) {
                NotifInfo note = new NotifInfo();


                Optional<Level> level = levelRepository.findById(Long.valueOf(levelsId.get(i)));
                note.setLevel(level.get());

                note.setLevel(level.get());
                if (file == null) {
                    note.setFile(null);

                } else {
                    File file1 = fileSystemStorageService.saveFile(file);
                    note.setFile(file1);


                }


                note.setTitle(title);

                note.setDateCreation(new Date());
                note.setDate(Date);
                note.setDescription(des);
                note.setType("Communiqué");
                notesRepository.save(note);


            }
        }

            if (type.contentEquals("e")){
                imageUser image =new imageUser();
                image.setType(file.getContentType());
                image.setName(file.getOriginalFilename());
                image.setPicByte(file.getBytes());
                NotifInfo note=new NotifInfo();
                note.setDateCreation(new Date());
                note.setDate(Date);


                note.setImageUser(  imageRepository.save(image));
                note.setFile(null);
                note.setTitle(title);
                note.setDescription(des);
                note.setType("Evénement");
                note.setLevel(null);
                notesRepository.save(note);


            }






       /* if(type.contentEquals("e")){

            File file1= fileSystemStorageService.saveFile(file);

            note.setLevel(level.get());
            note.setFile(file1);
            note.setTitle(title);
            note.setDescription(des);



        }*/



    }
    @RequestMapping(value = "get/note/level/{idLevel}",method = RequestMethod.GET)
    public List<NotifInfo>getNotesByLevel(@PathVariable Long idLevel){
        return notesRepository.findAllByLevelIdOrderByIdDesc(idLevel);

    }
    @RequestMapping(value = "/note/{id}",method = RequestMethod.GET)
    public NotifInfo getNote(@PathVariable Long id){
        return notesRepository.findById(id).get();

    }
    @RequestMapping(value = "delete/note/{id}",method = RequestMethod.DELETE)
    public  void deleteNote(@PathVariable Long id){
        notesRepository.deleteById(id);
    }
    @RequestMapping(value = "update/note/{id}",method = RequestMethod.PUT)
    public NotifInfo updateNote(@PathVariable Long id,@RequestParam (required = false) MultipartFile file,@RequestParam String title,@RequestParam String desc ) throws IOException {
       NotifInfo notifInfo =notesRepository.findById(id).get();
        Date date=new Date();

        notifInfo.setDateCreation(date);
       notifInfo.setTitle(title);
       notifInfo.setDescription(desc);
if(notifInfo.getType().equals("Evénement")){
    if (file==null){
        notifInfo.setImageUser(null);
    }
    else{
        imageUser image =new imageUser();
        image.setType(file.getContentType());
        image.setName(file.getOriginalFilename());
        image.setPicByte(file.getBytes());
        notifInfo.setImageUser(imageRepository.save(image));


    }

}
        if(notifInfo.getType().equals("Communiqué")){
      if (file==null){
        notifInfo.setFile(null);
    }else{

        notifInfo.setFile(fileSystemStorageService.saveFile(file));
    }

}

       return notesRepository.save(notifInfo);





    }
    @RequestMapping(value = "get/note/date",method = RequestMethod.GET)
    public List<NotifInfo>allNotes(@RequestParam String date){
        return notesRepository.findAllByDate(date);
    }



    @RequestMapping(value = "get/all/note",method = RequestMethod.GET)
    public List<NotifInfo>allNotess(){
        return notesRepository.findAllDate();
    }



    @RequestMapping(value = "all/event",method = RequestMethod.GET)
    public List<NotifInfo>allEvent(){
String type="Evénement";
        return notesRepository.findAllByTypeOrderByIdDesc(type);
    }



}
