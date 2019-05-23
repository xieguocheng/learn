package com.learn.web.controller.admin;

import com.learn.pojo.SystemLog;
import com.learn.pojo.UserSuper;
import com.learn.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/13 20:44
 */

@Controller
public class SystemController {

    @Autowired
    public SystemLogService systemLogService;

    /**
     * 查看所有管理员
     * @param model
     * @return
     */
    @RequestMapping(value="admin/system/systemLogList")
    public String userSuperList(Model model){
       List<SystemLog> systemLogList=systemLogService.findAllSystemLog();
        model.addAttribute("systemLogList", systemLogList);
        return "admin/system/systemLog-List";
    }



}
