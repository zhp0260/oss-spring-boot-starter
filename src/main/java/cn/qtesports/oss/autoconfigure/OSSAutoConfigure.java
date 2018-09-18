package cn.qtesports.oss.autoconfigure;

import cn.qtesports.oss.config.BaseCloudStorageProperties;
import cn.qtesports.oss.config.OSSCloundConstants;
import cn.qtesports.oss.service.CloudStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Mark
 * @create 2018-09-18 12:24
 * @desc OSS自动配置
 **/

@EnableConfigurationProperties({BaseCloudStorageProperties.class})
@ConditionalOnProperty(value = OSSCloundConstants.OSS_CONFIG_PREFIX_TYPE)
public class OSSAutoConfigure {
    @Bean
    @ConditionalOnProperty(value = OSSCloundConstants.OSS_CONFIG_PREFIX_TYPE)
    @ConditionalOnMissingBean
    public BaseCloudStorageProperties cloudStorageSetting(){
        return new BaseCloudStorageProperties();
    }

    @Bean("cloudStorageService")
    @ConditionalOnProperty(value = OSSCloundConstants.OSS_CONFIG_PREFIX_TYPE)
    @ConditionalOnMissingBean
    public CloudStorageService buildLock(BaseCloudStorageProperties cloudStorageSetting) {
        CloudStorageService redisLock = new CloudStorageService.Builder(cloudStorageSetting)
                .build();
        return redisLock;
    }

}
