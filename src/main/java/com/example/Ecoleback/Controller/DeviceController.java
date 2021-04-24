package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.Device;
import com.example.Ecoleback.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class DeviceController {
    @Autowired
    DeviceRepository deviceRepository;
    @RequestMapping(value = "/add/Device",method = RequestMethod.POST)
    public Device addDevice(@RequestParam  String keyDevice){

        Device device=new Device();
        device.setKey(keyDevice);
        return  deviceRepository.save(device);

    }

}
