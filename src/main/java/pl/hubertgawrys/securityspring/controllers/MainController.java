package pl.hubertgawrys.securityspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    Facebook facebook;

    @Autowired
    ConnectionRepository connectionRepository;

    @GetMapping("/")
    public String index(Model model){
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null){
            return "redirect:/connect/facebook";
        }
        String text2 = facebook.feedOperations().getFeed().stream().map(s-> s.getCreatedTime().toString()).collect(Collectors.joining(","));
        String text3 = facebook.feedOperations().getLinks().stream().map(s->s.getMessage()).collect(Collectors.joining());
        String text = facebook.feedOperations().getFeed().stream().map(s -> s.getMessage()).collect(Collectors.joining(","));
  //      String text = facebook.feedOperations().getFeed().stream().map(s -> s.);
  //      String text2 = facebook.likeOperations().like();
        model.addAttribute("someText", text);
        model.addAttribute("someText2", text2);
        model.addAttribute("someText3", text3);

        return "index";
    }


    @GetMapping("/content")
    @ResponseBody
    public String content(){
        return "tajny content";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


/*    @GetMapping("/connect/facebook")
    public String connect(){
        return "connect";
    }*/

}
