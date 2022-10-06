package com.muhammet.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /**
         * Gelen isteğin header kısmının içinde Authorization anahtarı varmı var ise içinde ne var bu bilgiyi alıyorun
         * bu bilgi içinde Bearer ile başlayan bir token bilgisi olmalıdır.
         */
        final String authorizationHeader = request.getHeader("Authorization");
        /**
         * gelen string ve request içindeki oturum bilgisini kontrol ederek işleme devam ediyorum.
         */
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ") &&
                SecurityContextHolder.getContext().getAuthentication() == null){
            String token = authorizationHeader.substring(7);
            Optional<Long> authId = jwtTokenManager.getUserId(token);
            if(authId.isPresent()){
                /**
                 * Spring için gerekli olan oturum kullanıcısının tanımlanması gereklidir.
                 * bunu spring UserDetails sınıfından türerilmiş özelleştirilmiş bir kullanıcının
                 * oluşturularak eklenmesi gereklidir.
                 */
                UserDetails userDetails = jwtUserDetails.loadUserByUserId(authId.get());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
