package top.xrk.fn436.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AframeController {

    @RequestMapping("/aframe")
    public String Aframe(){

        return "aframe";
    }

    @RequestMapping("/testframe")
    public String Test() {
        return "test";
    }
}
