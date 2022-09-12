package com.cpcd.microservice.app.gamification.microserviciogamification.services;

import com.cpcd.microservice.app.gamification.microserviciogamification.clients.GeneratorFeingClient;
import com.cpcd.microservice.app.gamification.microserviciogamification.clients.INDIeOpenFeingClient;
import com.cpcd.microservice.app.gamification.microserviciogamification.models.entity.Game;
import com.cpcd.microservice.app.gamification.microserviciogamification.models.repository.GameRepository;
import com.cpcd.microservice.app.gamification.microserviciogamification.models.request.GameRequest;
import com.cpcd.microservices.app.servicescommons.models.requests.LearningUnitRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class ServicioImplGame implements ServicioGame{
    private Logger log = LoggerFactory.getLogger(ServicioImplGame.class);

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private INDIeOpenFeingClient indIeOpenFeingClient;

    @Autowired
    private GeneratorFeingClient generatorFeingClient;

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        int temran = random.nextInt(max - min) + min;
        while (gameRepository.existsByCodgameAndEstado(Integer.toString(temran),0)) {
            temran = random.nextInt(max - min) + min;
        }
        return temran;
    }

    @Override
    @Transactional
    public Game crearGame(GameRequest gameRequest, String email) {
        Optional<Game> gameOpen = gameRepository.findByIdunidadAndIdwidgetAndEstadoAndEmail(gameRequest.getIdunidad(), gameRequest.getIdwidget(),0, email);
        if (!gameOpen.isEmpty()){
            return gameOpen.get();
        }
        int rnum = getRandomNumberUsingNextInt(100000,999999);
        Game game = new Game(0, gameRequest.getIdunidad(), gameRequest.getIdwidget(),Integer.toString(rnum), gameRequest.getOrigin(), email);
        game = gameRepository.save(game);
        String modelUnit = indIeOpenFeingClient.getUnitModel(gameRequest.getOrigin(), gameRequest.getIdunidad());
        modelUnit = modelUnit.replaceFirst("(Unit [^{]+)(Open)([^{]+')(\\w+)(' \\d \\{)", "$1Gamification$3" + gameRequest.getIdwidget() +"$5");
        LearningUnitRequest learningUnitRequest = new LearningUnitRequest(modelUnit,"teacher", game.getCodgame(), LearningUnitRequest.UnitTypes.GAME);
        generatorFeingClient.generateGame(learningUnitRequest);
        return game;
    }

    @Override
    @Transactional
    public Game setCompletadoCodgame(String codgame) {
        Optional<Game> gameOpen = gameRepository.findByCodgameAndEstado(codgame,0);

        Game game = gameOpen.get();
        game.setEstado(1);
        game = gameRepository.save(game);
        generatorFeingClient.deleteUnit("teacher",game.getCodgame(),LearningUnitRequest.UnitTypes.GAME);
        return game;
    }
}
