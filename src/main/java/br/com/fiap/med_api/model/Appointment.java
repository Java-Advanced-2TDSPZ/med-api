package br.com.fiap.med_api.model;

import java.util.Random;

public class Appointment {
    private Long id;
    private String name;
    private String icon;


    public Appointment(Long id, String name, String icon) {
        this.id = (id == null) ? Math.abs(new Random().nextLong()) : id;
        this.name = name;
        this.icon = icon;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getIcon() {
        return icon;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Appointment [id=" + id + ", name=" + name + ", icon=" + icon + "]";
    }
    
    



}
