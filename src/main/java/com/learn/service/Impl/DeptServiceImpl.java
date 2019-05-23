package com.learn.service.Impl;

import com.learn.mapper.DeptMapper;
import com.learn.pojo.Dept;
import com.learn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/13 18:33
 */

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    public DeptMapper deptMapper;


    @Override
    public List<Dept> findAllDept() {
        return deptMapper.selectAll();
    }
}
