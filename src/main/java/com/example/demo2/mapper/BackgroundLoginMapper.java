package com.example.demo2.mapper;

import com.example.demo2.dto.BackgroundLogin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

public interface BackgroundLoginMapper {
    @Update("INSERT INTO background (name,pwd) VALUES (#{name},#{pwd});")
    @Transactional
    void create(BackgroundLogin backgroundLogin);
    
    @Select("select exists (SELECT * from background where name=#{name}); ")
    @Transactional
    Integer search(String name);
    
    @Select("select exists (SELECT * from background where name=#{name} and pwd=#{pwd}); ")
    @Transactional
    Boolean login(BackgroundLogin backgroundLogin);
    
    @Delete("delete from background where name=#{name} ;")
    @Transactional
    Boolean delete(String name);
}
