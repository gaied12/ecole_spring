package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.Absence;
import com.example.Ecoleback.Util.AbsU;

import java.text.ParseException;
import java.util.List;

public interface IAbsenceService {
    public Absence addAbs(AbsU absU) throws ParseException;
    public List<Absence>allAbs(String idStud);
    public  List<Absence>allAbsbyDate(String id,String date);
}
