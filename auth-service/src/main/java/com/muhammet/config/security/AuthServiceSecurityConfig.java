package com.muhammet.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class AuthServiceSecurityConfig {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }

    /**
     * Sunucunuza gelen tüm isteklerin konfigurasyonunu burada yapıyorsunuz
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        /**
         * Olmasını istediğimiz tüm ayarlamaları burada yapıp
         */
        /**
         * 1- tüm isteklerde csrf kapalı olsun.
         */
        http.csrf().disable();
        /**
         * 1- anyRequest -> gelen tüm istekler
         * 2- authenticated -> kimlik doğrulaması yapılsın
         * 3- pernitAll -> kimlik doğrulamasına tabi tutma.
         */
        http.authorizeRequests()
                .antMatchers("/v3/api-docs/**","/swagger-ui/**","/v1/api/auth/dologin","/v1/api/auth/register").permitAll()
                .anyRequest().authenticated();
        /**
         * 1- Eğer kimlik doğrulaması gerekiyor ise bunu spring in login formu üzerinden yap.
         */
        //http.formLogin();
        /**
         * JWT ile gelen isterklerin doğrulamasının yapılasını talep ettiğimiz kısım
         * burada gelen isteğin cevabı verilmeden önce filter araya sokularak
         * doğrulama mekanizması buraya işlenmelidir.
         */
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        /*
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/v3/api/docs/**","/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin();
        */
        /**
         * gelen isteği yeni kurguladığımız şekli ile spring e iletiyoruz.
         */
        return http.build();
    }
}
