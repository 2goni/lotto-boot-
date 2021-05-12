package com.muyaho.lotto.domain.UserInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class UserInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column
    private String platform;

    @Builder
    public UserInfo(String name, String email, String picture, String platform) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.platform = platform;
    }

    public UserInfo update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }
}
