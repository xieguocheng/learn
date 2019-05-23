package com.learn.web.controller.admin;

import com.learn.dto.CourseDTO;
import com.learn.dto.UserExperimentDTO;
import com.learn.dto.UserTaskDTO;
import com.learn.enums.UserExperimentStatus;
import com.learn.enums.UserTaskStatus;
import com.learn.pojo.*;
import com.learn.service.*;
import com.learn.utils.ApiResponse;
import com.learn.utils.UrlFilesToZip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 19:37
 */

@Controller
public class CourseController {

    @Autowired
    public CourseService courseService;

    @Autowired
    public CourseTypeService courseTypeService;

    @Autowired
    public CourseTaskService courseTaskService;

    @Autowired
    public CourseExperimentService courseExperimentService;

    @Autowired
    public QuestionService questionService;

    @Autowired
    public UserTaskService userTaskService;

    @Autowired
    public UserExperimentService userExperimentService;


    /******************************课程基本信息页面增、删、改、查************************************/

    /**
     *  课程进行中页面，status=1
     * @param model
     * @return
     */
    @GetMapping("admin/course/courseDoingList")
    public String courseDoingListPage(Model model) {
        List<CourseDTO> courseDoingList=courseService.findAllCourseDoing();
        model.addAttribute("courseDoingList",courseDoingList);
        return "admin/course/course-doing-list";
    }

    /**
     *  课程结束页面，status=2
     * @param model
     * @return
     */
    @GetMapping("admin/course/courseEndList")
    public String courseEndListPage(Model model) {
        List<CourseDTO> courseEndList=courseService.findAllCourseEnd();
        model.addAttribute("courseEndList",courseEndList);
        return "admin/course/course-end-list";

    }
    /**
     *  已经删除课程页面，status=0
     * @param model
     * @return
     */
    @GetMapping("admin/course/courseDeleteList")
    public String courseDeleteList(Model model) {
        List<CourseDTO> courseDeleteList=courseService.findAllCourseDelete();
        model.addAttribute("courseDeleteList",courseDeleteList);
        return "admin/course/course-delete-list";
    }

    /**
     * 逻辑删除，status=0
     * @param courseId
     * @return
     */
    @PostMapping(value="admin/course/logicDelete")
    @ResponseBody
    public ApiResponse courseLogicDelete(String courseId){
        try{
            System.out.println(courseId);

            courseService.logicDeleteCourseByCourseId(courseId);
            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }




    /******************************课程详情基本详情页面************************************/
    /**
     * 查看课程表基本详情
     * @param model
     * @param courseId
     * @return
     */
    @GetMapping(value="admin/course/courseDetail/{courseId}")
    public String questionDetail(Model model,@PathVariable("courseId") String courseId){
        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        CourseType courseType=courseTypeService.findCourseTypeById(courseDTO.getCourseType());
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("courseType",courseType);
        return "admin/course/course-detail";
    }


    /******************************课程作业（courseTask）信息页面************************************/

    private Double yes=0.0;//一门课程作业总提交份数
    private Double no=0.0;//一门课程作业总未提交份数
    private Double total=0.0;//一门课程作业总数
    /**
     * 查看课程表作业详情统计
     * @param model
     * @param courseId
     * @return
     */
    @GetMapping(value="admin/course/courseTaskDetail/{courseId}")
    public String courseTaskDetail(Model model,@PathVariable("courseId") String courseId){
        //课程基本所有信息
        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        CourseType courseType=courseTypeService.findCourseTypeById(courseDTO.getCourseType());
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("courseType",courseType);
        //统计得到所有提交，未提交，总数
        courseDTO.getCourseTaskList().forEach(courseTask -> {
            Map<String,Double> map=analysisTask(courseTask.getCourseTaskId().toString());
            yes=map.get("submit_yes")+yes;
            no=map.get("submit_no")+no;
            total=map.get("total")+total;
        });
        model.addAttribute("yes", yes/total);
        model.addAttribute("no",no/total);

        yes=0.0;no=0.0;total=0.0;        //记得清零
        return "admin/course/course-task-detail";
    }

    /**
     * 查看课程表作业详情-question(作业题目详情）
     * @param model
     * @param courseTaskId
     * @return
     */
    @GetMapping(value="admin/course/courseTaskDetail/question/{courseTaskId}")
    public String courseTaskQuestionDetail(Model model,@PathVariable("courseTaskId") String courseTaskId){
        CourseTask courseTask=courseTaskService.findCourseTaskByCourseTaskId(courseTaskId);
        List<Question> questionList=questionService.findQuestionByQuestionListId(
                courseTask.getQuestionListId()
        );
        model.addAttribute("courseTask",courseTask);
        model.addAttribute("questionList",questionList);
        return "admin/course/task-question-detail";
    }
    /**
     * 查看课程表作业详情-user(学生答题情况）
     * @param model
     * @param courseTaskId
     * @return
     */
    @GetMapping(value="admin/course/courseTaskDetail/user/{courseTaskId}")
    public String courseTaskUserDetail(Model model,@PathVariable("courseTaskId") String courseTaskId){

        List<UserTaskDTO> userTaskDTOList=userTaskService.
                findUserTaskDTOByCourseTaskId(courseTaskId);
        model.addAttribute("userTaskDTOList",userTaskDTOList);
        return "admin/course/task-user-detail";
    }


    /**
     * 查看课程表作业详情-analysis(学生答题统计情况）
     * @param model
     * @param courseTaskId
     * @return
     */
    @GetMapping(value="admin/course/courseTaskDetail/analysis/{courseTaskId}")
    public String courseTaskAnalysisDetail(Model model,@PathVariable("courseTaskId") String courseTaskId){
        //调用统计函数
        Map<String,Double> map=analysisTask(courseTaskId);
        Double submit_yes=map.get("submit_yes")/map.get("total");
        Double submit_no=map.get("submit_no")/map.get("total");
        model.addAttribute("submit_yes",submit_yes);
        model.addAttribute("submit_no",submit_no);

        return "admin/course/task-analysis-detail";
    }


    private Double submit=0.0;//查出一门课程一次作业交作业了的人数

    /**
     *  统计作业人数函数
     * @param courseTaskId
     * @return
     */
    public Map<String,Double> analysisTask(String courseTaskId){
        //查出一个作业courseTask下所有学生
        List<UserTaskDTO> userTaskDTOList=userTaskService.
                findUserTaskDTOByCourseTaskId(courseTaskId);
        //算出交了作业的人数
        userTaskDTOList.forEach(userTaskDTO -> {
            if(userTaskDTO.getStatus().equals(UserTaskStatus.STATUS_YES.getValue())){
                submit++;
            }
        });
        Double total=(double)userTaskDTOList.size();
        Map<String,Double> map=new HashMap<>();
        //将上交，未上交添加进map
        map.put("submit_yes",submit);
        map.put("submit_no",total-submit);
        map.put("total",total);
        //清零
        submit=0.0;
        return map;
    }


    /******************************课程实验（courseExperiment）信息页面************************************/
    private Double ex_yes=0.0;//一门课程作业总提交份数
    private Double ex_no=0.0;//一门课程作业总未提交份数
    private Double ex_total=0.0;//一门课程作业总数
    /**
     * 查看课程实验表统计分析
     * @param model
     * @param courseId
     * @return
     */
    @GetMapping(value="admin/course/courseExperimentDetail/{courseId}")
    public String courseExperimentDetail(Model model,@PathVariable("courseId") String courseId){

        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        CourseType courseType=courseTypeService.findCourseTypeById(courseDTO.getCourseType());
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("courseType",courseType);
        //统计得到所有提交，未提交，总数
        courseDTO.getCourseExperimentList().forEach(courseExperiment -> {
            Map<String,Double> map=analysisExperiment(courseExperiment.getCourExperimentId().toString());
            ex_yes=map.get("submit_yes")+ex_yes;
            ex_no=map.get("submit_no")+ex_no;
            ex_total=map.get("total")+ex_total;
        });
        model.addAttribute("yes", ex_yes/ex_total);
        model.addAttribute("no",ex_no/ex_total);

        ex_yes=0.0;ex_no=0.0;ex_total=0.0;        //记得清零

        return "admin/course/course-experiment-detail";
    }

    /**
     * 查看课程表实验详情-question(实验详情）
     * @param model
     * @param courExperimentId
     * @return
     */
    @GetMapping(value="admin/course/courseExperimentDetail/question/{courExperimentId}")
    public String courseExperimentQuestionDetail(Model model,
                  @PathVariable("courExperimentId") String courExperimentId){
        //查出课程实验
        CourseExperiment courseExperiment=courseExperimentService.
                findCourseExperimentBycourExperimentId(courExperimentId);
        //设置课程实验附件路径
        List<String> urlList=new ArrayList<String>();
        String courUrl=courseExperiment.getCourUrl();
        if(courUrl!=null&&!courUrl.equals("")){     //判断是否为空
            String urls[]=courUrl.split(",");
            for(int i=0;i<urls.length;i++){
                urlList.add(urls[i]);
            }
        }
        model.addAttribute("courseExperiment",courseExperiment);
        model.addAttribute("urlList",urlList);
        return "admin/course/experiment-question-detail";
    }

    /**
     * 查看课程表实验详情-user(学生实验上交情况）
     * @param model
     * @param courExperimentId
     * @return
     */
    @GetMapping(value="admin/course/courseExperimentDetail/user/{courExperimentId}")
    public String courseExperimentUserDetail(Model model,
                 @PathVariable("courExperimentId") String courExperimentId){
        List<UserExperimentDTO> userExperimentDTOList=userExperimentService.
                findUserExperimentDTOByCourExperimentId(courExperimentId);
        model.addAttribute("courExperimentId",courExperimentId);
        model.addAttribute("userExperimentDTOList",userExperimentDTOList);
        return "admin/course/experiment-user-detail";
    }

    /**
     * 查看课程表实验详情-analysis(学生实验统计情况）
     * @param model
     * @param courExperimentId
     * @return
     */
    @GetMapping(value="admin/course/courseExperimentDetail/analysis/{courExperimentId}")
    public String coursExperimentAnalysisDetail(Model model,
                  @PathVariable("courExperimentId") String courExperimentId){
        //调用统计函数
        Map<String,Double> map=analysisExperiment(courExperimentId);
        Double submit_yes=map.get("submit_yes")/map.get("total");
        Double submit_no=map.get("submit_no")/map.get("total");
        model.addAttribute("submit_yes",submit_yes);
        model.addAttribute("submit_no",submit_no);

        return "admin/course/experiment-analysis-detail";
    }


    private Double ex_submit=0.0;//查出一门课程一次作业交作业了的人数
    /**
     * 统计实验人数函数
     * @param courExperimentId
     * @return
     */
    public Map<String,Double> analysisExperiment(String courExperimentId){
        //查出一个实验courExperiment下所有学生
        List<UserExperimentDTO> userExperimentDTOList=userExperimentService.
                findUserExperimentDTOByCourExperimentId(courExperimentId);
        //算出交了实验的人数
        userExperimentDTOList.forEach(userExperimentDTO -> {
            if(userExperimentDTO.getStatus().equals(UserExperimentStatus.STATUS_YES.getValue())){
                ex_submit++;
            }
        });
        Double ex_total=(double)userExperimentDTOList.size();
        Map<String,Double> map=new HashMap<>();
        //将上交，未上交添加进map
        map.put("submit_yes",ex_submit);
        map.put("submit_no",ex_total-ex_submit);
        map.put("total",ex_total);
        //清零
        ex_submit=0.0;
        return map;
    }


/******************************导出实验********************************************/
    /**
     * 下载一门实验下所有学生的实验文档，打包成zip文件，【记得携带？courExperimentId=参数】
     * @param response
     */

    @GetMapping("admin/create/courseExperiment/downLoad")
    public void courseExperimentDownLoad(HttpServletResponse response,
               @RequestParam(name = "courExperimentId") String courExperimentId){
        //导出数据
        courseExperimentService.courseExperimentExport(response,courExperimentId);
    }





}
