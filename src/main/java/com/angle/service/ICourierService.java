package com.angle.service;


import com.angle.domain.Courier;
import com.angle.utils.PageModel;
import com.angle.utils.PageResult;

import java.util.List;

public interface ICourierService {

    List<Courier> findAll();

    PageResult findAllPage(int currentPage, int pageSize);

    PageModel findAllPagePlus(int currentPageNum, int pageSize);

    Courier findById(int id);

    void modifyById(Courier courier);

    void deleteById(int id);

    void insert(Courier courier);

    Courier findByUsername(String username);
}
