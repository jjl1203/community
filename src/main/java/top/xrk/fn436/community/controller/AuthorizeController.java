package top.xrk.fn436.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xrk.fn436.community.dto.AccesstokenDTO;
import top.xrk.fn436.community.dto.GithubUser;
import top.xrk.fn436.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(clientid);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(RedirectUri);
        accesstokenDTO.setState(state);

        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);

        System.out.print(accesstoken);
        GithubUser user = githubProvider.getUser(accesstoken);
        //System.out.print(user);
        if(user!=null) {

            //登陆成功存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            return "redirect:/";

        }else{
            return "redirect:/";
        }

    }
}
