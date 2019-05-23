package com.learn.web.controller.admin;

import com.learn.pojo.UserSuper;
import com.learn.service.SmsService;
import com.learn.service.UserSuperService;
import com.learn.utils.ApiResponse;
import com.learn.utils.LoginUserUtil;
import com.learn.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/3/31 11:06
 */

@Controller
public class AdminController {

    /*@GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello" ;
    }*/
    @Autowired
    public SmsService smsService;

    @Autowired
    public UserSuperService userSuperService;

    /**
     * 管理员登录页,如果登录过的用户重新访问登录会跳转到管理中心页面
     * 除非用户退出才可以重新登录
     * @return
     */
    @GetMapping("/admin/login")
    public String adminLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            return "admin/login";
        }else{
            return "redirect:/admin/center";
        }
    }

    /**
     * 切换账号页面，同时会退出当前账号，我也不知道为什么
     * 可以防止已经登录的账号想要进入登录界面被跳转到后台中心
     * @return
     */
    @GetMapping("/admin/switchAccount")
    public String switchAccount() {

       // return "redirect:/logout";
        return "/admin/login";
    }

    /**
     * 进入后台管理中心
     * @return
     */
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    /**
     * 进入后台管理中-欢迎页
     * @return
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 进入后台页面，测试用的，后面要删了
     * @return
     */
    /*@PostMapping("login")
    public String admin() {
        return "admin/center";
    }*/


    @GetMapping(value = "sms/code")
    @ResponseBody
    public ApiResponse smsCode(@RequestParam("telephone") String telephone) {

        if (!LoginUserUtil.checkTelephone(telephone)) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),
                    "请输入正确的手机号");
        }

        UserSuper userSuper=userSuperService.findUserSuperByTelephone(telephone);
        if (userSuper == null) { // 如果没有该管理员，就直接抛出异常
            //user = userService.addUserByPhone(telephone);
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(),
                    "没有该账号,省点钱，就不发短信浪费你时间了");
        }
        ServiceResult<String> result = smsService.sendSms(telephone);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }

    }



}
