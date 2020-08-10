package com.angle.test;

import com.angle.domain.WareHouse;
import com.angle.utils.MapPathUtils;

import java.util.ArrayList;
import java.util.List;

public class DistanceTest {
    public static void main(String[] args) {
        List<WareHouse> list = new ArrayList<>();
        WareHouse wareHouse1 = new WareHouse();
        WareHouse wareHouse2 = new WareHouse();
        WareHouse wareHouse3 = new WareHouse();
        WareHouse wareHouse4 = new WareHouse();
        WareHouse wareHouse5 = new WareHouse();
        WareHouse wareHouse6 = new WareHouse();
        wareHouse1.setAddress("农场金瑞路87号");
        wareHouse1.setImgUrl("lucy");
        wareHouse2.setAddress("三亚市金鸡岭社区东四巷02号");
        //wareHouse2.setImgUrl("lucy");
        wareHouse3.setAddress("儋州市东成镇长坡长胜一街079号");
        //wareHouse3.setImgUrl("lucy");
        wareHouse4.setAddress("海南省澄迈县金安农场金瑞路87号");
        //wareHouse4.setImgUrl("lucy");
        wareHouse5.setAddress("海南省澄迈");
        //wareHouse5.setImgUrl("lucy");
        wareHouse6.setAddress("天门广场");
        //wareHouse6.setImgUrl("lucy");
        list.add(wareHouse1);
        list.add(wareHouse2);
        list.add(wareHouse3);
        list.add(wareHouse4);
        list.add(wareHouse5);
        list.add(wareHouse6);
        List<WareHouse> wareHouseList = MapPathUtils.getShortestPath("110.384755,18.797036", list);
        for (WareHouse wareHouse : wareHouseList) {
            System.out.println(wareHouse);
        }
    }
}
