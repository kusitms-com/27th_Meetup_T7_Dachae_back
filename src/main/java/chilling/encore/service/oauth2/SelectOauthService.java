package chilling.encore.service.oauth2;

import chilling.encore.service.kakao.KakaoAuthService;
import chilling.encore.service.kakao.KakaoService;
import chilling.encore.service.oauth2.Oauth2AuthService;
import chilling.encore.service.oauth2.Oauth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SelectOauthService {
    private final KakaoAuthService kakaoAuthService;
    private final KakaoService kakaoService;

    public Oauth2Service selectService(String provider) {
        switch (provider) {
            case "KAKAO" : return kakaoService;
        }
        return null;
    }

    public Oauth2AuthService selectAuth(String provider) {
        switch (provider) {
            case "KAKAO" : return kakaoAuthService;
        }
        return null;
    }
}
