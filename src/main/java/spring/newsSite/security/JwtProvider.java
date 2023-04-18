package spring.newsSite.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import spring.newsSite.entity.Role;

import java.util.Date;

@Component
public class JwtProvider {
    private static final long expireTime = 1000*60*60*24;
    private static final String securityKey = "bumaxfiykalitsozhisoblanadibunihechkimbilmasligilozim";
    public String generateToken(String username,String password, Role role){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", role)
                .signWith(SignatureAlgorithm.HS512, securityKey)
                .compact();
        return token;
    }

    public String getUserNameForToken(String token) {
        try{
            String username = Jwts
                    .parser()
                    .setSigningKey(securityKey)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
            return username;
        }catch (Exception e){
            return null;
        }
    }
}
