package com.learn.service.Impl;

import com.learn.mapper.SystemLogMapper;
import com.learn.pojo.SystemLog;
import com.learn.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/13 20:46
 */

@Service
public class SystemServiceImpl implements SystemLogService {


    @Autowired
    public SystemLogMapper systemLogMapper;

    @Override
    public List<SystemLog> findAllSystemLog() {
        return systemLogMapper.selectAll();
    }

    @Override
    public void saveSystemLog(SystemLog systemLog) {
        systemLogMapper.insert(systemLog);
    }
}
