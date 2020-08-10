package com.angle.mapper;

import com.angle.domain.Courier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourierMapper {

    List<Courier> findAll();

    int count();

    void deleteById(int id);

    void modifyById(Courier courier);

    Courier findById(int id);

    void insert(Courier courier);

    Courier findByUsername(String username);

    Courier findByUsernameTag(@Param("username") String username, @Param("tag") Integer tag);
}
