package com.muyaho.lotto.domain.LottoInfo;

import com.muyaho.lotto.domain.UserInfo.BaseTimeEntity;
import com.muyaho.lotto.domain.UserInfo.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class LottoInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lottoInfoId;

    @Column(nullable = false)
    private int num1;

    @Column(nullable = false)
    private int num2;

    @Column(nullable = false)
    private int num3;

    @Column(nullable = false)
    private int num4;

    @Column(nullable = false)
    private int num5;

    @Column(nullable = false)
    private int num6;

    @Column(nullable = false)
    private int luckyNum;

    private String ip;

    private String browser;

    private String device;

    private String useragent;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    @Builder
    public LottoInfo(int num1, int num2, int num3, int num4, int num5, int num6, int luckyNum, String ip, String browser, String device, String useragent, UserInfo userInfo) {
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
        this.userInfo = userInfo;
    }
}
