package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.Event;
import com.example.Ecoleback.Util.EventU;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IEventService {
public Event addEvent(EventU eventU, MultipartFile multipartFile) throws IOException;

}
