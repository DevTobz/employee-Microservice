package com.tobi.loginService.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String Secret_Key= "7638792F423F4528482B4D6251655468576D597133743677397A24432646294A";


    public String extractUsername(String token) {

        return extractClaim(Claims::getSubject, token);
    }

    public <T> T extractClaim(Function<Claims,T> claimsResolver, String token){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public Claims extractAllClaims(String token){
      return  Jwts.
                parserBuilder().
                setSigningKey(getSignInKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }

    private Key getSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(Secret_Key);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateToken(Map<String,Object> extraClaims, EmployeeUserDetails userDetails){
      return  Jwts.
                builder().
                setSubject(userDetails.getUsername()).
                addClaims(extraClaims).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+1000*60*24)).
                signWith(getSignInKey(), SignatureAlgorithm.HS256).
                compact();
    }

    public String generateToken(EmployeeUserDetails employeeUserDetails){
        return generateToken(new HashMap<>(),employeeUserDetails);
    }

    public boolean isTokenValid(String jwt, EmployeeUserDetails userDetails) {
        String username = extractUsername(jwt);
        if(username.equals(userDetails.getUsername()) && !isTokenExpired(jwt)){
            return true;
        }
        return false;
    }

    private boolean isTokenExpired(String jwt) {
       return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {

        return extractClaim(claims -> claims.getExpiration(), jwt);
    }


}
