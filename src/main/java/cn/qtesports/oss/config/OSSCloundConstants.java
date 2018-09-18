package cn.qtesports.oss.config;


/**
 * @author Mark
 * @create 2018-09-17 11:35
 * @desc
 **/
public interface OSSCloundConstants {
    /** 配置文件前缀 */
    public static final String OSS_CONFIG_PREFIX = "oss";
    public static final String OSS_CONFIG_PREFIX_TYPE = "oss.type";
    enum OSSType {
        QClound(1), AliCloud(2), QiniuCloud(3);

        public final int value;
        OSSType(int value) {
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
    }

}
