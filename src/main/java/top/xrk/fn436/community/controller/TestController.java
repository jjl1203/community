package top.xrk.fn436.community.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.xrk.fn436.community.util.FileUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    private final ResourceLoader resourceLoader;

    @Autowired
    public TestController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping("/test")
    public String toUpload(){
        return "shangchuan";
    }

    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("/fileUpload")
    public String upload(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file){

        Map<String, Object> map = new HashMap<>();
        // 要上传的目标文件存放路径
        String localPath = null;
        try {
            localPath = ResourceUtils.getURL("D:\\校内实习\\community\\src\\main\\resources\\static\\uploads").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        localPath = request.getServletContext().getRealPath("/")+"uploads\\";
        // 上传成功或者失败的提示
        String msg = "";

        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            msg = "上传成功！";
        }else {
            msg = "上传失败！";
        }
        String imgSrc = "/uploads/" + file.getOriginalFilename();
        // 显示图片
        map.put("msg", msg);
        map.put("fileName", file.getOriginalFilename());
        map.put("imgSrc",imgSrc);
        model.addAttribute("map",map);
        System.out.print(localPath);
        return "shangchuan";
    }
    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("/show")
    public String showPhotos(String fileName, Map<String, Object> map){


        String localPath = "/uploads/" + fileName;

//        try {
//            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
//            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
       System.out.print(localPath);
       map.put("localSrc",localPath);
        return "shangchuan";
    }

}
