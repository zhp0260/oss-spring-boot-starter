package cn.qtesports.oss.service;

import cn.qtesports.oss.config.BaseCloudStorageProperties;
import cn.qtesports.oss.config.OSSCloundConstants;
import cn.qtesports.oss.entity.OSSCommonModel;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Mark
 * @create 2018-09-17 11:41
 * @desc
 **/
@Log4j
public abstract class CloudStorageService {
    /** 云存储配置信息 */
    protected BaseCloudStorageProperties storageProperties;


    /**
     * 文件路径  如果没有prefix，则直接返回当前时间   yyyyMMdd/uuid, 如: 2018/282829930238423084.png
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String path = df.format(new Date()) + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path + suffix;
    }

    /**
     * 文件上传
     * @param data    文件字节数组
     * @param path    文件路径，包含文件名
     * @return        返回http地址
     */
    public abstract OSSCommonModel upload(byte[] data, String path);

    /**
     * 文件上传,不指定路径，按照日期生成
     * @param data     文件字节数组
     * @param suffix   后缀
     * @return         返回http地址
     */
    public abstract OSSCommonModel uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     * @param inputStream   字节流
     * @param path          文件路径，包含文件名
     * @return              返回http地址
     */
    public abstract OSSCommonModel upload(InputStream inputStream, String path);

    /**
     * 文件上传,不指定路径，按照日期生成
     * @param inputStream  字节流
     * @param suffix       后缀
     * @return             返回http地址
     */
    public abstract OSSCommonModel uploadSuffix(InputStream inputStream, String suffix);


    public String getCloudName(){
        if(this.storageProperties.getType() == OSSCloundConstants.OSSType.QClound.getValue()){
            return "腾讯云";
        }else if(this.storageProperties.getType() == OSSCloundConstants.OSSType.AliCloud.getValue()){
            return "阿里云";
        }
        return null;
    }

    public String getProperties(){
        return this.storageProperties.toString();
    }

    /**
     *  the builder
     */
    public static class Builder{
        protected BaseCloudStorageProperties storageProperties;

        public Builder(BaseCloudStorageProperties setting){
            this.storageProperties = setting;
        }

        public CloudStorageService build(){
            if(this.storageProperties.getType() == OSSCloundConstants.OSSType.QClound.getValue()){
                return new QCloundStorageService(storageProperties);
            }else if(this.storageProperties.getType() == OSSCloundConstants.OSSType.AliCloud.getValue()){
                return new AliCloudStorageService(storageProperties);
            }
            return null;
        }
    }
}
