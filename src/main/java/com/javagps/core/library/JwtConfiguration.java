package com.javagps.core.library;

import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;

public class JwtConfiguration {
    private static final String SECRET_KEY = "asjd12dnsan2zji213dsdddd";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour
    public JwtConfiguration() {
    }
    public static String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("role", "admin") // special claim
                .claim("email", "user@example.com") // special claim
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    // claims; role, username or userid, maybe ip,

    public static boolean isValid(String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwtToken);

//            Claims claims = claimsJws.getBody();
//
//            // Örnek: Kullanıcı kimliğini almak
//            String userId = claims.getSubject();
//
//            // Örnek: JWT'nin oluşturulma zamanını almak
//            Date issuedAt = claims.getIssuedAt();
//
//            // Örnek: JWT'nin süresinin dolacağı zamanı almak
//            Date expirationDate = claims.getExpiration();
//
//            // Özel claim'leri almak
//            String role = claims.get("role", String.class);
//            String email = claims.get("email", String.class);

            Date expirationDate = claimsJws.getBody().getExpiration();
            if(expirationDate == null || expirationDate.before(new Date())) {
                // throw exception token wrong
                return false;
            }
            return true;
        }
        catch(Exception ex)
        {
            // Exception token wrong
            ex.printStackTrace();
            return false;
        }
    }
}
