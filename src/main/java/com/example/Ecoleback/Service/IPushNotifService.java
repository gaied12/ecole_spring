package com.example.Ecoleback.Service;

public interface IPushNotifService {
    public void  sendNotiftoallUser(String msg);
    public void sendNotifToUser(String msg,String StudId);

}
