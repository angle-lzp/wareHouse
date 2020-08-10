package com.angle.service.impl;

import com.angle.domain.Courier;
import com.angle.mapper.CourierMapper;
import com.angle.service.ICourierService;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginServiceImpl")
public class CourierServiceImpl implements ICourierService {

    @Autowired
    private CourierMapper mapper;

    @Override
    public List<Courier> findAll() {
        return mapper.findAll();
    }

    @Override
    public PageResult findAllPage(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Courier> list = (Page<Courier>) mapper.findAll();
        return new PageResult(list.getTotal(), list.getResult());
    }

    @Override
    public PageModel findAllPagePlus(int currentPageNum, int pageSize) {
        int total = mapper.count();
        return new PageModel(currentPageNum, total, pageSize);
    }

    @Override
    public Courier findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public void modifyById(Courier courier) {
        mapper.modifyById(courier);
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }

    @Override
    public void insert(Courier courier) {
        mapper.insert(courier);
    }

    @Override
    public Courier findByUsername(String username) {
        return mapper.findByUsername(username);
    }
}