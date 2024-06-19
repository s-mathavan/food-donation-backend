package com.foodDonation.postCreation.service;

import com.foodDonation.postCreation.data.UserData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY="A237D08CC68F12FC39A153D58590A9FF94A437C7696530353E32CF8EFB5AC1E7625FB169887D8529D2D5B274B8EE7C2BB3A7E22DA1286B49056EA393620A4DA3B8AFEA03465B0106CD2D23F4893AAC2E6BC2886FC8EA6B57C30A5263B1F1D983E77D4B89C05EC54BED9EB43301C095884311690B4C9DDF07031AB13F7F729163";

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Integer extractId(String token)
    {
         String idAsString =extractClaim(token,claims -> claims.get("id", String.class));        //this is the proper way of extracting particular claim in a jwt
        return Integer.parseInt(idAsString);
    }

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);         //this copies the existing method working
    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){

        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //token validation
    public boolean isTokenValid(String token, UserData userData){
        final String username = extractUsername(token);
        return (username.equals(userData.getFirstName())) && !isTokenExpired(token);
    }

    //to check token expired or not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //to extract expiration date
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}
