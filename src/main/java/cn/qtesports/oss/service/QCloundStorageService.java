package cn.qtesports.oss.service;


import cn.qtesports.oss.config.BaseCloudStorageProperties;
import cn.qtesports.oss.entity.OSSCommonModel;
import cn.qtesports.oss.exception.OSSException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mark
 * @create 2018-09-17 12:15
 * @desc
 **/
public class QCloundStorageService extends CloudStorageService{

    private COSClient client;

    public QCloundStorageService(BaseCloudStorageProperties setting){
        this.storageProperties = setting;
        COSCredentials credentials = new BasicCOSCredentials(this.storageProperties.getAccessKey(),
                this.storageProperties.getSecretKey());

        //初始化客户端配置
        ClientConfig clientConfig = new ClientConfig(new Region(this.storageProperties.getRegion()));

        client = new COSClient(credentials, clientConfig);
    }

    @Override
    public OSSCommonModel upload(byte[] data, String path) {
        //腾讯云必需要以"/"开头
        if(!path.startsWith("/")) {
            path = "/" + path;
        }

        //上传到腾讯云
//        UploadFileRequest request = new UploadFileRequest(storageProperties.getBucketName(), path, data);
//        String response = client.uploadFile(request);
//        JSONObject jsonObject = JSONObject.parseObject(response);
//        if(jsonObject.getInteger("code") != 0) {
//            throw new OSSException("文件上传失败，" + jsonObject.getString("message"));
//        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为 500
        objectMetadata.setContentLength(data.length);
        PutObjectRequest request = new PutObjectRequest(storageProperties.getBucketName(),path, new ByteArrayInputStream(data),objectMetadata);
        PutObjectResult result = client.putObject(request);

        if(StringUtils.isEmpty(result.getETag())){
            throw new OSSException("文件上传失败.");
        }
        return new OSSCommonModel(path, storageProperties.getDomain() + path);
    }

    @Override
    public OSSCommonModel upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new OSSException("上传文件失败", e);
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
