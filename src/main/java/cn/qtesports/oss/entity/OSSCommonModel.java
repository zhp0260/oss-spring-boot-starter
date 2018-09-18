package cn.qtesports.oss.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mark
 * @create 2018-09-17 17:36
 * @desc OSSObject对象,包含文件上传后的路径，与访问路径
 **/
@Data
public class OSSCommonModel {

    //相对路径
    /***
     * 文件存储相对路径
     */
    private String fileUrl;

    /**
     * 文件可以访问的绝对路径
     */
    private String accessUrl;


    public OSSCommonModel(){

    }

    public OSSCommonModel(String fileUrl, String accessUrl){
        this.fileUrl = fileUrl;
        this.accessUrl = accessUrl;
    }
}
