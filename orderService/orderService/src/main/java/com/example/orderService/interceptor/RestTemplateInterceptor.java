package com.example.orderService.interceptor;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
@RequiredArgsConstructor
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private final OAuth2AuthorizedClientManager manager;
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization","Bearer " +
                manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("internal-client")
                        .principal("internal")
                        .build()).getAccessToken().getTokenValue());
        return execution.execute(request,body);
    }
}
