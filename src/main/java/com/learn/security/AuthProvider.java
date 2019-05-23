package com.learn.security;


import com.learn.pojo.SystemLog;
import com.learn.pojo.User;
import com.learn.pojo.UserSuper;
import com.learn.service.SystemLogService;
import com.learn.service.UserService;
import com.learn.service.UserSuperService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author： XO
 * @Description：
 * @Date： 2018/11/21 16:18
 */

@Data
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserSuperService userSuperService;

    @Autowired
    private UserService userService;

    @Autowired
    private SystemLogService systemLogService;

    //private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
    //MD5加密过时，使用BCrypt比较好
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        //1.获取用户输入的名字和密码
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        //2.1从数据库查询管理员信息
        UserSuper userSuper=userSuperService.findUserSuperByUsername(userName);

        //2.2从数据库查询普通用户信息
       // User user=userService.findUserByUsername(userName);

        if(userSuper==null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        //查看是否停用
        if(userSuper.getStatus()==0){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }

        //3.进行加密的密码验证
        //3.1先授权看看是不是admin
        if(this.passwordEncoder.matches(inputPassword,userSuper.getPassword())){

            //登录成功添加systemlog日志
            //更新操作日志
            SystemLog systemLog=new SystemLog();
            systemLog.setAddress(request.getRemoteAddr());
            systemLog.setNickname(userSuper.getNickname());
            systemLog.setOperateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            systemLog.setStatus(1);
            systemLogService.saveSystemLog(systemLog);

            return new UsernamePasswordAuthenticationToken(userSuper,
                    null, userSuper.getAuthorities());
        }
        //3.2在授权是不是普通user
        /*else if(this.passwordEncoder.matches(inputPassword,user.getPassword())){
            return new UsernamePasswordAuthenticationToken(userSuper,
                    null, user.getAuthorities());
        }*/
        throw new BadCredentialsException("authError");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }



}
