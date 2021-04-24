package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.Absence;
import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Model.Subject;
import com.example.Ecoleback.Repository.AbsenceRepository;
import com.example.Ecoleback.Repository.SonRepository;
import com.example.Ecoleback.Repository.SubjectRepository;
import com.example.Ecoleback.Util.AbsU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
public class AbsenceService implements IAbsenceService {
    @Autowired
    AbsenceRepository absenceRepository;
    @Autowired
    SonRepository sonRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public Absence addAbs(AbsU absU) throws ParseException {
        Absence absence=new Absence();
        Optional<Son>son=   sonRepository.findById(absU.getIdStud());
        absence.setSon(son.get());
        absence.setDate(absU.getDateAbs());
        Optional<Subject>subject=subjectRepository.findById(absU.getIdSubj());
        absence.setSubject(subject.get());
        absence.setStart(LocalTime.of(absU.gethStart(),absU.getMstart()));
        absence.setEnd(LocalTime.of(absU.gethEnd(),absU.getMend()));
      return   absenceRepository.save(absence);




    }

    @Override
    public List<Absence> allAbs(String idStud) {
        return absenceRepository.findAllBySonIdOrderByDateDesc(idStud);

    }

    @Override
    public List<Absence> allAbsbyDate(String id, String date) {
        return absenceRepository.findAllBySonIdAndDateOrderByIdDesc(id, date);
    }
}
