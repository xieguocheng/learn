package com.learn.web.controller.user;

import com.learn.dto.CourseDTO;
import com.learn.pojo.User;
import com.learn.service.CourseService;
import com.learn.service.UserService;
import com.learn.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/10 12:44
 */

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

/*************************************登录退出*******************************************/
    /**
     * 用户登录页面
     * @return
     */
    @GetMapping("/user/index")
    public String userLogin() {
        return "user/index";
    }


    /**
     * 登录验证
     * @param number
     * @param password
     * @param map
     * @param request
     * @return
     */
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String number,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpServletRequest request,
                        Model model) throws Exception{

        User user=userService.findUserByNumber(number);
        if(user==null){
            model.addAttribute("error","账号密码错误");
            return "user/index";
        }
        //使用md5进行加密验证
        if(MD5Utils.getMD5Str(password).equals(user.getPassword())){
            request.getSession().setMaxInactiveInterval(120*60);
            request.getSession().setAttribute("user", user);
            request.setAttribute("user",user);

            // 登录成功，就跳转到下一个controller.
            return "redirect:/user/courseList";
        }else {
            // 登录失败，刷新本登录页
            model.addAttribute("error","账号密码错误");
            return "user/index";
        }

    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @RequestMapping("/user/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "user/index";
    }
    /*************************************查询用户所有课程course*******************************************/

    /**
     * 查询用户所有课程（包括用户创建，加入的课程）
     * @param model
     * @return
     */
    @GetMapping(value="user/courseList")
    public String userCourse(Model model, HttpServletRequest request){
        //获取session中的user信息
        User user = (User)request.getSession().getAttribute("user");
        //查询我创建的所有课程(包含教师信息）
        List<CourseDTO> createCourseDTOList=courseService.findCreateCourseWithTeacherByUserId(user.getUserId());
        model.addAttribute("createCourseDTOList",createCourseDTOList);
        //查询我加入的所有课程（包含教师信息）
        List<CourseDTO> joinCourseDTOList=courseService.findJoinCourseWithTeacherByUserId(user.getUserId());
        model.addAttribute("joineCourseDTOList",joinCourseDTOList);

        return "user/course-list";
    }
















}
