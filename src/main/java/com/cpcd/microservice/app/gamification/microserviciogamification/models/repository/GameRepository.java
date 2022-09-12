package com.cpcd.microservice.app.gamification.microserviciogamification.models.repository;

import com.cpcd.microservice.app.gamification.microserviciogamification.models.entity.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    boolean existsByCodgameAndEstado(String codgame,int estado);

    Optional<Game> findByIdunidadAndIdwidgetAndEstadoAndEmail(String idunidad, String idwidget, int estado, String email);

    Optional<Game> findByCodgameAndEstado(String codgame, int estado);
}