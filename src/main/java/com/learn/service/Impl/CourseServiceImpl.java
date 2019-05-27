package com.learn.service.Impl;

import com.learn.dto.CourseDTO;
import com.learn.enums.CourseStatus;
import com.learn.mapper.*;
import com.learn.pojo.Course;
import com.learn.pojo.CourseTask;
import com.learn.pojo.UserCourse;
import com.learn.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 16:32
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public CourseMapper courseMapper;

    @Autowired
    public CourseTypeService courseTypeService;

    @Autowired
    public CourseTaskService courseTaskService;

    @Autowired
    public CourseExperimentService courseExperimentService;

    @Autowired
    public UserService userService;

    @Autowired
    public UserCourseService userCourseService;


    @Override
    public List<CourseDTO> findAllCourseDoing() {
        //存放courseDTO：包含course，type
        List<CourseDTO> courseDTOList=new ArrayList<CourseDTO>();
        //查询到所有status=1的进行中的课程
        Example courseExample=new Example(Course.class);
        courseExample.createCriteria().andEqualTo("status",CourseStatus.STATUS_NORMAL.getValue());
        List<Course> courseList=courseMapper.selectByExample(courseExample);
        //将所有存放courseList转换为存放courseDTOList
        courseList.forEach(course -> {
            CourseDTO courseDTO=modelMapper.map(course,CourseDTO.class);
            courseDTO.setMyCourseType(courseTypeService.findCourseTypeById(course.getCourseType()));
            courseDTOList.add(courseDTO);
        });
        return courseDTOList;

    }

    @Override
    public List<CourseDTO> findAllCourseEnd() {
        //存放courseDTO：包含course，type
        List<CourseDTO> courseDTOList=new ArrayList<CourseDTO>();
        //查询到所有status=2结束了的课程
        Example courseExample=new Example(Course.class);
        courseExample.createCriteria().andEqualTo("status",CourseStatus.STATUS_END.getValue());
        List<Course> courseList=courseMapper.selectByExample(courseExample);
        //将所有存放courseList转换为存放courseDTOList
        courseList.forEach(course -> {
            CourseDTO courseDTO=modelMapper.map(course,CourseDTO.class);
            courseDTO.setMyCourseType(courseTypeService.findCourseTypeById(course.getCourseType()));
            courseDTOList.add(courseDTO);
        });
        return courseDTOList;
    }

    @Override
    public List<CourseDTO> findAllCourseDelete() {
        //存放courseDTO：包含course，type
        List<CourseDTO> courseDTOList=new ArrayList<CourseDTO>();
        //查询到所有status=0的逻辑删除了的课程
        Example courseExample=new Example(Course.class);
        courseExample.createCriteria().andEqualTo("status",CourseStatus.STATUS_ADMIN_DELETE.getValue());
        List<Course> courseList=courseMapper.selectByExample(courseExample);
        //将所有存放courseList转换为存放courseDTOList
        courseList.forEach(course -> {
            CourseDTO courseDTO=modelMapper.map(course,CourseDTO.class);
            courseDTO.setMyCourseType(courseTypeService.findCourseTypeById(course.getCourseType()));
            courseDTOList.add(courseDTO);
        });
        return courseDTOList;
    }

    @Override
    public Course findCourseByCourseId(String courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }


    @Override
    public CourseDTO findCourseDTOByCourseID(String courseId) {
        //存放courseDTO：包含course，type，course_task，course_experiment,user_course,uer
        CourseDTO courseDTO=new CourseDTO();
        //通过courseid查询一门课程的基本信息
        Course course=courseMapper.selectByPrimaryKey(courseId);
        //1.courseDTO添加course基本信息
        courseDTO=modelMapper.map(course,CourseDTO.class);
        //2.courseDTO添加courseType信息
        courseDTO.setMyCourseType(courseTypeService.findCourseTypeById(course.getCourseType()));
        //3.courseDTO添加course_task信息
        courseDTO.setCourseTaskList(courseTaskService.findCourseTaskByCourseId(courseId));
        //4.courseDTO添加course_experiment信息
        courseDTO.setCourseExperimentList(courseExperimentService.findCourseExperimentByCourseId(courseId));
        //5.courseDTO添加user_course信息（学生ids）
        courseDTO.setUserCourseList(userCourseService.findUserCourseByCourseId(courseId));
        //6.courseDTO添加user信息(学生）
        courseDTO.setUserList(userService.findUserByCourseId(courseId));
        //7.courseDTO添加user信息（老师）
        courseDTO.setTeacher(userService.findUserByUserId(course.getUserId()));
        return courseDTO;
    }

    @Override
    public List<Course> findCreateCourseByUserId(String userId) {
        Example courseExample=new Example(Course.class);
        courseExample.createCriteria().andEqualTo("userId",userId);
        return courseMapper.selectByExample(courseExample);

    }

    @Override
    public List<Course> findJoinCourseByUserId(String userId) {

        List<UserCourse> userCourseList=userCourseService.findUserCourseByUserId(userId);
        //如果没有用户加入本课程直接放回一个空user对象集合
        if(userCourseList.size()==0){
            return new ArrayList<Course>();
        }
        //获取所有课程courseIds
        List<String> courseIds=new ArrayList<>();
        userCourseList.forEach(userCourse ->{
            courseIds.add(userCourse.getCourseId());
        });
        //通过userids查询所有用户
        List<Course> courseList=courseMapper.getCourseListByCourseIds(courseIds);
        return courseList;
    }

    @Override
    public List<CourseDTO> findCreateCourseWithTeacherByUserId(String userId) {
        //存放查询我创建的所有课程(包含教师信息）
        List<CourseDTO> createCourseDTOList=new ArrayList<CourseDTO>();//courseDto包含教师信息
        //查询我创建的课程
        List<Course> createCourseList=findCreateCourseByUserId(userId);
        createCourseList.forEach(course -> {
            //判断是否删除了
            if(course.getStatus()!=0){
                CourseDTO courseDTO=modelMapper.map(course,CourseDTO.class);
                courseDTO.setTeacher(userService.findUserByUserId(course.getUserId()));
                createCourseDTOList.add(courseDTO);
            }
        });
        return createCourseDTOList;
    }

    @Override
    public List<CourseDTO> findJoinCourseWithTeacherByUserId(String userId) {
        //存放查询我创建的所有课程(包含教师信息）
        List<CourseDTO> joinCourseDTOList=new ArrayList<CourseDTO>();//courseDto包含教师信息
        //查询我创建的课程
        List<Course> joinCourseList=findJoinCourseByUserId(userId);
        joinCourseList.forEach(course -> {
            //判断是否删除了
            if(course.getStatus()!=0) {
                CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
                courseDTO.setTeacher(userService.findUserByUserId(course.getUserId()));
                joinCourseDTOList.add(courseDTO);
            }
        });
        return joinCourseDTOList;
    }

    @Override
    public CourseDTO findCourseDTOTaskByCourseId(String courseId) {
        //存放courseDTO：包含course，type，course_task，course_experiment,user_course,uer
        CourseDTO courseDTO=new CourseDTO();
        //通过courseid查询一门课程的基本信息
        Course course=courseMapper.selectByPrimaryKey(courseId);
        //1.courseDTO添加course基本信息
        courseDTO=modelMapper.map(course,CourseDTO.class);
        //3.courseDTO添加course_task信息
        courseDTO.setCourseTaskList(courseTaskService.findCourseTaskByCourseId(courseId));
        //5.courseDTO添加user_course信息
        courseDTO.setUserCourseList(userCourseService.findUserCourseByCourseId(courseId));
        //6.courseDTO添加uer信息
        courseDTO.setUserList(userService.findUserByCourseId(courseId));
        return courseDTO;
    }

    @Override
    public CourseDTO findCourseDTOExperimentByCourseId(String courseId) {
        //存放courseDTO：包含course，type，course_task，course_experiment,user_course,uer
        CourseDTO courseDTO=new CourseDTO();
        //通过courseid查询一门课程的基本信息
        Course course=courseMapper.selectByPrimaryKey(courseId);
        //1.courseDTO添加course基本信息
        courseDTO=modelMapper.map(course,CourseDTO.class);
        //4.courseDTO添加course_experiment信息
        courseDTO.setCourseExperimentList(courseExperimentService.findCourseExperimentByCourseId(courseId));
        //5.courseDTO添加user_course信息
        courseDTO.setUserCourseList(userCourseService.findUserCourseByCourseId(courseId));
        //6.courseDTO添加uer信息
        courseDTO.setUserList(userService.findUserByCourseId(courseId));
        return courseDTO;
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateByPrimaryKey(course);

    }

    @Override
    public void logicDeleteCourseByCourseId(String courseId) {
        //查询后修改状态，逻辑删除
        Course course=courseMapper.selectByPrimaryKey(courseId);
        course.setStatus(CourseStatus.STATUS_ADMIN_DELETE.getValue());
        courseMapper.updateByPrimaryKey(course);
    }
}
