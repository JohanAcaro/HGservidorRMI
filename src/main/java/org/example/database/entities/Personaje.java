package org.example.database.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

// Clase que representa la tabla Personaje de la base de datos
@Entity
@NamedQuery(name = "Personaje.findAll", query = "SELECT p FROM Personaje p")
public class Personaje implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASA")
    private Casa casa;

    @Column(name = "EDAD", nullable = false)
    private int edad;
    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;


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

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
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
