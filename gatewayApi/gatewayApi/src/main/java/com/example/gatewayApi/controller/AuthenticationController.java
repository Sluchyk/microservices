package com.example.gatewayApi.controller;

import com.example.gatewayApi.model.AuthenticationResponse;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorize")
public class AuthenticationController {
    @GetMapping
    public ResponseEntity<AuthenticationResponse> login(@AuthenticationPrincipal OidcUser user,
                                                        Model model,
                                                        @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client){
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .userId(user.getEmail())
                .accessToken(client.getAccessToken().getTokenValue())
                .authority(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .build();
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
