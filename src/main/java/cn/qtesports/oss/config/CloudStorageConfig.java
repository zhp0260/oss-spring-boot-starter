package cn.qtesports.oss.config;


import cn.qtesports.oss.service.CloudStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mark
 * @create 2018-09-12 14:21
 * @desc 云存储配置信息
 **/
@Configuration
public class CloudStorageConfig {

    @Bean
    public BaseCloudStorageProperties cloudStorageSetting(){
        return new BaseCloudStorageProperties();
    }

    @Bean("cloudStorageService")
    public CloudStorageService buildLock(BaseCloudStorageProperties cloudStorageSetting) {
        CloudStorageService redisLock = new CloudStorageService.Builder(cloudStorageSetting)
				.build();
		return redisLock;
	}


}
