package com.muyaho.lotto.controller;

import com.muyaho.lotto.config.auth.LoginUser;
import com.muyaho.lotto.config.auth.dto.SessionUser;
import com.muyaho.lotto.domain.LottoInfo.dto.LottoDTO;
import com.muyaho.lotto.domain.LottoInfo.dto.ManualDTO;
import com.muyaho.lotto.domain.UserInfo.UserInfo;
import com.muyaho.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class LottoController {

    private final LottoService service;


    @PostMapping("/lotto")
    public String lotto(@LoginUser SessionUser user, Model model, LottoDTO lottoDTO, ManualDTO manualDTO, HttpServletRequest request) {
        model.addAttribute("lotto", service.cal(lottoDTO, manualDTO, request, user));
        return "lotto";

    }

    @RequestMapping("/lottoList")
    public String lottoList(@LoginUser SessionUser user, Model model){
        if(service.getList(user) == null){
            return "error";
        }
        model.addAttribute("lottoList", service.getList(user));
        return "lottoList";
    }
}


