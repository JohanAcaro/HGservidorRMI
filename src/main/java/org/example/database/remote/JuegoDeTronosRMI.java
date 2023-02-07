package org.example.database.remote;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.database.entities.Casa;
import org.example.database.entities.Personaje;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class JuegoDeTronosRMI extends UnicastRemoteObject implements JuegoDeTronosInterfaceRMI{
    @Serial
    private static final long serialVersionUID = -4817856459999901795L;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "curso";
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

    public boolean iniciarSesion(String username, String password) throws RemoteException {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }

    @Override
    public String buscarPersonaje(String nombre) throws Exception {
        String sql = "SELECT p FROM Personaje p WHERE p.nombre = :nombre";
        var personajeConsulta = em.createQuery(sql, Personaje.class);
        personajeConsulta.setParameter("nombre", nombre);
        personajes = (ArrayList<Personaje>) personajeConsulta.getResultList();
        String resultado = "";
        for (Personaje p : personajes) {
            resultado = "Nombre: " + p.getNombre() +
                        "\nCasa: " + p.getCasa().getNombre() +
                        "\nEdad: " + p.getEdad() +
                        "\nTitulo: " + p.getTitulo()+
                        "\n";
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
        for (Personaje p : personajes) {
            resultado.append("Nombre: ").append(p.getNombre())
                    .append(" - Casa: ").append(p.getCasa().getNombre())
                    .append(" - Edad: ").append(p.getEdad())
                    .append(" - Titulo: ").append(p.getTitulo())
                    .append("\n");
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
        for (Casa c : casas) {
            resultado = "Nombre: " + c.getNombre() +
                        "\nLema: " + c.getLema() +
                        "\nEscudo: " + c.getEscudo()+
                        "\n";
        }
        return resultado;
    }

    @Override
    public String allPersonajes() throws Exception{
        var personajesConsulta = em.createNamedQuery("Personaje.findAll", Personaje.class);
        personajes = new ArrayList<>(personajesConsulta.getResultList());
        StringBuilder resultado = new StringBuilder();

        for (Personaje p : personajes) {
            resultado.append("Nombre: ").append(p.getNombre())
                    .append(" - Casa: ").append(p.getCasa().getNombre())
                    .append(" - Edad: ").append(p.getEdad())
                    .append(" - Titulo: ").append(p.getTitulo())
                    .append("\n");
        }
        return resultado.toString();
    }

    @Override
    public String allCasas() throws Exception{
        var casasConsulta = em.createNamedQuery("Casa.findAll", Casa.class);
        casas = new ArrayList<>(casasConsulta.getResultList());
        StringBuilder resultado = new StringBuilder();
        for (Casa c : casas) {
            resultado.append("Nombre: ").append(c.getNombre())
                    .append(" - Lema: ").append(c.getLema())
                    .append(" - Escudo: ").append(c.getEscudo())
                    .append("\n");
        }
        return resultado.toString();
    }
}
