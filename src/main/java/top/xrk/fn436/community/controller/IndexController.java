package top.xrk.fn436.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xrk.fn436.community.bean.User;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
