package com.muyaho.lotto.domain.LottoInfo.dto;

import lombok.Data;

@Data
public class ManualDTO {
    private int manual0;
    private int manual1;
    private int manual2;
    private int manual3;
    private int manual4;
    private int manual5;

    public int[] getall() {
        int[] list = {manual0,manual1,manual2,manual3,manual4,manual5};
        return list;
    }
}
