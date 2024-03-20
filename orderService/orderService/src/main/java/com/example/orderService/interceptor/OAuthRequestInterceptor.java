package com.example.orderService.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@RequiredArgsConstructor
@Configuration
public class OAuthRequestInterceptor implements RequestInterceptor {
    private final OAuth2AuthorizedClientManager manager;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization","Bearer "+
                manager.authorize(OAuth2AuthorizeRequest
                        .withClientRegistrationId("internal-client")
                                .principal("internal")
                        .build()).getAccessToken().getTokenValue());
    }
}
