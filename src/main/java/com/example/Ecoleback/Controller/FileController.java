package com.example.Ecoleback.Controller;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.TimeTable;
import com.example.Ecoleback.Service.IFileSytemStorage;
import com.example.Ecoleback.Service.TtableService;
import com.example.Ecoleback.Util.Ttable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)

public class FileController {
    @Autowired
    IFileSytemStorage fileSytemStorage ;
    @Autowired
    TtableService ttableService ;
    @RequestMapping(value = "/add/file",method = RequestMethod.POST)
    public File addFile(@RequestParam MultipartFile multipartFile){
        return  fileSytemStorage.saveFile(multipartFile);

    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
        Resource file = fileSytemStorage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }



}
