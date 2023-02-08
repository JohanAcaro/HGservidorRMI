package org.example.database.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

// Clase que representa la tabla Casa de la base de datos
@Entity
@NamedQuery(name="Casa.findAll", query="SELECT c FROM Casa c")
public class Casa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;

    @Column(name = "LEMA", length = 100, nullable = false)
    private String lema;

    @Column(name = "ESCUDO", length = 100)
    private String escudo;

    @OneToMany(mappedBy = "casa")
    private Set<Personaje> personajes = new LinkedHashSet<>();

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

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }

}
