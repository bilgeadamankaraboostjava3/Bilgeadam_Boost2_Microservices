package org.muhammet.utility;

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
