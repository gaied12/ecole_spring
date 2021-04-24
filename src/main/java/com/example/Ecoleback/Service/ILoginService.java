package com.example.Ecoleback.Service;

import com.example.Ecoleback.Model.Code;
import com.example.Ecoleback.Model.User;

public interface ILoginService {
    public Code addCode(String idStud, String email) throws Exception;

    public User authenticated(String email, String password) throws Exception;
}
