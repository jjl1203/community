package top.xrk.fn436.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.xrk.fn436.community.mapper.UserMapper;
import top.xrk.fn436.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;

@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request){

//        try {
//            String path = ResourceUtils.getURL("D:\\校内实习\\community\\src\\main\\resources\\static").getPath();
//            System.out.print(path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }


        return "index";
    }

}
