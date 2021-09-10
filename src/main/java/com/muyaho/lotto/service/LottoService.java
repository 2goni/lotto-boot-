package com.muyaho.lotto.service;

import java.time.LocalDate;

import java.util.*;

import com.muyaho.lotto.config.auth.dto.SessionUser;
import com.muyaho.lotto.domain.LottoInfo.LottoInfo;
import com.muyaho.lotto.domain.LottoInfo.LottoInfoRepository;
import com.muyaho.lotto.domain.LottoInfo.dto.LottoDTO;
import com.muyaho.lotto.domain.LottoInfo.dto.LottoUserDTO;
import com.muyaho.lotto.domain.LottoInfo.dto.ManualDTO;
import com.muyaho.lotto.domain.UserInfo.UserInfo;
import com.muyaho.lotto.domain.UserInfo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoInfoRepository lottoInfoRepository;
    private final HttpService httpService;
    private final UserInfoRepository userInfoRepository;

    public int[] cal(LottoDTO lottoDTO, ManualDTO manualDTO, HttpServletRequest request, SessionUser user) {
        LocalDate dayday = LocalDate.now();
        long num = Math.round(dayday.getYear() * dayday.lengthOfMonth() * dayday.getDayOfYear() + lottoDTO.getLuckyNum() * lottoDTO.getDay() * lottoDTO.getMonth() * Math.pow(lottoDTO.getYear(), 2) * 1.618);
        int[] auto = getLotto(num);
        int[] lotto = manualDTO.getall();
        for (int i = 0; i < 6; i++) {
            if (lotto[i] == 0) {
                lotto[i] = check(auto[i], lotto);
            }
        }
        lotto = sort(lotto);
        lottoInfoRepository.save(getLottoUserInfo(lotto, request, user, lottoDTO.getLuckyNum()));
        return sort(lotto);
    }

    public LottoInfo getLottoUserInfo(int[] lotto, HttpServletRequest request, SessionUser user, int luckyNum) {
        LottoInfo lottoInfo = LottoInfo.builder()
                .ip(httpService.getIp(request))
                .browser(httpService.getBrowser(request))
                .device(httpService.getDevice(request))
                .useragent(request.getHeader("user-agent"))
                .num1(lotto[0])
                .num2(lotto[1])
                .num3(lotto[2])
                .num4(lotto[3])
                .num5(lotto[4])
                .num6(lotto[5])
                .luckyNum(luckyNum)
                .userInfo(userInfoRepository.findByEmailAndPlatform(user.getEmail(), user.getPlatform()).get())
                .build();
        return lottoInfo;
    }

    public int[] getLotto(long num) {
        int[] lotto = new int[6];
        long rd = num;
        for (int i = 0; i < lotto.length; i++) {
            lotto[i] = check(getRandom(rd), lotto);
            rd = (long) (rd * 1.618);
        }
        return lotto;
    }

    public int getRandom(long num) {
        Random random = new Random(num);
        int rd = random.nextInt(45) + 1;
        return rd;
    }

    public int check(int num, int[] lotto) {
        for (int i = 0; i < lotto.length; i++) {
            if (lotto[i] == num) {
                num = getRandom(Math.round(num * 1.618));
                check(num, lotto);
            }
        }
        return num;
    }

    public int[] sort(int[] lotto) {
        for (int i = 0; i < lotto.length; i++) {
            for (int j = i; j < lotto.length; j++) {
                if (lotto[i] > lotto[j]) {
                    int tmp = lotto[i];
                    lotto[i] = lotto[j];
                    lotto[j] = tmp;
                }
            }
        }
        return lotto;
    }

    public List<LottoUserDTO> getList(SessionUser user) {
        List<LottoUserDTO> lottoUserDTOList = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        if (userInfoRepository.findByEmailAndPlatform(user.getEmail(), user.getPlatform()).isPresent()) {
            userInfo = userInfoRepository.findByEmailAndPlatform(user.getEmail(), user.getPlatform()).get();
            List<LottoInfo> lottoInfoList = lottoInfoRepository.findTop10ByUserInfoOrderByCreatedDateAsc(userInfo);
            for (LottoInfo lottoInfo : lottoInfoList) {
                lottoUserDTOList.add(LottoUserDTO.builder()
                        .createdDate(lottoInfo.getCreatedDate())
                        .num1(lottoInfo.getNum1())
                        .num2(lottoInfo.getNum2())
                        .num3(lottoInfo.getNum3())
                        .num4(lottoInfo.getNum4())
                        .num5(lottoInfo.getNum5())
                        .num6(lottoInfo.getNum6())
                        .luckyNum(lottoInfo.getLuckyNum())
                        .browser(lottoInfo.getBrowser())
                        .ip(lottoInfo.getIp())
                        .useragent(lottoInfo.getUseragent())
                        .device(lottoInfo.getDevice())
                        .userInfo(userInfo)
                        .build());
            }
            return lottoUserDTOList;
        }
        return null;
    }

}
