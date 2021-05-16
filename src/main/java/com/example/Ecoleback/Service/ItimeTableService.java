package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Model.Prof;
import com.example.Ecoleback.Model.TimeTable;
import com.example.Ecoleback.Util.Ttable;
import org.springframework.web.multipart.MultipartFile;

public interface ItimeTableService {
    public TimeTable addTtable(MultipartFile multipartFile,String idLevel,String title,String desc);
    public Prof addTtableToUser(MultipartFile multipartFile, String idProf, String title, String desc);
    public void delTtable(Long id);
    public TimeTable getTtable(Long id );
    public String OwnerTtable(Long id);
}
