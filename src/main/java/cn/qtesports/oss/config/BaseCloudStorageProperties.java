package cn.qtesports.oss.config;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mark
 * @create 2018-09-17 11:09
 * @desc
 **/
@Data
@ConfigurationProperties(prefix = OSSCloundConstants.OSS_CONFIG_PREFIX)
public class BaseCloudStorageProperties {
    /**
     * 配置的类型
     * {@link OSSCloundConstants.OSSType}
     *
     */
    private int type;
    // accessId/accessKey
    private String accessKey;

    private String secretKey;

    //AppId ，主要是QCloud使用
    private String appId;
    // 前缀
    private String prefix;

    //存储空间名
    private String bucketName;

    //绑定的域名
    private String domain;

    //阿里云EndPoint
    private String endPoint;

    //所属地区 ，主要是QCloud使用
    private String region;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
