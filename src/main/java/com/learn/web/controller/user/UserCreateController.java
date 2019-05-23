package com.learn.web.controller.user;

import com.learn.dto.CourseDTO;
import com.learn.dto.UserCourseDTO;
import com.learn.dto.UserExperimentDTO;
import com.learn.pojo.*;
import com.learn.service.*;
import com.learn.utils.ApiResponse;
import com.learn.utils.MailUtil;
import com.learn.utils.UrlFilesToZip;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户创建的课程操作（教师端）
 * @Author： XO
 * @Description：
 * @Date： 2019/3/31 15:41
 */

@Controller
@Slf4j
public class UserCreateController {

    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseExperimentService courseExperimentService;

    @Autowired
    private UserExperimentService userExperimentService;

    @Autowired
    private CourseTypeService courseTypeService;

    @Autowired
    private UserCourseService userCourseService;

    /*@Autowired
    private JavaMailSender mailSender;*/



/**********************************页面查询展示************************************/

    /**
     * 返回页面（back按钮）
     * @param courseId
     * @return
     */
        @GetMapping(value="user/createCourse/back")
        public String createCourseBack(@RequestParam(name = "courseId")
                                                       String courseId){
            return "redirect:/user/createCourse/experimentList?courseId="+courseId;
        }


    /**
     * 通过courseid,进入课程信息页面【记得携带？courseId=参数】
     * @param model
     * @return
     */
    @GetMapping(value="user/createCourse/course")
    public String userCreateCourse(Model model,RedirectAttributes attr,
           @RequestParam(name = "courseId") String courseId) throws IOException {

        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        model.addAttribute("courseDTO",courseDTO);
        model.addAttribute("courseId",courseId);
        return "user/createCourse/course";
    }

    /**
     *通过courseId查询所有实验页面
     * @param model
     * @return
     */
    @GetMapping(value="user/createCourse/experimentList")
    public String ExperimentList(Model model,@RequestParam(name = "courseId") String courseId){

        //查询一门课程下所有experiment
        List<CourseExperiment> courseExperimentList=courseExperimentService.
                findCourseExperimentByCourseId(courseId);
        model.addAttribute("courseExperimentList",courseExperimentList);
        return "user/createCourse/course-experiment-list";
    }

    /**
     * 通过courseID查询课程用户列表页面
     * @param model
     * @return
     */
    @GetMapping(value="user/createCourse/userList")
    public String userList(Model model,@RequestParam(name = "courseId") String courseId){
        //TODO 换一个方法
        List<User> userList=courseService.findCourseDTOByCourseID(courseId).getUserList();
        model.addAttribute("userList",userList);
        return "user/createCourse/course-user-list";
    }

    /**
     * 课程详情页面
     * @return
     */
    @GetMapping("user/createCourse/courseDetail/{courseId}")
    public String courseDetail(Model model,
                  @PathVariable("courseId") String courseId) {
        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        CourseType courseType=courseTypeService.findCourseTypeById(courseDTO.getCourseType());
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("courseType",courseType);
        //return "admin/course/course-detail";
        return "user/createCourse/course-detail";
    }



/****************************老师课程实验experiment增、删、改、查、导出数据*******************************************/

    /**
     * 发送邮件页面
     * @param model
     * @param courseId
     * @return
     */
    @GetMapping("user/createCourse/Experiment/mailPage/{courseId}")
    public String mailPage(Model model,
           @PathVariable("courseId") String courseId) {
        model.addAttribute("courseId",courseId);
        return "user/createCourse/mail-page";
    }
    /**
     * 发送邮件
     * @param courseId
     * @return
     */
    @PostMapping(value="user/create/courseExperiment/sendMail")
    @ResponseBody
    public ApiResponse senMail(String courseId,String subject,String text){

        //查询所有学生
        List<UserCourseDTO> userCourseDTOList=userCourseService.findUserCourseDTOByCourseId(courseId);
        //发送信息
        userCourseDTOList.forEach(userCourseDTO -> {

            log.info("发送邮件信息到："+userCourseDTO.getStudent().getEmail()+userCourseDTO.getStudent().getUsername());
            try {
                MailUtil.sendMessage(userCourseDTO.getStudent().getEmail(),
                        "【子鹿班课提醒您】"+subject, text);
            } catch (Exception e) {
                log.info("发送邮件失败！！");
                e.printStackTrace();
            }
           /* SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("1078700658@qq.com");
            mailMessage.setTo(userCourseDTO.getStudent().getEmail());
            mailMessage.setSubject("【子鹿班课提醒您】"+subject);
            mailMessage.setText(text);
            mailSender.send(mailMessage);*/


            log.info("发送邮件成功！！");
        });
        log.info("邮件发送圆满结束！！");

        return ApiResponse.ofSuccess(null);

    }



    /**
     * 老师创建实验页面
     * @return
     */
    @GetMapping("user/createCourse/Experiment/addPage/{courseId}")
    public String experimentAddpage(Model model,
                                    @PathVariable("courseId") String courseId) {
        model.addAttribute("courseId",courseId);
        return "user/createCourse/experiment-add";
    }


    /**
     * 老师添加课程实验，上面创建实验页面会携带courseId参数
     * @param courseExperiment
     */
    @PostMapping(value="user/createCourse/courseExperiment/add")
    @ResponseBody
    public ApiResponse userExperimentCreate(CourseExperiment courseExperiment, String end_time,
            @RequestParam(value="courUrls", required = false,defaultValue = "") List<String> courUrls){
        try{
            //【1】设置endTime属性值
            // 判断页面传值end_time是否为空，如果为空设置为7天后,同时将字符串格式截止时间转换为Data类型
            Date endTime;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(end_time.equals("")||end_time==null){
                Date d = new Date();
                String currdate = format.format(d);// System.out.println("现在的日期是：" + currdate);
                Calendar ca = Calendar.getInstance();
                ca.add(Calendar.DATE, 7);// num为增加的天数，可以改变的
                String enddate = format.format(ca.getTime());//System.out.println("增加天数以后的日期：" + enddate);
                endTime=format.parse(enddate);
            }else {
                endTime = format.parse(end_time);
            }
            //【2】对课程实验路径进行修改拼接，使用“，”连接所有资源路径
            String courUrl="";
            for (String url:courUrls) {
                url=java.net.URLEncoder.encode(url,"utf-8");//对路径上的中文字体进行编码urlencoder
                String path=cdnPrefix+url+",";
                courUrl=courUrl+path;
            }//System.out.println(courUrl);
            //【3】补充courseExperiment的属性
            courseExperiment.setEndTime(endTime);
            courseExperiment.setCourUrl(courUrl);
            courseExperiment.setCreateTime(new Date());
            courseExperiment.setUpdateTime(new Date());
            System.out.println("courseExperiment-------------"+courseExperiment);
            //【4】courseExperiment插入数据
            courseExperimentService.saveCourseExperiment(courseExperiment);
            //【5】userExperiment插入数据
            //先获取最新插入的courExperimentId,再向userExperiment中插入数据
            String courseId=courseExperiment.getCourseId();
            Integer courExperimentId=courseExperimentService.findMaxCourExperimentIdByCourseId(courseId);
            userExperimentService.addUserExperiment(courExperimentId,courseId);

            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }

    /**
     * 通过courExperimentId
     * 查询我创建的一门课程下，每一个实验详细信息页面【记得携带？courExperimentId=参数】
     * @return
     */
    @GetMapping(value="user/createCourse/ExperimentDetail")
    public String userCourseExperimentDetail(Model model,
                   @RequestParam(name = "courExperimentId") String courExperimentId){
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
        return "user/createCourse/experiment-detail";
    }

    /**
     * 查看课程表实验详情-user(学生实验情况）
     * @param model
     * @param courExperimentId
     * @return
     */
    @GetMapping(value="user/createCourse/courseExperiment/user/{courExperimentId}")
    public String courseExperimentUserDetail(Model model,
                @PathVariable("courExperimentId") String courExperimentId){

            List<UserExperimentDTO> userExperimentDTOList=userExperimentService.
                    findUserExperimentDTOByCourExperimentId(courExperimentId);
            model.addAttribute("courExperimentId",courExperimentId);
            model.addAttribute("userExperimentDTOList",userExperimentDTOList);
            return "user/createCourse/experiment-user";
    }

    /**
     * 下载一门实验下所有学生的实验文档，打包成zip文件，【记得携带？courExperimentId=参数】
     * @param response
     */
    private static final Logger logger = LoggerFactory.getLogger(UrlFilesToZip.class);
    @GetMapping("user/create/courseExperiment/downLoad")
    public void filesDownLoad(HttpServletResponse response,
                @RequestParam(name = "courExperimentId") String courExperimentId){
        //导出zip
        courseExperimentService.courseExperimentExport(response,courExperimentId);
    }

    /**
     * 根据courExperimentid删除一个实验，
     * @param courExperimentId
     * @return
     */
    @PostMapping(value="user/create/courseExperiment/ExperimentDelete")
    @ResponseBody
    public ApiResponse courseExperimentDelete(Integer courExperimentId){
        try{
            //TODO
            //questionService.deleteQuestionById(questionId);
            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }

    /**
     * 修改问题页面
     * @param model
     * @param questionId
     * @return
     */
    /*@GetMapping(value="admin/question/questionEditPage/{questionId}")
    public String questionEditPage(Model model,@PathVariable("questionId") Integer questionId){
        Question question=questionService.findQuestionById(questionId);
        List<CourseType> courseTypeList=courseTypeService.findAllCourseType();
        model.addAttribute("question", question);
        model.addAttribute("courseTypeList",courseTypeList);
        return "admin/question/question-edit";
    }*/

    /**
     * 修改
     * @param question
     */
    /*@PostMapping(value="admin/question/questionEdit")
    @ResponseBody
    public ApiResponse questionEdit(Question question){
        try{
            Question existQuestion=questionService.findQuestionById(question.getQuestionId());
            existQuestion.setQuestionDesc(question.getQuestionDesc());
            existQuestion.setChoice(question.getChoice());
            existQuestion.setType(question.getType());
            //修改记录
            questionService.updateQuestion(existQuestion);
            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

    }*/







}
