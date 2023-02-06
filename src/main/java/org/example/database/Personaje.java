package org.example.database;

import java.util.ArrayList;

public class Personaje {
    private int id;
    private String nombre;
    private ArrayList<Casa> casa;
    private int edad;

    private String titulo;

    public Personaje(int id, String nombre, ArrayList<Casa> casa, int edad, String titulo) {
        this.id = id;
        this.nombre = nombre;
        this.casa = casa;
        this.edad = edad;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Casa> getCasa() {
        return casa;
    }

    public void setCasa(ArrayList<Casa> casa) {
        this.casa = casa;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
