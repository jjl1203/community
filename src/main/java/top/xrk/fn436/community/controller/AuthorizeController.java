package top.xrk.fn436.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xrk.fn436.community.dto.AccesstokenDTO;
import top.xrk.fn436.community.dto.GithubUser;
import top.xrk.fn436.community.provider.GithubProvider;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id("83e1f2e22fc4ff555753");
        accesstokenDTO.setClient_secret("5bca896f9bcfed9481a35ff13bbdf00e9a057e86");
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accesstokenDTO.setState(state);

        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);

        System.out.print(accesstoken);
        GithubUser user = githubProvider.getUser(accesstoken);
        System.out.print(user);
        return "index";
    }
}
