package com.muyaho.lotto.controller;

import com.muyaho.lotto.domain.LottoInfo.dto.LottoDTO;
import com.muyaho.lotto.domain.LottoInfo.dto.ManualDTO;
import com.muyaho.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lotto")
@RequiredArgsConstructor
@Controller
public class LottoController {

    private final LottoService service;


    @PostMapping("/cal")
    public String cal(Model model, LottoDTO lottoDTO, ManualDTO manualDTO) {
        model.addAttribute("lotto", service.cal(lottoDTO, manualDTO));
        return "cal";
    }

}


