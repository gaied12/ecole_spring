package com.example.Ecoleback.Util;

import org.springframework.web.multipart.MultipartFile;

public class Ttable {
  private static  int n=0;
    private  Long idLevel;
private String title;
    private String desc ;

    public static int getN() {
        return n;
    }

    public static void setN(int n) {
        Ttable.n = n;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(Long idLevel) {
        this.idLevel = idLevel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
