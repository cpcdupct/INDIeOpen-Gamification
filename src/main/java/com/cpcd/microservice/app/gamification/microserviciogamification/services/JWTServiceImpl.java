package com.cpcd.microservice.app.gamification.microserviciogamification.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JWTServiceImpl implements JWTService {
    @Autowired
    private Environment env;

    @Override
    public boolean validate(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Excepcion" + e.getMessage());
            return false;
        }
    }

    @Override
    public Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey("MY_JWT_KEY".getBytes())
                .parseClaimsJws(adaptToken(token)).getBody();
        return claims;
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).get("user_name").toString();
    }

    @Override
    public String getId(String token) {
        return getClaims(token).get("id").toString();
    }


    public Long getUserId(String token) {return Long.valueOf(getClaims(token).get("userid").toString());}
    public Long getCurso(String token) {return Long.valueOf(getClaims(token).get("curso").toString());}

    public Long getActividad(String token) {return Long.valueOf(getClaims(token).get("actividad").toString());}

    public String getCorreo(String token) {return getClaims(token).get("correo").toString();}
    public String getDominio(String token) {return getClaims(token).get("dominio").toString();}

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        ArrayList<String> roles = (ArrayList<String>) getClaims(token).get("authorities");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String adaptToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }
        return null;
    }
}
