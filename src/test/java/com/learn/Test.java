package com.learn;

import com.learn.mapper.*;
import com.learn.service.*;
import com.learn.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/1 11:26
 */

public class Test extends LearnApplicationTests{

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseExperimentMapper courseExperimentMapper;

    @Autowired
    public  QuestionService questionService;

    @Autowired
    public CourseMapper courseMapper;
    @Autowired
    public UserSuperService userSuperService;
    @Autowired
    private CourseTaskService courseTaskService;

    @Autowired
    private UserTaskMapper userTaskMapper;

    @Autowired
    private UserExperimentMapper userExperimentMapper;
    @Autowired
    private UserExperimentService userExperimentService;

    @Autowired
    private HttpServletRequest request;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private CourseExperimentService courseExperimentService;

    @Autowired
    private UserService userService;


    /*@Autowired
    private JavaMailSender mailSender;*/


    // 创建一个线程池，可装载大概3个线程任务的
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);



    @org.junit.Test
    public void test01(){


        /*try {
            MailUtil.sendMessage("1078700658@qq.com",
                    "asdfasd",
                    "adsfa");
        } catch (Exception e) {
            e.printStackTrace();
        }*/





       /* SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1078700658@qq.com");
        mailMessage.setTo("1078700658@qq.com");
        mailMessage.setSubject("【子鹿班课提醒您】");
        mailMessage.setText("生哥帮我买个包子呗");
        mailSender.send(mailMessage);*/

        /*List<String> CreateTimeList=new ArrayList<String>();
        List<User> userList=userService.findAllUser();
        userList.forEach(user -> {
            CreateTimeList.add(new SimpleDateFormat("yyyy-MM").
                    format(user.getCreateTime()));
        });
        Integer time[]={0,0,0,0,0,0,0,0,0,0,0,0,0};
        CreateTimeList.forEach(createTime->{
            switch (createTime){
                case "2019-01":time[1]++;break;
                case "2019-02":time[2]++;break;
                case "2019-03":time[3]++;break;
                case "2019-04":time[4]++;break;
                case "2019-05":time[5]++;break;
                case "2019-06":time[6]++;break;
                case "2019-07":time[7]++;break;
                case "2019-08":time[8]++;break;
                case "2019-09":time[9]++;break;
                case "2019-10":time[10]++;break;
                case "2019-11":time[11]++;break;
                case "2019-12":time[12]++;break;
            }
        });
        for (int i = 0; i < time.length; i++) {
            System.out.println(time[i]);
        }*/









        //System.out.println( userExperimentService.findUserExperiment("1",5));
       // System.out.println(courseExperimentService.findMaxCourExperimentIdByCourseId("1"));

        /*System.out.println(passwordEncoder.encode("123456"));*/

        //Question question = questionMapper.selectByPrimaryKey(2);

       // System.out.println(questionMapper.queryMaxQuestionListId());
        /*Example questionExample=new Example(question.getClass());
        questionExample.createCriteria().andEqualTo("questionId","1");
        Question question1=questionMapper.selectOneByExample(questionExample);
        System.out.println(question1);
        System.out.println(question);
        System.out.println(schoolMapper.selectByPrimaryKey(1));*/

        /*List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(userMapper.getUserListByUserIds(list));*/

        /*System.out.println(questionService.findQuestionByQuestionListId("67,78,56"));*/

       /* List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(courseMapper.getCourseListByCourseIds(list));*/

      /* System.out.println(userSuperService.findUserSuperByUsername("admin"));*/

        /*Date endTime=courseTaskService.findCourseTaskByCourseId("3").get(0).getEndTime();
        Date now =new Date();
        System.out.println(endTime+"----"+now);

        for (int i = 0; i <2 ; i++) {
            if(endTime==null){
                break;
            }else {
                System.out.println(endTime.before(now));
            }
        }*/

        //1614080902422-%E4%BC%8D%E4%B8%96%E9%94%90-UML%E5%AE%9E%E9%AA%8C6-%236.docx
        /*String name= null;
        try {
            name = java.net.URLDecoder.decode("1614080902422-%E4%BC%8D%E4%" +
                    "B8%96%E9%94%90-UML%E5%AE%9E%E9%AA%8C6-%236","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(name);*/

        //查出所有用户的实验路径（姓名/文件名字）

        /*nameList.forEach(name->{
            //获取最后一个“/”的位置，返回int
            int j=name.lastIndexOf("/");
            name=name.substring(j+1);
            //截取“/”后面的所有字符同时进行解码，再封装回去urls
                try {
                    name=java.net.URLDecoder.decode(name.substring(j+1),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            System.out.println(name);
        });*/







    }




}
