package top.xrk.fn436.community.controller;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Controller
public class ImageController {

    @RequestMapping("/upload")
    @ResponseBody
    public String file1(HttpServletRequest request, @RequestParam("upload") MultipartFile upload) throws Exception {

        System.out.print("文件上传...");

        //上传位置
        String path = ResourceUtils.getURL("D:\\校内实习\\community\\src\\main\\resources\\static\\uploads").getPath();
//        String path = "";
//        String path = "/photos";
        //判断路径是否存在
        File file = new File(path);
        System.out.print(path);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
        String filename = upload.getOriginalFilename();

        //String uuid = UUID.randomUUID().toString();
        //filename = uuid + filename;

        upload.transferTo(new File(path,filename));

        return "index";


    }


}
