package com.example.Ecoleback.Model;

import net.bytebuddy.utility.RandomString;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerator;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class CustomIdentifierGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        int year = Calendar.getInstance().get(Calendar.YEAR);
       return year+"_"+ RandomString.make(3);
    }
}
