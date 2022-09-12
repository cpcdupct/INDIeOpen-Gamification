package com.cpcd.microservice.app.gamification.microserviciogamification.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="${feign.indieopen}")
public interface INDIeOpenFeingClient {
    @GetMapping("/model/{unitResource}")
    public String getUnitModel(@RequestHeader("X-TenantID") String tenant, @PathVariable String unitResource);
}
