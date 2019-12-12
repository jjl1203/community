package top.xrk.fn436.community.controller.hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.xrk.fn436.community.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/exit")
    public String delToken(String accountId,
                           HttpServletRequest request){
        request.getSession().removeAttribute("user");
        String token = UUID.randomUUID().toString();
        //System.out.print(token+"    "+accountId);
        userMapper.updateToken(token,accountId);
        return "redirect:/";
    }
}
