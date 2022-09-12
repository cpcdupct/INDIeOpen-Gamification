package com.cpcd.microservice.app.gamification.microserviciogamification.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estado;

    private String idunidad;

    private String idwidget;

    private String codgame;

    private String origin;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_at")
    private Date createAt;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Game(){}

    public Game(Long id, int estado, String idunidad, String idwidget, String codgame, String origin, Date createAt) {
        this.id = id;
        this.estado = estado;
        this.idunidad = idunidad;
        this.idwidget = idwidget;
        this.codgame = codgame;
        this.origin = origin;
        this.createAt = createAt;
    }

    public Game(int estado, String idunidad, String idwidget, String codgame, String origin, String email) {
        this.estado = estado;
        this.idunidad = idunidad;
        this.idwidget = idwidget;
        this.codgame = codgame;
        this.origin = origin;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getCodgame() {
        return codgame;
    }

    public void setCodgame(String codgame) {
        this.codgame = codgame;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
