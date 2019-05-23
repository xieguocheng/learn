package com.learn.service;

import com.learn.utils.ServiceResult;

/**
 * @Author： XO
 * @Description： 短信验证服务
 * @Date： 2019/5/20 11:49
 */

public interface SmsService {

    /**
     * 发送验证码到指定手机 并 缓存验证码 10分钟 及 请求间隔时间1分钟
     * @param telephone
     * @return
     */
    ServiceResult<String> sendSms(String telephone);

    /**
     * 获取缓存中的验证码
     * @param telephone
     * @return
     */
    String getSmsCode(String telephone);

    /**
     * 移除指定手机号的验证码缓存
     */
    void remove(String telephone);
}
