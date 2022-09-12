package com.cpcd.microservice.app.gamification.microserviciogamification.controller;

import com.cpcd.microservice.app.gamification.microserviciogamification.models.entity.Game;
import com.cpcd.microservice.app.gamification.microserviciogamification.models.request.GameRequest;
import com.cpcd.microservice.app.gamification.microserviciogamification.services.JWTService;
import com.cpcd.microservice.app.gamification.microserviciogamification.services.ServicioGame;
import com.cpcd.microservices.app.servicescommons.token.ModelToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControladorGame {
    private Logger log = LoggerFactory.getLogger(ControladorGame.class);

    @Autowired
    private ServicioGame servicioGame;


    @PostMapping
    public ResponseEntity<?> addGame(@RequestHeader("Authorization") String token,
                                        @Valid @RequestBody GameRequest gameRequest,
                                        BindingResult result) {

        ModelToken jwtService = new ModelToken(token);
        if (result.hasErrors()) {
            return validar(result);
        }
        try {
            String email = jwtService.getCorreo();
            Game game = servicioGame.crearGame(gameRequest,email);
            return ResponseEntity.status(HttpStatus.CREATED).body(game);
        } catch (Exception exception){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping("/{codgame}")
    public ResponseEntity<?> setCompletadoCodigoGame(@RequestHeader("Authorization") String token,@PathVariable String codgame){
        try {
            Game game = servicioGame.setCompletadoCodgame(codgame);
            return ResponseEntity.status(HttpStatus.CREATED).body(game);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
    protected ResponseEntity<?> validar(BindingResult result) {

        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
