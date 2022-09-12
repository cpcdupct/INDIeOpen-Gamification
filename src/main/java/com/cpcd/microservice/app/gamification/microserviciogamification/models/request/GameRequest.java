package com.cpcd.microservice.app.gamification.microserviciogamification.models.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class GameRequest implements Serializable {
    @NotBlank
    private String idunidad;

    @NotBlank
    private String idwidget;

    @NotBlank
    private String origin;

    public GameRequest() {
    }

    public String getIdunidad() {
        return idunidad;
    }

    public void setIdunidad(String idunidad) {
        this.idunidad = idunidad;
    }

    public String getIdwidget() {
        return idwidget;
    }

    public void setIdwidget(String idwidget) {
        this.idwidget = idwidget;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
