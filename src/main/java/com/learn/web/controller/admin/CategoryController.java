package com.learn.web.controller.admin;

import com.learn.pojo.CourseType;
import com.learn.pojo.Dept;
import com.learn.service.CourseTypeService;
import com.learn.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/13 18:21
 */

@Controller
public class CategoryController {

    @Autowired
    private CourseTypeService courseTypeService;
    @Autowired
    private DeptService deptService;

    /**
     * 课程分类列表
     * @param model
     * @return
     */
    @GetMapping("admin/category/courseTypeList")
    public String courseTypeListPage(Model model){
        List<CourseType> courseTypeList=courseTypeService.findAllCourseType();
        model.addAttribute("courseTypeList",courseTypeList);
        return "admin/category/category-coursetype-list";
    }

    /**
     * 院系分类列表
     * @param model
     * @return
     */
    @GetMapping("/admin/category/deptList")
    public String deptList(Model model){
        List<Dept> deptList=deptService.findAllDept();
        model.addAttribute("deptList",deptList);
        return "admin/category/category-dept-list";
    }

    /**
     * 新增courseType页面
     * @return
     */
    @RequestMapping(value="admin/category/courseType/AddPage")
    public String courseTypeAddPage(){
        return "admin/category/courseType-add";
    }

    /**
     * 新增courseType页面
     * @return
     */
    @RequestMapping(value="admin/category/dept/AddPage")
    public String deptAddPage(){
        return "admin/category/dept-add";
    }





}
