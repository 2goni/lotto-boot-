package com.muyaho.lotto.config.auth.dto;

import com.muyaho.lotto.domain.UserInfo.UserInfo;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserInfo user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
