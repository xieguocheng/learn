package com.learn.security;

import com.google.common.base.Strings;
import com.learn.pojo.UserSuper;
import com.learn.service.SmsService;
import com.learn.service.UserSuperService;
import com.learn.utils.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author： XO
 * @Description：
 * @Date： 2018/11/21 16:24
 */

@Slf4j
public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserSuperService userSuperService;

    @Autowired
    private SmsService smsService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {

        //【1】验证用户名不为空的话就是走：用户名密码登录步骤
        String name = obtainUsername(request);
        if (!Strings.isNullOrEmpty(name)) {
            request.setAttribute("username", name);
            return super.attemptAuthentication(request, response);
        }
        //【2】这边是走验证码登录
        String telephone = request.getParameter("telephone");
        if (Strings.isNullOrEmpty(telephone) || !LoginUserUtil.checkTelephone(telephone)) {
            throw new BadCredentialsException("Wrong telephone number");
        }

        UserSuper userSuper=userSuperService.findUserSuperByTelephone(telephone);
        if (userSuper == null) { // 如果用户第一次用手机登录 则自动注册该用户
            //user = userService.addUserByPhone(telephone);
            throw new BadCredentialsException("NO USER");
        }

        log.info("【开始进入短信验证】");
        String inputCode = request.getParameter("smsCode");
        String sessionCode = smsService.getSmsCode(telephone);

        if (Objects.equals(inputCode, sessionCode)) {
            return new UsernamePasswordAuthenticationToken(userSuper,
                    null, userSuper.getAuthorities());
        } else {
            throw new BadCredentialsException("smsCodeError");
        }
    }
}
