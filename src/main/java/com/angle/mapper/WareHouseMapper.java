package com.angle.mapper;


import com.angle.domain.WareHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WareHouseMapper {

    List<WareHouse> findAll();

    int count();

    void deleteById(int id);

    void modifyById(WareHouse wareHouse);

    WareHouse findById(int id);

    void insert(WareHouse wareHouse);

    void modifyOnlyCid(@Param(value = "id") int id, @Param(value = "cid") int cid);

    List<WareHouse> findAllByCid(int cid);

    //按线路精准查找
    List<WareHouse> findAllByLine(String line);

    //对客户名字，地址进行模糊查找
    List<WareHouse> findAllByOther(String otherthing);

    //对线路精准查找 and 客户名字，地址的模糊查找
    List<WareHouse> findAllByAll(@Param(value = "line") String line, @Param(value = "other") String other);

    //对线路，客户名字，地址的模糊查询
    List<WareHouse> findAllByCondition(@Param(value = "term") String term);

    List<WareHouse> findAllPageByCidAndLine(@Param(value = "cid") int cid, @Param(value = "line") String line);

    void modifyForImgUrlById(@Param(value = "id") int id, @Param(value = "imgUrl") String imgUrl);

    void modifyImgUrlById(WareHouse wareHouse);

    int findCountByCid(Integer cid);

    List<WareHouse> findByImgUrlOver(Integer cid);

    List<WareHouse> findByImgUrlNoOver(Integer cid);

    void modifyByIdForCid(Integer id);

    void updateTaskByCid(Integer cid);
}
