package com.example.Ecoleback.Service;

import com.example.Ecoleback.Exception.AppException;
import com.example.Ecoleback.Model.File;
import com.example.Ecoleback.Repository.FileRepository;
import com.example.Ecoleback.Util.FileUploadProperties;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements  IFileSytemStorage {
    @Value("${file.upload.location}")
    private Path dirLocation;
    @Autowired
    FileRepository fileRepository ;



    @Override
    public File saveFile(MultipartFile file) {
        try {
          Path path=  this.dirLocation.getRoot();
            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(fileName);
                Files.copy(file.getInputStream(), dfile.toAbsolutePath(),StandardCopyOption.REPLACE_EXISTING);

          String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path("/files/"+fileName)
                .toUriString();
            File file1=new File();
            file1.setType(file.getContentType());
          file1.setFileUrl(fileDownloadUri);
            file1.setName(fileName);
          return   fileRepository.save(file1);


        } catch (Exception e) {
            throw new AppException("Could not upload file","99");
        }
    }

    @Override
    public Resource loadFile(String fileName) throws MalformedURLException {
        Path path=dirLocation.resolve(fileName );
        Resource resource = new UrlResource(path.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }

    }


}
