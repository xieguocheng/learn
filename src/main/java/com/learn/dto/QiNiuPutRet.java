package com.learn.dto;

import lombok.Data;

/**
 * @Author： XO
 * @Description：
 * @Date： 2019/4/12 13:28
 */

@Data
public final class QiNiuPutRet {
    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

}
