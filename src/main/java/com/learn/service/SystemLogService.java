package com.learn.service;

import com.learn.pojo.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/13 20:45
 */

public interface SystemLogService {

    //查询所有systemlog
    public List<SystemLog> findAllSystemLog();

    //添加日志
    public void saveSystemLog(SystemLog systemLog);






}
