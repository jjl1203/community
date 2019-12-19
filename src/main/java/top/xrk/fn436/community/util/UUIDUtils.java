package top.xrk.fn436.community.util;

import java.util.UUID;

/**
 * 生成文件名
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
