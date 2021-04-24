package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.Event;
import com.example.Ecoleback.Model.imageUser;
import com.example.Ecoleback.Repository.EventRepository;
import com.example.Ecoleback.Repository.ImageRepository;
import com.example.Ecoleback.Util.EventU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class EventService implements IEventService {
    @Autowired
    EventRepository eventRepository ;
    @Autowired
    ImageRepository imageRepository ;
    @Override
    public Event addEvent(EventU eventU, MultipartFile multipartFile) throws IOException {
        imageUser image=new  imageUser();
        image.setName(multipartFile.getOriginalFilename());
        image.setPicByte(compressZLib(multipartFile.getBytes()));
        image.setType(multipartFile.getContentType());
      imageRepository.save(image);
       Event event=new Event();
       event.setImage(image);
       event.setDesc(eventU.getDesc());
       event.setTitle(eventU.getTitle());
       eventRepository.save(event);




        return event;
    }
    public static byte[] compressZLib(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }
    public static byte[] decompressZLib(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
