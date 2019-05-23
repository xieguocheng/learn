package com.learn.web.controller.admin;

import com.learn.dto.UserDTO;
import com.learn.pojo.Course;
import com.learn.pojo.User;
import com.learn.service.CourseService;
import com.learn.service.UserService;
import com.learn.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/9 12:35
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    /***********************************用户详情页面*****************************************/


    /**
     *  查询所有用户页面
     * @param model
     * @return
     */
    @GetMapping("admin/user/userList")
    public String userListPage(Model model) {
        List<User> userList=userService.findAllUser();
        model.addAttribute("userList",userList);
        return "admin/user/user-list";
    }

    /**
     * 查看用户详情
     * @param model
     * @param userId
     * @return
     */
    @GetMapping(value="admin/user/userDetail/{userId}")
    public String userDetail(Model model,@PathVariable("userId") String userId){
        UserDTO userDTO=userService.findUserDTOByUserId(userId);
        model.addAttribute("userDTO",userDTO);
        return "admin/user/user-detail";
    }


    /**
     * 查看用户创建的所有课程
     * @param model
     * @param userId
     * @return
     */
    @GetMapping(value="admin/user/createCourse/{userId}")
    public String createCourseDetail(Model model,@PathVariable("userId") String userId){
        List<Course> courseList=courseService.findCreateCourseByUserId(userId);
        model.addAttribute("courseList",courseList);
        return "admin/user/user-createcourse-detail";
    }

    /**
     * 查看用户加入的所有课程
     * @param model
     * @param userId
     * @return
     */
    @GetMapping(value="admin/user/joinCourse/{userId}")
    public String joinCourseDetail(Model model,@PathVariable("userId") String userId){
        List<Course> courseList=courseService.findJoinCourseByUserId(userId);
        //List<Course> courseList=courseService.findCourseByUserId(userId);
        model.addAttribute("courseList",courseList);
        return "admin/user/user-joincourse-detail";
    }

/***********************************增删改成，导出数据*****************************************/
    /**
     * 导出学生Excel
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/admin/excel/exportGradeList")
    public void exportDeptPExcel(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<User> userList=userService.findAllUser();//查找所有user
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String("用户.xlsx".getBytes("utf-8"), "ISO-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        //格式化导出数据到excel
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelUtil.exportUserList(userList,outputStream);
        if(outputStream != null){
            outputStream.close();
        }
        /*
         response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=USER.xls");
        HSSFWorkbook wb = ExcelUtil.export(userList);
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();*/
    }












}
