package com.learn.service;

import com.learn.pojo.UserSuper;

import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/4 21:37
 */



public interface  UserSuperService {

    //通过username查询UserSuper
    public UserSuper findUserSuperByUsername(String username);

    //通过id查询UserSuper
    public UserSuper findUserSuperById(Integer id);

    //通过电话号码查询一个用户
    public UserSuper findUserSuperByTelephone(String telephone);

    //查询所有UserSuper
    public List<UserSuper> findAllUserSuper();

    //跟新UserSuper
    public void updateUserSuper(UserSuper userSuper);

    //保存新的UserSuper
    public void saveUserSuper(UserSuper userSuper);

    //通过id删除UserSuper
    public void deleteUserSuperById(Integer id);

    //查询所有正常的UserSuper
    public List<UserSuper> findAllNormalUserSuper();




}
