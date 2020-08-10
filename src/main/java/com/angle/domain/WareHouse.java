package com.angle.domain;

import java.io.Serializable;

public class WareHouse implements Serializable {

    private Integer id;//仓库ID主键
    private String onCarOrder;//装车顺序
    private String line;//线路
    private String codeNum;//编码
    private String username;//客户名称
    private String address;//该客户的地址
    private int cid;//配送员ID
    private int number;//件数
    private Courier courier;//该客户对应的配送员
    private String imgUrl;//上传照片的长度
    private Integer flagLonLat;//标注是否已经规划到了最短路径中

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOnCarOrder() {
        return onCarOrder;
    }

    public void setOnCarOrder(String onCarOrder) {
        this.onCarOrder = onCarOrder;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getFlagLonLat() {
        return flagLonLat;
    }

    public void setFlagLonLat(Integer flagLonLat) {
        this.flagLonLat = flagLonLat;
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", onCarOrder='" + onCarOrder + '\'' +
                ", line='" + line + '\'' +
                ", codeNum='" + codeNum + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", cid=" + cid +
                ", number=" + number +
                ", courier=" + courier +
                ", imgUrl='" + imgUrl + '\'' +
                ", flagLonLat=" + flagLonLat +
                '}';
    }
}
