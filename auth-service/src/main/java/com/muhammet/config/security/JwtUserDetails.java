package com.muhammet.config.security;

import com.muhammet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    AuthService authService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUserId(Long authId) throws UsernameNotFoundException {
        /**
         * id si verilen kullanıcının var olup olmadığına bakılmalıdır.
         */
        boolean isUserExist = authService.findById(authId).getId() != null;
        if(isUserExist){
            /**
             * Burada oluşturulan kullanıcı, hangi sayfalara griş yapabileceğinin
             * anlaşılabilmedi konytorl edilebilmesi için bir yetki listesininin
             * olmasına gerek vardır. bu nedenle burada "USER", "ADMIN" v.s. gibi
             * listeyi burada belirtmeliyiz.
             */
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

            return User.builder()
                    .username(authId.toString())
                    .password("")
                    .accountExpired(false)
                    .accountLocked(false)
                    .authorities(authorities)
                    .build();
        }
        return null;
    }
}
