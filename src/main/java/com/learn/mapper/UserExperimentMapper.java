package com.learn.mapper;

import com.learn.pojo.UserExperiment;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserExperimentMapper extends MyMapper<UserExperiment> {
}