package com.cpcd.microservice.app.gamification.microserviciogamification.clients;

import com.cpcd.microservices.app.servicescommons.models.requests.LearningUnitRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="${feign.microserviciogenerator}")
public interface GeneratorFeingClient {
    @PostMapping("/gamificationgenerator")
    void generateGame(@RequestBody LearningUnitRequest learningUnit);

    @DeleteMapping("/learninggenerator/{unitid}/{teacherid}/{unitTypes}")
    void deleteUnit(@PathVariable String unitid, @PathVariable String teacherid, @PathVariable LearningUnitRequest.UnitTypes unitTypes);


}
