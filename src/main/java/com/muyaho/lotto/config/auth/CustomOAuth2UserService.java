package com.muyaho.lotto.config.auth;

import com.muyaho.lotto.config.auth.dto.OAuthAttributes;
import com.muyaho.lotto.config.auth.dto.SessionUser;
import com.muyaho.lotto.domain.UserInfo.UserInfo;
import com.muyaho.lotto.domain.UserInfo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserInfoRepository userInfoRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        UserInfo userInfo = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(userInfo));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(userInfo.getName())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private UserInfo saveOrUpdate(OAuthAttributes attributes){
        UserInfo userInfo = userInfoRepository.findByEmailAndPlatform(attributes.getEmail(), attributes.getPlatform())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userInfoRepository.save(userInfo);

    }
}
