package com.bumsoap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Autowired
    private Environment environment;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity)
        throws  Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/secured").authenticated()
                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        var github = githubClientRegistration();
        var facebook = facebookClientRegistration();
        return new InMemoryClientRegistrationRepository(github, facebook);
    }

    private ClientRegistration githubClientRegistration() {
        String client_id = environment.getProperty("CLIENT_ID_GITHUB");
        String client_secret = environment.getProperty("CLIENT_SECRET_GITHUB");

        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId(client_id)
                .clientSecret(client_secret)
                .build();
    }

    private ClientRegistration facebookClientRegistration() {
        String client_id = environment.getProperty("CLIENT_ID_FACEBOOK");
        String client_secret = environment.getProperty("CLIENT_SECRET_FACEBOOK");

        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
                .clientId(client_id)
                .clientSecret(client_secret)
                .build();
    }
}
