package com.cpcd.microservice.app.gamification.microserviciogamification.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {
    public boolean validate(String token);
    public Claims getClaims(String token);
    public String getUsername(String token);
    public String getId(String token);
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException;
    public String adaptToken(String token);
    public Long getUserId(String token);
    public Long getCurso(String token);
    public Long getActividad(String token);
    public String getDominio(String token);
    public String getCorreo(String token);
}
