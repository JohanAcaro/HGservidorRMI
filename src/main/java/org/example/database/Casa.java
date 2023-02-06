package org.example.database;

public class Casa {
    int id;
    String nombre;
    String lema;

    String escudo;

    public Casa(int id, String nombre, String lema, String escudo) {
        this.id = id;
        this.nombre = nombre;
        this.lema = lema;
        this.escudo = escudo;
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

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lema='" + lema + '\'' +
                ", escudo='" + escudo + '\'' +
                '}';
    }
}
