package com.learn.service.Impl;

import com.learn.enums.UserSuperStatus;
import com.learn.mapper.UserSuperMapper;
import com.learn.pojo.UserSuper;
import com.learn.service.UserSuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/4 21:36
 */

@Service
public class UserSuperServiceImpl implements UserSuperService {


    @Autowired
    public UserSuperMapper userSuperMapper;


    @Override
    public UserSuper findUserSuperByUsername(String username) {

        Example userSuperExample=new Example(UserSuper.class);
        userSuperExample.createCriteria().andEqualTo("username",username);
        List<UserSuper> userSuperList=userSuperMapper.selectByExample(userSuperExample);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if(userSuperList!=null && userSuperList.size()>0){
            userSuperList.get(0).setAuthorityList(authorities);
            return userSuperList.get(0);
        }

       /* //java8特性
        roles.forEach(role -> authorities.add(
                new SimpleGrantedAuthority("ROLE_" + role.getName())));*/
        return null;
    }

    @Override
    public UserSuper findUserSuperById(Integer id) {
        return userSuperMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserSuper findUserSuperByTelephone(String telephone) {
        Example userSuperExample=new Example(UserSuper.class);
        userSuperExample.createCriteria().andEqualTo("telephone",telephone);
        List<UserSuper> userSuperList=userSuperMapper.selectByExample(userSuperExample);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        if(userSuperList!=null && userSuperList.size()>0){
            userSuperList.get(0).setAuthorityList(authorities);
            return userSuperList.get(0);
        }
        return null;
    }

    @Override
    public List<UserSuper> findAllUserSuper() {

        /*Example userSuperExample=new Example(UserSuper.class);
        userSuperExample.createCriteria().andIsNotNull("id");
        return userSuperMapper.selectByExample(userSuperExample);*/
        return userSuperMapper.selectAll();
    }

    @Override
    public void deleteUserSuperById(Integer id) {
        userSuperMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void updateUserSuper(UserSuper userSuper) {
        userSuperMapper.updateByPrimaryKey(userSuper);
    }

    @Override
    public void saveUserSuper(UserSuper userSuper) {
        userSuperMapper.insertSelective(userSuper);
    }

    @Override
    public List<UserSuper> findAllNormalUserSuper() {
        //查询所有正常超级管理员
        Example userSuperExample=new Example(UserSuper.class);
        userSuperExample.createCriteria().
                andEqualTo("status",UserSuperStatus.STATUS_YES.getValue());
        return userSuperMapper.selectByExample(userSuperExample);
    }
}
