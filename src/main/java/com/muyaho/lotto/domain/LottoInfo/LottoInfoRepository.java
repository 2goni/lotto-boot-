package com.muyaho.lotto.domain.LottoInfo;

import com.muyaho.lotto.domain.UserInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LottoInfoRepository extends JpaRepository<LottoInfo, Long> {
    List<LottoInfo> findTop10ByUserInfoOrderByCreatedDateAsc(UserInfo userInfo);
}
