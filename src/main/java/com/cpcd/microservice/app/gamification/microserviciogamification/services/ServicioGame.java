package com.cpcd.microservice.app.gamification.microserviciogamification.services;

import com.cpcd.microservice.app.gamification.microserviciogamification.models.entity.Game;
import com.cpcd.microservice.app.gamification.microserviciogamification.models.request.GameRequest;

public interface ServicioGame {
    public Game crearGame(GameRequest gameRequest, String email);

    public Game setCompletadoCodgame(String codgame);
}
