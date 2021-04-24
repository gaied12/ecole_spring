package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Util.SonU;

import java.util.List;

public interface ISonService {
    public Son addSon(SonU sonU);
    public void deletStud(String id) ;
    public Long levelId(String idStud );
    public Son updStud(String id,SonU sonU);
    public List <Son>allSonbyParent(Long id);


}
