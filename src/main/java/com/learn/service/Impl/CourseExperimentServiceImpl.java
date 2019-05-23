package com.learn.service.Impl;

import com.learn.dto.CourseExperimentDTO;
import com.learn.dto.UserExperimentDTO;
import com.learn.mapper.CourseExperimentMapper;
import com.learn.pojo.CourseExperiment;
import com.learn.service.CourseExperimentService;
import com.learn.service.UserExperimentService;
import com.learn.utils.UrlFilesToZip;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/5 18:40
 */

@Service
@Slf4j
public class CourseExperimentServiceImpl implements CourseExperimentService {


    @Autowired
    public CourseExperimentMapper courseExperimentMapper;

    //可以用于属性复制
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public UserExperimentService userExperimentService;


    @Override
    public List<CourseExperiment> findAllcourseExperiment() {
        return courseExperimentMapper.selectAll();
    }

    @Override
    public List<CourseExperiment> findCourseExperimentByCourseId(String courseId) {
        Example courseExperimentExample = new Example(CourseExperiment.class);
        courseExperimentExample.createCriteria().andEqualTo("courseId", courseId);
        List<CourseExperiment> courseExperimentList = courseExperimentMapper.selectByExample(courseExperimentExample);
        //如果查询到空返回一个空对象
        if(courseExperimentList.size()==0){
            return new ArrayList<CourseExperiment>();
        }
        return courseExperimentList;
    }

    @Override
    public List<CourseExperimentDTO> findCourseExperimentDTOWithUser(String courseId, String userId) {
        Example courseExperimentExample = new Example(CourseExperiment.class);
        courseExperimentExample.createCriteria().andEqualTo("courseId", courseId);
        List<CourseExperiment> courseExperimentList = courseExperimentMapper.selectByExample(courseExperimentExample);
        //如果查询到空返回一个空对象
        if(courseExperimentList.size()==0){
            return new ArrayList<CourseExperimentDTO>();
        }
        //新建courseExperimentDTOList映射对应属性
        List<CourseExperimentDTO> courseExperimentDTOList=new ArrayList<CourseExperimentDTO>();
        courseExperimentList.forEach(courseExperiment -> {
            CourseExperimentDTO courseExperimentDTO= modelMapper.map(courseExperiment,CourseExperimentDTO.class);
            courseExperimentDTO.setUserExperiment(userExperimentService.
                    findUserExperiment(userId,courseExperiment.getCourExperimentId()));
            courseExperimentDTOList.add(courseExperimentDTO);
        });
        return courseExperimentDTOList;
    }

    @Override
    public CourseExperiment findCourseExperimentBycourExperimentId(String courExperimentId) {

        Example courseExperimentExample = new Example(CourseExperiment.class);
        courseExperimentExample.createCriteria().andEqualTo("courExperimentId", courExperimentId);
        List<CourseExperiment> courseExperimentList = courseExperimentMapper.selectByExample(courseExperimentExample);
        //如果查询到空返回一个空对象
        if(courseExperimentList.size()==0){
            return new CourseExperiment();
        }
        return courseExperimentList.get(0);
    }

    @Override
    public void updateCourseExperiment(CourseExperiment courseExperiment) {
        courseExperimentMapper.updateByPrimaryKey(courseExperiment);
    }

    private static final Logger logger = LoggerFactory.getLogger(UrlFilesToZip.class);
    @Override
    public void courseExperimentExport(HttpServletResponse response,String courExperimentId) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);
            UrlFilesToZip s = new UrlFilesToZip();
//---------------------------------------------------------------
            //【1】设置实验名字
            CourseExperiment courseExperiment=findCourseExperimentBycourExperimentId(courExperimentId);
            String filename = new String((courseExperiment.getTitle()+".zip").getBytes("UTF-8"), "ISO8859-1");//控制文件名编码
            //【2】查出所有用户的实验路径（姓名/文件名字）
            List<UserExperimentDTO> userExperimentDTOList=userExperimentService.
                    findUserExperimentDTOByCourExperimentId(courExperimentId);
            //【3】获取一次实验下所有学生的实验下载路径
            List<String> pathList = new ArrayList<String>();
            userExperimentDTOList.forEach(userExperimentDTO -> {
                String userUrl=userExperimentDTO.getUserUrl();
                if(userUrl!=null&&!userUrl.equals("")){     //判断是否有没上交实验的，userurl可能为空
                    String []url=userUrl.split(",");
                    for(int i=0;i<url.length;i++){
                        pathList.add(url[i]);
                    }
                }
            });
            pathList.forEach(path->{
                log.info(path);
            });
            //【4】设置存放一门实验下所有学生实验的打包下载名称，复制pathList，进行字符截取和转码
            List<String> nameList=new ArrayList<String>();//复制urlList,用于转码
            for(int i=0;i<pathList.size();i++) {
                nameList.add(pathList.get(i));//开始复制一个list的内容到另外一个list
            }
            for(int i=0;i<nameList.size();i++){
                try {
                    //TODO
                    String str=java.net.URLDecoder.decode(nameList.get(i),"UTF-8");
                    int j=str.lastIndexOf("/");
                    nameList.set(i,str.substring(j+1));

                    //int j=nameList.get(i).lastIndexOf("/"); //获取最后一个“/”的位置，返回int
                    //nameList.set(i,java.net.URLDecoder.decode(nameList.get(i).substring(j+1),"UTF-8"));//截取“/”后面的所有字符同时进行解码
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            //【5】对下载路径pathlist和下载名称namelist进行处理
            for(int i=0;i<pathList.size();i++){
                zos.putNextEntry(new ZipEntry(nameList.get(i)));//设置名字
                byte[] bytes = s.getImageFromURL(pathList.get(i));//设置下载路径
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }
//---------------------------------------------------------------
            zos.close();
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名
            OutputStream os = response.getOutputStream();
            os.write(bos.toByteArray());
            os.close();
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException", ex);
        } catch (Exception ex) {
            logger.error("Exception", ex);
        }
    }

    @Override
    public Integer findMaxCourExperimentIdByCourseId(String courseId) {
        return courseExperimentMapper.selectMaxCourExperimentIdByCourseId(courseId);
    }

    @Override
    public void saveCourseExperiment(CourseExperiment courseExperiment) {
        courseExperimentMapper.insertSelective(courseExperiment);
    }
}
