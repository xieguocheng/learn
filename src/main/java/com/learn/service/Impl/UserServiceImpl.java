package com.learn.service.Impl;

import com.learn.dto.UserDTO;
import com.learn.mapper.DeptMapper;
import com.learn.mapper.SchoolMapper;
import com.learn.mapper.UserMapper;
import com.learn.pojo.User;
import com.learn.pojo.UserCourse;
import com.learn.pojo.UserSuper;
import com.learn.service.UserCourseService;
import com.learn.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:42
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public SchoolMapper schoolMapper;
    @Autowired
    public DeptMapper deptMapper;



    @Autowired
    public UserCourseService userCourseService;


    @Override
    public List<User> findAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public UserDTO findUserDTOByUserId(String userId) {
        UserDTO userDTO=new UserDTO();
        User user=userMapper.selectByPrimaryKey(userId);
        userDTO=modelMapper.map(user,UserDTO.class);
        userDTO.setMySchool(schoolMapper.selectByPrimaryKey(user.getSchoolId()));
        if (user.getDept()==null){
            userDTO.setMyDept(null);
        }else {
            userDTO.setMyDept(deptMapper.selectByPrimaryKey(
                    Integer.valueOf(user.getDept())));//注意user.getDept()换成integer
        }

        return userDTO;
    }

    @Override
    public List<User> findUserByCourseId(String courseId) {

        List<UserCourse> userCourseList= userCourseService.findUserCourseByCourseId(courseId);
        //如果没有用户加入本课程直接放回一个空user对象集合
        if(userCourseList.size()==0){
            return new ArrayList<User>();
        }
        //userIds获取所有用户id
        List<String> userIds=new ArrayList<>();
        userCourseList.forEach(userCourse ->{
            userIds.add(userCourse.getUserId());
        });
        //通过userids查询所有用户
        List<User> userList=userMapper.getUserListByUserIds(userIds);
        return userList;
    }

    @Override
    public User findUserByUserId(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        /*Example userExample=new Example(User.class);
        userExample.createCriteria().andEqualTo("username",username);
        List<User> userList=userMapper.selectByExample(userExample);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(userList!=null && userList.size()>0){
            userList.get(0).setAuthorityList(authorities);
            return userList.get(0);
        }*/
        return null;
    }

    @Override
    public User findUserByNumber(String number) {
        Example userExample=new Example(User.class);
        userExample.createCriteria().andEqualTo("number",number);
        List<User> userList=userMapper.selectByExample(userExample);
        if(userList!=null && userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

}
