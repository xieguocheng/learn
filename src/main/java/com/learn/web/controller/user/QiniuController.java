package com.learn.web.controller.user;

import com.google.gson.Gson;
import com.learn.dto.QiNiuPutRet;
import com.learn.pojo.User;
import com.learn.service.QiNiuService;
import com.learn.utils.ApiResponse;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/12 13:25
 */

@Controller
@Slf4j
public class QiniuController {

    @Autowired
    private QiNiuService qiNiuService;

    @Autowired
    private Gson gson;


    /**
     * 上传文件接口,需要携带参数courseId，用于设置文件路径
     * 路径由：courseId/userid/文件名字组成，路径唯一
     * @param file
     * @return
     */
    @PostMapping(value = "user/upload/file/{courseId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file,
           @PathVariable("courseId") String courseId, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String userId=user.getUserId();

        //判断文件是否为空
        if (file.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        //设置文件名路径名字：courseid/userid/文件名字
        String fileName = courseId+"/"+userId+"/"+file.getOriginalFilename();

        try {
            //【上传文件用inputStream方式到七牛云，失败就重复3次】
            InputStream inputStream = file.getInputStream();
            Response response = qiNiuService.uploadFile(inputStream,fileName);
            if (response.isOK()) {
                //【如果上传成功返回gson数据，内含重要的图片信息key】
                //方法一：使用默认putRet
                DefaultPutRet defaultPutRet= gson.
                        fromJson(response.bodyString(),DefaultPutRet.class);
                log.info("default putRet"+defaultPutRet.toString());
                //方法二：使用自定义putRet
                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                log.info("QiNiuPutRet"+ret);

                return ApiResponse.ofSuccess(ret);
            } else {
                //失败返回错误码和错误信息
                return ApiResponse.ofMessage(response.statusCode, response.getInfo());
            }
        } catch (QiniuException e) {
            Response response = e.response;
            try {
                return ApiResponse.ofMessage(response.statusCode, response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
                return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
