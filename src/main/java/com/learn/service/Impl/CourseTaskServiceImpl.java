package com.learn.service.Impl;

import com.learn.mapper.CourseTaskMapper;
import com.learn.pojo.CourseTask;
import com.learn.pojo.CourseType;
import com.learn.service.CourseTaskService;
import com.learn.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:39
 */

@Service
public class CourseTaskServiceImpl implements CourseTaskService {



    @Autowired
    public CourseTaskMapper courseTaskMapper;


    @Override
    public List<CourseTask> findAllCourseTask() {
        return courseTaskMapper.selectAll();
    }

    @Override
    public List<CourseTask> findCourseTaskByCourseId(String courseId) {
        Example courseTaskExample = new Example(CourseTask.class);
        courseTaskExample.createCriteria().andEqualTo("courseId", courseId);//状态
        List<CourseTask> courseTaskList = courseTaskMapper.selectByExample(courseTaskExample);
        //如果查询不到返回一个空对象
        if(courseTaskList.size()==0){
            return new ArrayList<CourseTask>();
        }
        return courseTaskList;
    }

    @Override
    public CourseTask findCourseTaskByCourseTaskId(String courseTaskId) {
        //通过courseTaskId查询一门课程作业
        Example courseTaskExample = new Example(CourseTask.class);
        courseTaskExample.createCriteria().andEqualTo("courseTaskId", courseTaskId);
        return courseTaskMapper.selectOneByExample(courseTaskExample);
    }

    @Override
    public void updateCourseTask(CourseTask courseTask) {
        courseTaskMapper.updateByPrimaryKey(courseTask);
    }
}
