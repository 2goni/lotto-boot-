package com.muyaho.lotto.controller;

import com.muyaho.lotto.config.auth.LoginUser;
import com.muyaho.lotto.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if(user != null){
            model.addAttribute("userName", user.getName());
            return "main";
        }
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userName", user.getName());
            return "main";
        }
        return "login";
    }
}
