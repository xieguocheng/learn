package com.learn.mapper;

import com.learn.pojo.Announce;
import com.learn.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnounceMapper extends MyMapper<Announce> {
}