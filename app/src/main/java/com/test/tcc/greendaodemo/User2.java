package com.test.tcc.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User2 {
    @Id(autoincrement = true)
    private Long id;
    private String userName;
    private String passWord;
    private String lv;
    private String address;
    @Generated(hash = 1055359901)
    public User2(Long id, String userName, String passWord, String lv,
            String address) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.lv = lv;
        this.address = address;
    }
    @Generated(hash = 377798761)
    public User2() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getLv() {
        return this.lv;
    }
    public void setLv(String lv) {
        this.lv = lv;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
