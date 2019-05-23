package com.learn.web.controller;

import com.learn.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * web错误 全局配置
 * Created by xo
 */
@Controller
@Slf4j
public class AppErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @Autowired
    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * Web页面错误处理
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        log.info("错误：AppErrorController errorPageHandler status:"+status);
        switch (status) {
            case 403:
                return "403";
            case 404:
                return "404";
            case 500:
                return "500";
        }

        return "user/index";
    }

    /**
     * 除Web页面外的错误处理，比如Json/XML等
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ApiResponse errorApiHandler(HttpServletRequest request) {
       // RequestAttributes requestAttributes = new ServletRequestAttributes(request);
       // Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);

        //springboot2.0版本出现webRequest问题，未解决
        //TODO
        /*HttpServletRequest req=new ServletRequestAttributes(request).getRequest();
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes
                ((WebRequest) req,false);*/

        int status = getStatus(request);
        log.info("错误：AppErrorController errorApiHandler status:"+status);
        return ApiResponse.ofMessage(status, "some error !!");
    }


    private int getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }
        return 500;
    }
}
