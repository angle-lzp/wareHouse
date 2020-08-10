package com.angle.utils;

import com.angle.domain.WareHouse;

import java.util.ArrayList;
import java.util.List;

/**
 * 配合工具类：GetDistance共同使用：
 * GetDistance作用：1：通过位置计算距离；2：通过经纬度计算距离
 */
public class MapPathUtils {

    /**
     * @param currentLonLat：当前位置的经纬度
     * @param list：传入需要排序的数据
     * @return
     */
    public static List<WareHouse> getShortestPath(String currentLonLat, List<WareHouse> list) {

        //无法获取经纬度地点的客户
        List<WareHouse> listNone = new ArrayList<>();
        //可获取经纬度地点的客户
        List<WareHouse> listCanGet = new ArrayList<>();
        //记录可以获取经纬度地址点的经纬度
        List<String> listData = new ArrayList<>();

        for (WareHouse wareHouse : list) {
            try {
                //能获得经纬度的记录到集合:lonLat 当前位置的经纬度
                String lonLat = GetDistance.getLonLat(wareHouse.getAddress());
                listData.add(lonLat);
                listCanGet.add(wareHouse);

            } catch (NullPointerException e) {
                //无法获取经纬度的数据存入当里面
                listNone.add(wareHouse);
                e.printStackTrace();
            }
        }

        //处理可以计算得出经纬度的客户性信息
        //记录最终的排序好的数据
        List<WareHouse> finalWarehouse = new ArrayList<>();
        //当前位置和第一个位置的距离
        Long distanceflag = 0L;
        //标记当前最小距离的小标
        int shortFlag = 0;
        //标志不为空得第一个数据
        int flag = 0;
        //现在用户到被记录的距离
        Long nowDistance = 0L;
        //获取可以确定经纬度数据的个数
        int listCanGetLength = listCanGet.size();
        //记录当前用户的经纬度
        String currentUserLonLat = currentLonLat;

        //临时变量
        WareHouse temp = null;
        String tempStr = null;
        //计算总距离
        Long count = 0L;

        //算法二：
        for (int i = 0; i < listCanGetLength; i++) {
            for (int j = i; j < listCanGetLength; j++) {
                if (j == i) {
                    distanceflag = GetDistance.getDistance(currentUserLonLat, listData.get(j));
                    shortFlag = j;
                } else {
                    //现在用户到被记录的距离
                    nowDistance = GetDistance.getDistance(currentUserLonLat, listData.get(j));
                    //与被记录的当前用户经纬度计算距离
                    if (distanceflag > nowDistance) {
                        distanceflag = nowDistance;
                        shortFlag = j;
                    }
                }
            }
            //计算总距离
            //count += distanceflag;
            //替换记录当前用户的经纬度
            currentUserLonLat = listData.get(shortFlag);
            //把已经加入finalWarehous列表中的数据放入最前面（表示不再使用）
            temp = listCanGet.get(shortFlag);
            listCanGet.set(shortFlag, listCanGet.get(i));
            listCanGet.set(i, temp);
            //同时也改变listDate的经纬度信息
            tempStr = listData.get(shortFlag);
            listData.set(shortFlag, listData.get(i));
            listData.set(i, tempStr);
        }
        //System.out.println(count + "============================================");
        listCanGet.addAll(listNone);
        return listCanGet;


        //算法一：
        /*for (int i = 0; i < listCanGetLength; i++) {
            flag = 0;
            for (int j = 0; j < listCanGetLength; j++) {
                if (listCanGet.get(j).getFlagLonLat() == null && (!(GetDistance.getLonLat(listCanGet.get(j).getAddress()).equals(currentUserLonLat)))) {
                    if (flag == 0) {
                        distanceflag = GetDistance.getDistance(currentUserLonLat, GetDistance.getLonLat(listCanGet.get(j).getAddress()));
                        flag = 1;
                        shortFlag = j;
                    }
                    //现在用户的经纬度
                    String nowUserLonLat = GetDistance.getLonLat(listCanGet.get(j).getAddress());
                    //现在用户到被记录的距离
                    Long nowDistance = GetDistance.getDistance(currentUserLonLat, nowUserLonLat);
                    //与被记录的当前用户经纬度计算距离
                    if (distanceflag > nowDistance) {
                        distanceflag = nowDistance;
                        shortFlag = j;
                    }
                }
            }
            //计算总距离
            //count += distanceflag;
            //替换记录当前用户的经纬度
            currentUserLonLat = GetDistance.getLonLat(listCanGet.get(shortFlag).getAddress());
            //将当前用户设置到列表中
            finalWarehouse.add(listCanGet.get(shortFlag));
            //将当前用户的的flagLonLat归null
            listCanGet.get(shortFlag).setFlagLonLat(1);
        }
        //System.out.println(count + "============================================");
        finalWarehouse.addAll(listNone);
        return finalWarehouse;*/
    }
}