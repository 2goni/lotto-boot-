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

    private String ip;

    private String browser;

    private String device;

    private String useragent;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

}
