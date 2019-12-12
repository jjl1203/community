package top.xrk.fn436.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xrk.fn436.community.dto.AccesstokenDTO;
import top.xrk.fn436.community.dto.GithubUser;
import top.xrk.fn436.community.mapper.UserMapper;
import top.xrk.fn436.community.model.User;
import top.xrk.fn436.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {


    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(clientid);
        accesstokenDTO.setClient_secret(clientSecret);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedirect_uri(RedirectUri);
        accesstokenDTO.setState(state);

        String accesstoken = githubProvider.getAccesstoken(accesstokenDTO);

        //System.out.print(accesstoken);
        GithubUser githubUseruser = githubProvider.getUser(accesstoken);
        //System.out.print(user);
        if(githubUseruser!=null) {
            String token = UUID.randomUUID().toString();
            String AccountId = String.valueOf(githubUseruser.getId());
            User useristrue = userMapper.findByid(AccountId);
            if(useristrue!=null){
                response.addCookie(new Cookie("token",token));
                userMapper.updateToken(token,AccountId);
                return "redirect:/";
            }
            User user = new User();
            user.setToken(token);
            user.setName(githubUseruser.getLogin());
            user.setAccountId(String.valueOf(githubUseruser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

            response.addCookie(new Cookie("token",token));

            //登陆成功存入session
//            HttpSession session = request.getSession();
//            session.setAttribute("user",githubUseruser);

            return "redirect:/";

        }else{
            return "redirect:/";
        }

    }
}
