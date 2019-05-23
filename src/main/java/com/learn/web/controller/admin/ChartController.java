package com.learn.web.controller.admin;

import com.learn.mapper.CourseMapper;
import com.learn.pojo.Course;
import com.learn.pojo.User;
import com.learn.service.CourseService;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/17 23:39
 */

@Controller
public class ChartController {

    @Autowired
    public UserService userService;

    @Autowired
    public CourseMapper courseMapper;


    /**
     *chart-01
     * @return
     */
    @RequestMapping(value="admin/chart/chart-01")
    public String chart_01(){

        return "admin/chart/chart-01";
    }

    /**
     *chart-01
     * @return
     */
    @RequestMapping(value="admin/chart/chart-02")
    public String chart_02(Model model){
        List<String> CreateTimeList=new ArrayList<String>();
        List<User> userList=userService.findAllUser();
        userList.forEach(user -> {
            CreateTimeList.add(new SimpleDateFormat("yyyy-MM").
                    format(user.getCreateTime()));
        });
        Integer time[]={0,0,0,0,0,0,0,0,0,0,0,0};
        CreateTimeList.forEach(createTime->{
            switch (createTime){
                case "2019-01":time[0]++;break;
                case "2019-02":time[1]++;break;
                case "2019-03":time[2]++;break;
                case "2019-04":time[3]++;break;
                case "2019-05":time[4]++;break;
                case "2019-06":time[5]++;break;
                case "2019-07":time[6]++;break;
                case "2019-08":time[7]++;break;
                case "2019-09":time[8]++;break;
                case "2019-10":time[9]++;break;
                case "2019-11":time[10]++;break;
                case "2019-12":time[11]++;break;
            }
        });
        model.addAttribute("time",time);
        return "admin/chart/chart-02";
    }


    /**
     *chart-03
     * @return
     */
    @RequestMapping(value="admin/chart/chart-03")
    public String chart_03(Model model){

        List<String> CreateTimeList=new ArrayList<String>();
        List<Course> courseList=courseMapper.selectAll();
        courseList.forEach(course -> {
            CreateTimeList.add(new SimpleDateFormat("yyyy-MM").
                    format(course.getCreateTime()));
        });
        Integer time[]={0,0,0,0,0,0,0,0,0,0,0,0};
        CreateTimeList.forEach(createTime->{
            switch (createTime){
                case "2018-01":time[0]++;break;
                case "2019-02":time[1]++;break;
                case "2019-03":time[2]++;break;
                case "2019-04":time[3]++;break;
                case "2019-05":time[4]++;break;
                case "2019-06":time[5]++;break;
                case "2019-07":time[6]++;break;
                case "2019-08":time[7]++;break;
                case "2019-09":time[8]++;break;
                case "2019-10":time[9]++;break;
                case "2019-11":time[10]++;break;
                case "2019-12":time[11]++;break;
            }
        });
        model.addAttribute("time",time);

        return "admin/chart/chart-03";
    }



}
