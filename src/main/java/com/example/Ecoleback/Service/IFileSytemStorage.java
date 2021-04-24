package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.File;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileSytemStorage  {
    File saveFile(MultipartFile file);
    Resource loadFile(String fileName) throws Exception;
}
