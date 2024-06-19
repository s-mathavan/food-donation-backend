package com.foodDonation.login.service;

import com.foodDonation.login.data.AuthenticationResponseData;
import com.foodDonation.login.data.ClaimsForJwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY="A237D08CC68F12FC39A153D58590A9FF94A437C7696530353E32CF8EFB5AC1E7625FB169887D8529D2D5B274B8EE7C2BB3A7E22DA1286B49056EA393620A4DA3B8AFEA03465B0106CD2D23F4893AAC2E6BC2886FC8EA6B57C30A5263B1F1D983E77D4B89C05EC54BED9EB43301C095884311690B4C9DDF07031AB13F7F729163";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //token generation without extra claims
    public AuthenticationResponseData generateToken(ClaimsForJwt claims){
        HashMap<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("id", String.valueOf(claims.getId()));
        extraClaims.put("role", String.valueOf(claims.getRole()));

        return AuthenticationResponseData.builder()
                .token(generateToken(extraClaims,claims))
                .message("Authentication success")
                .build();
    }

    //Token generation with extra claims
    public String generateToken(
            Map<String,Object> extraClaims,
            ClaimsForJwt claims
    ){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(String.valueOf(claims.getFirstName()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis()+1000*60*10)))       //5 minutes
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
