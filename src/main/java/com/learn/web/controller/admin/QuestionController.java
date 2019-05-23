package com.learn.web.controller.admin;

import com.learn.dto.QuestionDTO;
import com.learn.mapper.CourseTypeMapper;
import com.learn.pojo.CourseType;
import com.learn.pojo.Question;
import com.learn.service.CourseTypeService;
import com.learn.service.QuestionService;
import com.learn.utils.ApiResponse;
import com.learn.web.form.QuestionFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/1 20:44
 */

@Controller
public class QuestionController {


    //service
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping("test")
    @ResponseBody
    public  List<QuestionDTO> test(){
        return questionService.findAllSingleChoice(1);
    }


/*********************************单选，多选，填空，简答管理***********************************************/
    /**
     * 进入后台管理中singleChoiceList管理
     * @return
     */
    @GetMapping("admin/question/singleChoiceList")
    public String questionListPage(Model model,Integer examType) {
        if(examType==null){//如果examtype为null，查询所有单选题
            List<QuestionDTO> singleChoiceList=questionService.findAllSingleChoice(1);
            model.addAttribute("singleChoiceList", singleChoiceList);
            return "admin/question/single-choice-list";
        }else {//否则查询单选题外status为examtype的题目,0-逻辑删除 1-正常
            //TODO
            List<QuestionDTO> singleChoiceList=questionService.findAllSingleChoice(examType);
            model.addAttribute("singleChoiceList", singleChoiceList);
            return "admin/question/searchList_1";
        }
    }

    /**
     * 不定项
     * @param model
     * @return
     */
    @GetMapping(value="admin/question/multipleChoiceList")
    public String multipleChoiceList(Model model,Integer examType){
        if(examType==null){
            //TODO 3
            List<QuestionDTO> multipleChoiceList=questionService.findAllMultipleChoice(1);
            model.addAttribute("multipleChoiceList", multipleChoiceList);
            return "admin/question/multiple-choice-list";
        }else{
            List<QuestionDTO> multipleChoiceList=questionService.findAllMultipleChoice(examType);
            model.addAttribute("multipleChoiceList", multipleChoiceList);
            return "admin/question/searchList_1";
        }

    }
    /**
     * 填空
     * @param model
     * @return
     */
    @GetMapping(value="admin/question/fillBlankList")
    public String fillBlankList(Model model,Integer examType){
        if(examType==null){
            //TODO 4
            List<QuestionDTO> fillBlankList=questionService.findAllfillBlank(1);
            model.addAttribute("fillBlankList", fillBlankList);
            return "admin/question/fill-blank-list";
        }else{
            List<QuestionDTO> fillBlankList=questionService.findAllfillBlank(examType);
            model.addAttribute("fillBlankList", fillBlankList);
            return "admin/question/searchList_2";
        }

    }
    /**
     * 简答
     * @param model
     * @return
     */
    @GetMapping(value="admin/question/essayQuestionList")
    public String essayQuestionList(Model model,Integer examType){
        if(examType==null){
            //TODO 5
            List<QuestionDTO> essayQuestionList=questionService.findAllEssayQuestion(1);
            model.addAttribute("essayQuestionList", essayQuestionList);
            return "admin/question/essay-question-list";
        }else{
            List<QuestionDTO> essayQuestionList=questionService.findAllEssayQuestion(examType);
            model.addAttribute("essayQuestionList", essayQuestionList);
            return "admin/question/searchList_2";
        }

    }


    /**
     * 查看题详情
     * @param model
     * @param questionId
     * @return
     */
    @GetMapping(value="admin/question/questionDetail/{questionId}")
    public String questionDetail(Model model,@PathVariable("questionId") Integer questionId){
        Question question=questionService.findQuestionById(questionId);
        CourseType courseType=courseTypeService.findCourseTypeById(question.getType());
        model.addAttribute("question", question);
        model.addAttribute("courseType",courseType);
        return "admin/question/question-detail";
    }

    /**
     * 修改问题页面
     * @param model
     * @param questionId
     * @return
     */
    @GetMapping(value="admin/question/questionEditPage/{questionId}")
    public String questionEditPage(Model model,@PathVariable("questionId") Integer questionId){
        Question question=questionService.findQuestionById(questionId);
        List<CourseType> courseTypeList=courseTypeService.findAllCourseType();
        model.addAttribute("question", question);
        model.addAttribute("courseTypeList",courseTypeList);
        return "admin/question/question-edit";
    }

    /**
     * 修改
     * @param question
     */
    @PostMapping(value="admin/question/questionEdit")
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

    }
    /**
     * 删除
     */
    @PostMapping(value="admin/question/questionDelete")
    @ResponseBody
    public ApiResponse questionDelete(Integer questionId){
        try{
            questionService.deleteQuestionById(questionId);
            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }


    /**
     * 添加题目页面
     * @return
     */
    @GetMapping(value="admin/question/questionAddPage")
    public String questionAddPage(Model model){
        //显示题目类型
        List<CourseType> courseTypeList=courseTypeService.findAllCourseType();
        model.addAttribute("courseTypeList",courseTypeList);
        return "admin/question/question-add";
    }

    /**
     * 添加题目
     * @param question
     */
    @PostMapping(value="admin/question/questionAdd")
    @ResponseBody
    public ApiResponse questionAdd(Question question){
        try{
           // System.out.println("question-------------"+question);
            question.setStatus(1);
            question.setQuestionSource("管理员添加");
            //保存记录
            questionService.saveQuestion(question);
            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }


}
