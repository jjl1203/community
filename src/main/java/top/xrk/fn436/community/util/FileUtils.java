package top.xrk.fn436.community.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具包
 */
public class FileUtils {

    /**
     *
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName){


        // 生成新的文件名
        //fileName = UUIDUtils.getUUID() + "_" + fileName;

        //使用原文件名

        File dest = new File(path);

        //判断文件父目录是否存在
//        if(!dest.getParentFile().exists()){
//            dest.getParentFile().mkdir();
//        }
        if (!dest.exists()) {
            //创建文件夹
            dest.mkdirs();
        }

        try {
            //保存文件
            file.transferTo(new File(path,fileName));
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }


}
