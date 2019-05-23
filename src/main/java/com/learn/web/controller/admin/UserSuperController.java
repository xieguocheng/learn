package com.learn.web.controller.admin;

import com.learn.enums.UserSuperStatus;
import com.learn.pojo.UserSuper;
import com.learn.service.UserSuperService;
import com.learn.utils.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/4 21:36
 */

@Controller
public class UserSuperController {

    @Autowired
    private UserSuperService userSuperService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 查看所有管理员
     * @param model
     * @return
     */
    @RequestMapping(value="admin/userSuper/userSuperList")
    public String userSuperList(Model model){
        List<UserSuper> userSuperList=userSuperService.findAllUserSuper();
        model.addAttribute("userSuperList", userSuperList);
        return "admin/user_super/user-super-list";
    }


    /**
     * 管理员停用
     * @param id
     * @return
     */
    @PostMapping(value="admin/userSuper/userSuperStop")
    @ResponseBody
    public ApiResponse userSuperStop(Integer id){
        try{
            UserSuper userSuper=userSuperService.findUserSuperById(id);
            userSuper.setStatus(UserSuperStatus.STATUS_NO.getValue());
            userSuperService.updateUserSuper(userSuper);

            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }

    /**
     * 管理员启用
     * @param id
     * @return
     */
    @PostMapping(value="admin/userSuper/userSuperStart")
    @ResponseBody
    public ApiResponse userSuperStart(Integer id){
        try{
            UserSuper userSuper=userSuperService.findUserSuperById(id);
            userSuper.setStatus(UserSuperStatus.STATUS_YES.getValue());
            userSuperService.updateUserSuper(userSuper);
            return ApiResponse.ofSuccess(null);
        }catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }
    }

    /**
     * 新增管理页面
     * @return
     */
    @RequestMapping(value="admin/userSuper/AddPage")
    public String userSuperAddPage(){
        return "admin/user_super/user-super-add";
    }

    /**
     * 新增管理
     * @param userSuper
     */
    @PostMapping(value="admin/userSuper/add")
    @ResponseBody
    public ApiResponse userSuperAdd(UserSuper userSuper){
        userSuper.setPassword(passwordEncoder.encode(userSuper.getPassword()));
        userSuperService.saveUserSuper(userSuper);

        return ApiResponse.ofSuccess(null);
    }



    /*

    @GetMapping(value="userHrShow/{userHrId}")
    public String userHrShow(Model model,@PathVariable("userHrId") Integer userHrId){
        //查询
        UserHr userHr=userHrService.findUserHrById(userHrId);
        //传递
        model.addAttribute("userHr", userHr);
        //跳转
        return "user_hr/user-hr-show";
    }


    @PostMapping(value="userHrDelete")
    public void userHrDelete(HttpServletResponse response,Integer userHrId){
        PrintWriter out=null;

        try{
            out=response.getWriter();
            userHrService.deleteUserHrById(userHrId);
            //删除成功，向客户端输出1
            out.write("{\"status\":1}");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="changePasswordPage/{userHrId}")
    public String changePasswordPage(Model model,@PathVariable("userHrId") Integer userHrId){
        UserHr userHr=userHrService.findUserHrById(userHrId);
        model.addAttribute("userHr", userHr);
        return "user_hr/change-password";
    }

    @PostMapping(value="changePassword")
    public void changePassword(HttpServletResponse response,Integer userHrId,String newpassword){
        PrintWriter out=null;

        try{
            out=response.getWriter();
            UserHr userHr=userHrService.findUserHrById(userHrId);
            //加密
            userHr.setPassword(new MD5Util().getMD5ofStr(newpassword));
            userHrService.updateUserHr(userHr);

            //修改成功，向客户端输出1
            out.write("{\"status\":1}");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value="userHrEditPage/{userHrId}")
    public String userHrEditPage(Model model,@PathVariable("userHrId") Integer userHrId){
        UserHr userHr=userHrService.findUserHrById(userHrId);
        model.addAttribute("userHr", userHr);
        return "user_hr/user-hr-edit";
    }

    @RequestMapping(value="userHrPermission")
    public String userHrPermission(Model model){
        List<UserHr> userHrList=userHrService.findAllNormalUserHr();
        model.addAttribute("userHrList", userHrList);
        return "user_hr/user-permission-list";
    }

    @PostMapping(value="userHrStart")
    public void userHrStart(Integer userHrId,HttpServletResponse response){
        PrintWriter out=null;
        try{
            out=response.getWriter();
            UserHr userHr=userHrService.findUserHrById(userHrId);
            userHr.setStatus(2);
            userHrService.updateUserHr(userHr);
            out.write("{\"status\":1}");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value="userHrEdit")
    public void userHrEdit(UserHr userHr,HttpServletResponse response,HttpServletRequest request){

        PrintWriter out=null;
        try{
            out=response.getWriter();

            //照片
            MultipartFile items =  ((MultipartHttpServletRequest) request).getFile("image");
            if(items.getOriginalFilename()==null||"".equals(items.getOriginalFilename())){
                out.write("{\"status\":-1}");
                return;
            }

            //windows上传图片D:\nginx\html\oas\interviewerImg
            //linux上传图片/usr\local\nginx\html\oas\interviewerImg
            //面试官的图片
            File picture = null;
            String date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //String path = "D:/nginx/html/oas/interviewerImg";
            String path = "/usr/local/nginx/html/oas/userHrImg";
			*//*File dir = new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}*//*

            //文件后缀名
            String fileName = items.getOriginalFilename();
            int i =fileName.lastIndexOf(".");
            String suffix=fileName.substring(i, fileName.length());
            picture=new File(path+"/","userHr"+date+suffix);


            //开始上传
            items.transferTo(picture);
            //设置访问权限
            Runtime.getRuntime().exec("chmod 755 " + "/usr/local/nginx/html/oas/userHrImg/"+picture.getName());
            System.out.println(picture.getName());

            UserHr existUserHr=userHrService.findUserHrById(userHr.getId());

            existUserHr.setEmail(userHr.getEmail());
            existUserHr.setTelephone(userHr.getTelephone());
            existUserHr.setHrIntro(userHr.getHrIntro());
            existUserHr.setSex(userHr.getSex());
            existUserHr.setNickname(userHr.getNickname());
            existUserHr.setUsername(userHr.getUsername());
            existUserHr.setHrImg(picture.getName());

            //修改记录
            userHrService.updateUserHr(existUserHr);

            //修改成功，向客户端输出1
            out.write("{\"status\":1}");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }*/



}
