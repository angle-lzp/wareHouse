package com.angle.service;


import com.angle.domain.WareHouse;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;

import java.util.List;

public interface IWareHouseService {

    List<WareHouse> findAll();

    PageResult findAllPage(int currentPage, int pageSize);

    PageModel findAllPagePlus(int currentPageNum, int pageSize);

    WareHouse findById(int id);

    void modifyById(WareHouse wareHouse);

    void deleteById(int id);

    void insert(WareHouse wareHouse);

    void modifyOnlyCid(int id, int cid);

    PageResult findAllPageByCid(int currentPage, int pageSize, int cid);

    //按线路精准查找
    PageResult findAllPageByLine(int currentPage, int pageSize, String line);

    //对客户名字，地址进行模糊查找
    PageResult findAllPageByOther(int currentPage, int pageSize, String other);

    //对线路精准查找 and 客户名字，地址的模糊查找
    PageResult findAllPageByAll(int currentPage, int pageSize, String line, String other);

    //对线路，客户名字，地址的模糊查找
    PageResult findAllPageByCondition(int currentPage, int pageSize, String condition);

    PageResult findAllPageByCidAndLine(int currentPage, int pageSize, int id, String line);

    void modifyForImgUrlById(int id, String imgUrl);

    void modifyImgUrlById(WareHouse wareHouse);

    List<WareHouse> findAllByCid(Integer id);

    int findCountByCid(Integer cid);

    List<WareHouse> findByImgUrlOver(int cid);

    List<WareHouse> findByImgUrlNoOver(int cid);

    void modifyByIdForCid(Integer id);

    void updateTaskByCid(Integer cid);
}
