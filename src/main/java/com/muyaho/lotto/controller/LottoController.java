package com.muyaho.lotto.controller;

import com.muyaho.lotto.domain.LottoInfo.dto.LottoDTO;
import com.muyaho.lotto.domain.LottoInfo.dto.ManualDTO;
import com.muyaho.lotto.service.LottoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lotto/*")
@RequiredArgsConstructor
@Controller
public class LottoController {

    private final LottoService service;

    @RequestMapping("/main")
    public void show() {
    }
    //main.mustache 호출

    @PostMapping("/cal")
    public void cal(Model model, LottoDTO lottoDTO, ManualDTO manualDTO) {
        model.addAttribute("lotto", service.cal(lottoDTO, manualDTO));
    }
    //service.cal()메소드 사용후 model에 lotto 이름으로 담아 cal.mustache 호출


}

