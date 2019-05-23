package com.learn.service.Impl;

import com.learn.service.QiNiuService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

/**
 * @Author： XO
 * @Description：
 * @Date： 2018/11/23 15:21
 */

@Service
public class QiNiuServiceImpl implements QiNiuService,InitializingBean {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.Bucket}")
    private String bucket;

    //上传回复
    private StringMap putPolicy;


    /**
     * 获取上传token ，并指定上传策略
     * @return
     */
    private String getUploadToken() {

        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }

    /**
     * 用spring的 InitializingBean 的 afterPropertiesSet来初始化这个方法。
     * 将在所有的属性被初始化后调用。但是会在init前调用
     * 上传回复的returnBody内容
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\"," +
                        "\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, null, getUploadToken());

        //得到上传文件的文件名，并赋值给key作为七牛存储的文件名
        //key = file.getOriginalFilename();
        //重复上传3次试试
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        return response;
    }

    @Override
    public Response uploadFile(InputStream inputStream,String fileName) throws QiniuException {

        Response response = this.uploadManager.put(inputStream, fileName, getUploadToken(), null, null);
        //重复上传3次试试
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
            retry++;
        }
        return response;
    }

    @Override
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(this.bucket, key);
        //重复删除3次试试
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(bucket, key);
        }
        return response;
    }




}
