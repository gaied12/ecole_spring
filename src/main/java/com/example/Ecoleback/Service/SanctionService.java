package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.SancType;
import com.example.Ecoleback.Model.Sanction;
import com.example.Ecoleback.Model.Son;
import com.example.Ecoleback.Repository.SancRepository;
import com.example.Ecoleback.Repository.SanctionRepository;
import com.example.Ecoleback.Repository.SonRepository;
import com.example.Ecoleback.Util.SanctionU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SanctionService implements ISanctionService {
    @Autowired
    SancRepository sancRepository;
    @Autowired
    SanctionRepository sanctionRepository;
    @Autowired
    SonRepository sonRepository;
    @Autowired
    IPushNotifService pushNotifService;
    @Override
    public Sanction addSanction(SanctionU sanctionU) {
        Sanction sanction=new Sanction();
        sanction.setDate(new Date());
        Optional<SancType>sancType=sancRepository.findById(sanctionU.getIdSacntion());
        sanction.setType(sancType.get());
        Optional<Son>son=sonRepository.findById(sanctionU.getMatStud());
        sanction.setSon(son.get());
        sanction.setDescription(sanctionU.getDesc());
        pushNotifService.sendNotifToUser("votre fils a reçu une nouvelle sanction disciplinaire",sanctionU.getMatStud());

        return sanctionRepository.save(sanction);


    }
}
