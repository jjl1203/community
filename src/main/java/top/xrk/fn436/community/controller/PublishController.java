package top.xrk.fn436.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xrk.fn436.community.mapper.QuesstionMapper;
import top.xrk.fn436.community.mapper.UserMapper;
import top.xrk.fn436.community.model.Question;
import top.xrk.fn436.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuesstionMapper quesstionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            Question question,
            HttpServletRequest request,
            Model model
    ){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user!=null) {
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            quesstionMapper.create(question);
        }else{

            model.addAttribute("error","用户未登录!");
            return "publish";
        }
        return "redirect:/";

    }
}
