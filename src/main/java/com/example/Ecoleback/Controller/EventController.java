package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Event;
import com.example.Ecoleback.Service.IEventService;
import com.example.Ecoleback.Util.EventU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EventController {
    @Autowired
    IEventService iEventService ;

    @RequestMapping(value = "/add/Event",method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public Event addEvent(@RequestParam MultipartFile multipartFile, @ModelAttribute EventU eventU) throws Exception{
        return iEventService.addEvent(eventU,multipartFile);


    }

}
