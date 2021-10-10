package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC_DEFAULT = {"/oauth/token", "/h2-console/**"};
    private static final String[] PUBLIC_GET_APPLICATION = {"/cities", "/events"};
    private static final String[] OPERATOR_POST = {"/events"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        //Liberar H2
        if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests()
        .antMatchers(PUBLIC_DEFAULT).permitAll()
        .antMatchers(HttpMethod.GET, PUBLIC_GET_APPLICATION).permitAll()
        .antMatchers(HttpMethod.POST, OPERATOR_POST).hasAnyRole("CLIENT", "ADMIN")
        .anyRequest().hasAnyRole("ADMIN");
    }
}
