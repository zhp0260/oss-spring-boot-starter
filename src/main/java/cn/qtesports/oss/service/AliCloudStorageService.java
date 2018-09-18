package cn.qtesports.oss.service;

import cn.qtesports.oss.config.BaseCloudStorageProperties;
import cn.qtesports.oss.entity.OSSCommonModel;
import cn.qtesports.oss.exception.OSSException;
import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Mark
 * @create 2018-09-17 13:44
 * @desc
 **/
public class AliCloudStorageService extends CloudStorageService {

    private OSSClient client;


    public AliCloudStorageService(BaseCloudStorageProperties setting){
        this.storageProperties = setting;
        client = new OSSClient(storageProperties.getEndPoint(), storageProperties.getAccessKey(),
                storageProperties.getSecretKey());
    }

    @Override
    public OSSCommonModel upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public OSSCommonModel upload(InputStream inputStream, String path) {
        OSSCommonModel commonModel = null;
        try {
            client.putObject(storageProperties.getBucketName(), path, inputStream);
            if(!path.startsWith("/")) {
                path = "/" + path;
            }
            return new OSSCommonModel(path, storageProperties.getDomain() + path);

        } catch (Exception e){
            throw new OSSException("上传文件失败，请检查配置信息", e);
        }
    }

    @Override
    public OSSCommonModel uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(storageProperties.getPrefix(), suffix));
    }

    @Override
    public OSSCommonModel uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(storageProperties.getPrefix(), suffix));
    }

}
