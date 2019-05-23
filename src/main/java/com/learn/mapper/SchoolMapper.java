package com.learn.mapper;

import com.learn.pojo.School;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchoolMapper extends MyMapper<School> {
}