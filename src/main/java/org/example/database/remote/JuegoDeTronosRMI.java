package org.example.database.remote;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.database.entities.Casa;
import org.example.database.entities.Personaje;

import java.io.Serial;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class JuegoDeTronosRMI extends UnicastRemoteObject implements JuegoDeTronosInterfaceRMI{
    @Serial
    private static final long serialVersionUID = -4817856459999901795L;

    private ArrayList<Personaje> personajes;
    private ArrayList<Casa> casas;
    EntityManagerFactory factoria = Persistence.createEntityManagerFactory("juego_tronos");
    EntityManager em = factoria.createEntityManager();

    public JuegoDeTronosRMI() throws Exception {
        //Todos los personajes y casas de la Entity Personaje y Entity Casa
        //Inner Join
        var personajesConsulta = em.createNamedQuery("Personaje.findAll", Personaje.class);
        personajes = new ArrayList<>(personajesConsulta.getResultList());

        var casasConsulta = em.createNamedQuery("Casa.findAll", Casa.class);
        casas = new ArrayList<>(casasConsulta.getResultList());

    }

    @Override
    public String buscarPersonaje(String nombre) throws Exception {
        String sql = "SELECT p FROM Personaje p WHERE p.nombre = :nombre";
        var personajeConsulta = em.createQuery(sql, Personaje.class);
        personajeConsulta.setParameter("nombre", nombre);
        personajes = (ArrayList<Personaje>) personajeConsulta.getResultList();
        String resultado = "";
        for (Personaje personaje : personajes) {
            resultado = "Nombre: " + personaje.getNombre() + " - Casa: " + personaje.getCasa().getNombre() +
                        " - Edad: " + personaje.getEdad() + " - Titulo: " + personaje.getTitulo();
        }
        return resultado;
    }

    @Override
    public String buscarPersonajesCasa(String casa) throws Exception {
        //parsear casa a int
        String sql = "SELECT p FROM Personaje p INNER JOIN p.casa c WHERE c.nombre = :casa";
        var personajeConsulta = em.createQuery(sql, Personaje.class);
        personajeConsulta.setParameter("casa", casa);
        personajes = (ArrayList<Personaje>) personajeConsulta.getResultList();
        StringBuilder resultado = new StringBuilder();
        for (Personaje personaje : personajes) {
            resultado.append("Nombre: ").append(personaje.getNombre()).append(" - Casa: ")
                    .append(personaje.getCasa().getNombre()).append(" - Edad: ")
                    .append(personaje.getEdad()).append(" - Titulo: ").append(personaje.getTitulo());
            //Si hay mas de un personaje en la casa se separa por un salto de linea
            if (personajes.size() > 1) {
                resultado.append("\n");
            }
        }
        return resultado.toString();
    }

    @Override
    public String buscarCasa(String nombreCasa) throws Exception {
        String sql = "SELECT c FROM Casa c WHERE c.nombre = :nombreCasa";
        var casaConsulta = em.createQuery(sql, Casa.class);
        casaConsulta.setParameter("nombreCasa", nombreCasa);
        casas= (ArrayList<Casa>) casaConsulta.getResultList();
        String resultado = "";
        for (Casa casa : casas) {
            resultado = "Nombre: " + casa.getNombre() + "\nLema: " + casa.getLema() + "\nEscudo: " + casa.getEscudo();
        }
        return resultado;
    }

    @Override
    public String allPersonajes() throws Exception{
        var personajesConsulta = em.createNamedQuery("Personaje.findAll", Personaje.class);
        personajes = new ArrayList<>(personajesConsulta.getResultList());
        StringBuilder resultado = new StringBuilder();

        for (Personaje personaje : personajes) {
            resultado.append("Nombre: ").append(personaje.getNombre()).append(" - Casa: ")
                    .append(personaje.getCasa().getNombre()).append(" - Edad: ")
                    .append(personaje.getEdad()).append(" - Titulo: ").append(personaje.getTitulo());
            //Si hay mas de un personaje en la casa se separa por un salto de linea
            if (personajes.size() > 1) {
                resultado.append("\n");
            }
        }
        return resultado.toString();
    }

    @Override
    public String allCasas() throws Exception{
        var casasConsulta = em.createNamedQuery("Casa.findAll", Casa.class);
        casas = new ArrayList<>(casasConsulta.getResultList());
        StringBuilder resultado = new StringBuilder();
        for (Casa casa : casas) {
            resultado.append("Nombre: ").append(casa.getNombre()).append("\nLema: ")
                    .append(casa.getLema()).append("\nEscudo: ").append(casa.getEscudo());
            //Si hay mas de un personaje en la casa se separa por un salto de linea
            if (casas.size() > 1) {
                resultado.append("\n");
            }
        }
        return resultado.toString();
    }
}
