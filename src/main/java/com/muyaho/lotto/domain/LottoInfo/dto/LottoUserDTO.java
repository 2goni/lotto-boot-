package com.muyaho.lotto.domain.LottoInfo.dto;

import com.muyaho.lotto.domain.UserInfo.UserInfo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class LottoUserDTO {
    private String userName;

    private String email;

    private String createdDate;

    private int num1;

    private int num2;

    private int num3;

    private int num4;

    private int num5;

    private int num6;

    private int luckyNum;

    private String ip;

    private String browser;

    private String device;

    private String useragent;

    @Builder
    public LottoUserDTO(String createdDate,int num1, int num2, int num3, int num4, int num5, int num6, int luckyNum, String ip, String browser, String device,String useragent, UserInfo userInfo) {
        this.userName = userInfo.getName();
        this.email = userInfo.getEmail();
        this.createdDate = createdDate;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.luckyNum = luckyNum;
        this.ip = ip;
        this.browser = browser;
        this.device = device;
        this.useragent = useragent;
    }
}
