package com.learn.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/27 17:50
 */

@Controller
public class FeedbackController {


    @GetMapping("admin/feedback/feedbackList")
    public String questionListPage(){

        return "admin/feedback/feedback-list";
    }
}
