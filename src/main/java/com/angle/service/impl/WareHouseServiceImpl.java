package com.angle.service.impl;

import com.angle.domain.WareHouse;
import com.angle.mapper.WareHouseMapper;
import com.angle.service.IWareHouseService;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHouseServiceImpl implements IWareHouseService {

    @Autowired
    private WareHouseMapper mapper;


    @Override
    public List<WareHouse> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findAllPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAll();
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageResult findAllPageByCid(int currentPage, int pageSize, int cid) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAllByCid(cid);
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageResult findAllPageByLine(int currentPage, int pageSize, String line) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAllByLine(line);
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageResult findAllPageByOther(int currentPage, int pageSize, String other) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAllByOther("%" + other + "%");
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageResult findAllPageByAll(int currentPage, int pageSize, String line, String other) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAllByAll(line, "%" + other + "%");
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageResult findAllPageByCondition(int currentPage, int pageSize, String condition) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAllByCondition("%" + condition + "%");
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageResult findAllPageByCidAndLine(int currentPage, int pageSize, int cid, String line) {
        PageHelper.startPage(currentPage, pageSize);
        Page<WareHouse> list = (Page<WareHouse>) mapper.findAllPageByCidAndLine(cid, line);
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public List<WareHouse> findAllByCid(Integer id) {
        return mapper.findAllByCid(id);
    }

    @Override
    public int findCountByCid(Integer cid) {
        return mapper.findCountByCid(cid);
    }

    @Override
    public List<WareHouse> findByImgUrlOver(int cid) {
        return mapper.findByImgUrlOver(cid);
    }

    @Override
    public List<WareHouse> findByImgUrlNoOver(int cid) {
        return mapper.findByImgUrlNoOver(cid);
    }

    @Override
    public void modifyByIdForCid(Integer id) {
        mapper.modifyByIdForCid(id);
    }

    @Override
    public void updateTaskByCid(Integer cid) {
        mapper.updateTaskByCid(cid);
    }

    @Override
    public void modifyForImgUrlById(int id, String imgUrl) {
        mapper.modifyForImgUrlById(id, imgUrl);
    }

    @Override
    public void modifyImgUrlById(WareHouse wareHouse) {
        mapper.modifyImgUrlById(wareHouse);
    }

    @Override
    public PageModel findAllPagePlus(int currentPageNum, int pageSize) {
        int total = mapper.count();
        return new PageModel(currentPageNum, total, pageSize);
    }

    @Override
    public WareHouse findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public void modifyById(WareHouse wareHouse) {
        mapper.modifyById(wareHouse);
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }

    @Override
    public void insert(WareHouse wareHouse) {
        mapper.insert(wareHouse);
    }

    @Override
    public void modifyOnlyCid(int id, int cid) {
        mapper.modifyOnlyCid(id, cid);
    }
}