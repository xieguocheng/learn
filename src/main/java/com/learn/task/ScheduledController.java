package com.learn.task;

import com.learn.enums.CourseExperimentStatus;
import com.learn.enums.CourseTaskStatus;
import com.learn.pojo.CourseExperiment;
import com.learn.pojo.CourseTask;
import com.learn.service.CourseExperimentService;
import com.learn.service.CourseTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author： XO
 * @Description：    springboot自带的定时器，定时改变作业截止状态
 * @Date： 2019/4/11 18:24
 */

@EnableScheduling
@Slf4j
@Component
public class ScheduledController {

    @Autowired
    private CourseTaskService courseTaskService;
    @Autowired
    private CourseExperimentService courseExperimentService;

    //定时将courseTask中已经结束的作业状态status设置为2-已经结束
    //每天0点运行一次
    @Scheduled(cron = "0 0 0 * * ?  ")
    public void courseTaskScheduled() {
        log.info("开始更新courseTask课程作业结束了的status");
        List<CourseTask> courseTaskList=courseTaskService.findAllCourseTask();
        courseTaskList.forEach(courseTask -> {
            if(courseTask.getEndTime()==null){
                return;//如果endTime==null执行下一次遍历
            }else if(courseTask.getEndTime().before(new Date())){
                courseTask.setStatus(CourseTaskStatus.STATUS_END.getValue());
                courseTaskService.updateCourseTask(courseTask);
            }
        });
        log.info("更新courseTask完成");
    }


    //定时将courseExperiment中已经结束的作业状态status设置为1-已经结束
    //每天0点运行一次
    @Scheduled(cron = "0 0 0 * * ? ")
    public void courseExperimentScheduled() {
        log.info("开始更新courseExperimentService课程实验结束了的status");
        List<CourseExperiment> courseExperimentList=courseExperimentService.findAllcourseExperiment();
        courseExperimentList.forEach(courseExperiment -> {
            if(courseExperiment.getEndTime()==null){
                return;//如果endTime==null执行下一次遍历
            }else if(courseExperiment.getEndTime().before(new Date())){
                courseExperiment.setStatus(CourseExperimentStatus.STATUS_END.getValue());
                courseExperimentService.updateCourseExperiment(courseExperiment);
            }
        });
        log.info("更新courseExperimentService完成");
    }












}
