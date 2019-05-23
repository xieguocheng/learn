package com.learn.utils;


import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/5/9 19:04
 */

public class MyStringUtils {

    /**
     * 16进制的字符串转化为utf-8格式的字符串
     *
     * @param s
     * @return
     */
    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    public static void main(String[] args) {
        String name = null;
        try {
            //查出所有用户的实验路径（姓名/文件名字）
            String []urls={"http://pr2t5kesu.bkt.clouddn.com/1/1/1614080902422-%E4%BC%8D%E4%B8%96%E9%94%90-UML%E5%AE%9E%E9%AA%8C6-%236.docx"
                    ,"http://pr2t5kesu.bkt.clouddn.com/1/1/1614080902441-%E8%B0%A2%E5%9B%BD%E5%9F%8E-UML%E5%AE%9E%E9%AA%8C2-%232.docx"};

            //urls文件名字截取和urls字符编码urldecoder解码,返回urls。

            for(int j=0;j<urls.length;j++) {
                //获取最后一个“/”的位置，返回int
                int i=urls[j].lastIndexOf("/");
                //截取“/”后面的所有字符同时进行解码，再封装回去urls
                //urls[j]=urls[j].substring(i+1);
                urls[j]=java.net.URLDecoder.decode(urls[j].substring(i+1),"UTF-8");
                System.out.println(urls[j]);
                //url=java.net.URLDecoder.decode(url,"UTF-8");
            }
            for (String url : urls) {
                System.out.println(url);
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
