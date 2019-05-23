package com.learn.web.controller.user;

import com.learn.dto.CourseDTO;
import com.learn.dto.CourseExperimentDTO;
import com.learn.enums.UserExperimentStatus;
import com.learn.pojo.CourseExperiment;
import com.learn.pojo.CourseType;
import com.learn.pojo.User;
import com.learn.pojo.UserExperiment;
import com.learn.service.*;
import com.learn.utils.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户加入的课程操作（学生端）
 * @Author： XO
 * @Description：
 * @Date： 2019/5/10 12:41
 */

@Controller
public class UserJoinController {
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


/**********************************页面查询展示************************************/

    /**
     * 返回页面（back按钮）
     * @param courseId
     * @return
     */
    @GetMapping(value="user/joinCourse/back")
    public String createCourseBack(@RequestParam(name = "courseId")
                                           String courseId){
        return "redirect:/user/joinCourse/experimentList?courseId="+courseId;
    }
    /**
     * 通过courseid,进入课程信息页面【记得携带？courseId=参数】
     * @param model
     * @return
     */
    @GetMapping(value="user/joinCourse/course")
    public String userCreateCourse(Model model,
           @RequestParam(name = "courseId") String courseId)  {
        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        model.addAttribute("courseDTO",courseDTO);
        model.addAttribute("courseId",courseId);
        return "user/joinCourse/course";
    }
    /**
     *通过courseId查询所有实验页面
     * @param model
     * @return
     */
    @GetMapping(value="user/joinCourse/experimentList")
    public String ExperimentList(Model model,HttpServletRequest request,
                                 HttpSession session,
                                 @RequestParam(name = "courseId") String courseId){
        //查询一门课程下所有experiment，包含用户完成情况
        User user = (User)request.getSession().getAttribute("user");
        //User user = (User) request.getSession().getAttribute("user");
        List<CourseExperimentDTO> courseExperimentList=courseExperimentService.
                findCourseExperimentDTOWithUser(courseId,user.getUserId());
        model.addAttribute("courseExperimentList",courseExperimentList);
        return "user/joinCourse/course-experiment-list";
    }

    /**
     * 通过courseID查询课程用户列表页面
     * @param model
     * @return
     */
    @GetMapping(value="user/joinCourse/userList")
    public String userList(Model model,@RequestParam(name = "courseId") String courseId){
        //TODO 换一个方法
        List<User> userList=courseService.findCourseDTOByCourseID(courseId).getUserList();
        model.addAttribute("userList",userList);
        return "user/joinCourse/course-user-list";
    }

    /**
     * 课程详情页面
     * @return
     */
    @GetMapping("user/joinCourse/courseDetail/{courseId}")
    public String courseDetail(Model model,
                               @PathVariable("courseId") String courseId) {
        CourseDTO courseDTO=courseService.findCourseDTOByCourseID(courseId);
        CourseType courseType=courseTypeService.findCourseTypeById(courseDTO.getCourseType());
        model.addAttribute("courseDTO", courseDTO);
        model.addAttribute("courseType",courseType);
        return "user/joinCourse/course-detail";
    }


/*************************************学生课程实验experiment上传，修改实验*******************************************/


    /**
     * 通过courExperimentId
     * 查询我创建的一门课程下，每一个实验详细信息页面【记得携带？courExperimentId=参数】
     * @return
     */
    @GetMapping(value="user/joinCourse/ExperimentDetail")
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
        return "user/joinCourse/experiment-detail";
    }


    /**
     * 上交实验
     * @param userExperiment
     * @param request
     * @param session
     * @param userUrls
     * @return
     */
    @PostMapping(value="user/join/courseExperiment/add")
    @ResponseBody
    public ApiResponse userExperimentSubmit(UserExperiment userExperiment,
                                            HttpServletRequest request,
                                            HttpSession session,
          @RequestParam(value="userUrls", required = false,defaultValue = "") List<String> userUrls){

        //System.out.println(userExperiment);
        //System.out.println(userUrls.toString());

        //【1】上传url解析
        String userUrl="";
        for (String url:userUrls) {
            try {
                
                url=java.net.URLEncoder.encode(url,"utf-8");//对路径上的中文字体进行编码urlencoder
            } catch (UnsupportedEncodingException e) {
                ApiResponse.ofMessage(ApiResponse.Status.NOT_SUPPORTED_OPERATION.getCode(),"字符串解析出错");
                e.printStackTrace();
            }
            String path=cdnPrefix+url+",";
            userUrl=userUrl+path;
        }
        //【2】属性填充
        //User user = (User)session.getAttribute("user");
        User user = (User) request.getSession().getAttribute("user");
        userExperiment.setUserId(user.getUserId());
        userExperiment.setUserUrl(userUrl);
        userExperiment.setStatus(UserExperimentStatus.STATUS_YES.getValue());
        userExperiment.setUpTime(new Date());
        //【3】更新数据
        userExperimentService.updateUserExperiment(userExperiment);

        return ApiResponse.ofSuccess(null);

    }













}
