package com.aryan.bluetag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    private final String profileupic;
    String profile,mail,username,password,userid,lastmessege,status;


    public user(String userid, String username, String mail, String password, String profileupic, String status){
        this.userid = userid;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.profileupic = profileupic;
        this.status = status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastmessege() {
        return lastmessege;
    }

    public void setLastmessege(String lastmessege) {
        this.lastmessege = lastmessege;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}