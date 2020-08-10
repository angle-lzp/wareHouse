package com.angle.domain;

import java.io.Serializable;

public class Courier implements Serializable {

    private Integer id;//配送员ID
    private Integer tag;//1:管理员；2：配送员
    private String username;//配送员名字
    private String password;//密码
    private String appPassword;
    private String telephone;//联系方式
    private String address;//住址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", tag=" + tag +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", appPassword='" + appPassword + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
