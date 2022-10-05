package com.muhammet.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1- Create JWT
     * 2- Validate JWT
     */
    public Optional<String> createToken(Long id){
        String token= null;
        //  "Agj\"G8Ub9grfX'wo3yN@KkE%Ojj'cd'cH<.%*[EjYs\"MiBwI+MVql\"42#yXi,u.";
        String sifreAnahtari ="1234";

        try{
            /**
             * JWT içerisine hassas bilgilerinizi koymayınız. Örneğin şifre gibi.
             *
             */
            token = JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withIssuer("muhammet")
                    .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60))
                    .withIssuedAt(new Date())
                    .sign(Algorithm.HMAC256(sifreAnahtari));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("1234");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("muhammet")
                    .build();
            DecodedJWT decode = verifier.verify(token);
            if(decode==null)
                return false;
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Optional<Long> getUserId(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("1234");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("muhammet")
                    .build();
            DecodedJWT decode = verifier.verify(token);
            if(decode==null)
                return Optional.empty();
            return Optional.of(decode.getClaim("id").asLong());
        }catch (Exception e){
            return Optional.empty();
        }
    }

}
