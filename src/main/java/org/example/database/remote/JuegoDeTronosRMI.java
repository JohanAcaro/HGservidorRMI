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
    // Usuario y contraseña para iniciar sesión
    private static final String USERNAME = "root";
    private static final String PASSWORD = "curso";
    // ArrayLists para guardar los datos de la Entity Personaje y Entity Casa
    private ArrayList<Personaje> personajes;
    private ArrayList<Casa> casas;
    // EntityManager para hacer las consultas
    EntityManagerFactory factoria = Persistence.createEntityManagerFactory("juego_tronos");
    EntityManager em = factoria.createEntityManager();

    // Constructor
    public JuegoDeTronosRMI() throws Exception {
        //Todos los personajes y casas de la Entity Personaje y Entity Casa
        var personajesConsulta = em.createNamedQuery("Personaje.findAll", Personaje.class);
        personajes = new ArrayList<>(personajesConsulta.getResultList());

        var casasConsulta = em.createNamedQuery("Casa.findAll", Casa.class);
        casas = new ArrayList<>(casasConsulta.getResultList());
    }

    // Métodos de la interfaz
    // Iniciar sesión
    public boolean iniciarSesion(String username, String password) throws RemoteException{
        try {
            // Comprobar que el usuario y contraseña sean correctos y devolver true o false
            return username.equals(USERNAME) && password.equals(PASSWORD);
        } catch (Exception e) {
            return false;
        }
    }
    // Buscar un personaje por nombre
    @Override
    public String buscarPersonaje(String nombre) {
        try {
            // setencia sql para buscar un personaje por nombre
            String sql = "SELECT p FROM Personaje p WHERE p.nombre = :nombre";
            // Crear la consulta y pasarle el nombre del personaje
            var personajeConsulta = em.createQuery(sql, Personaje.class);
            personajeConsulta.setParameter("nombre", nombre);
            // Crear un ArrayList de tipo Personaje y guardar los datos de la consulta
            personajes = (ArrayList<Personaje>) personajeConsulta.getResultList();
            // Crear un String para guardar los datos del personaje
            String resultado = "";
            // Recorrer el ArrayList de personajes y guardar los datos en el String
            for (Personaje p : personajes) {
                resultado = "Nombre: " + p.getNombre() +
                        "\nCasa: " + p.getCasa().getNombre() +
                        "\nEdad: " + p.getEdad() +
                        "\nTitulo: " + p.getTitulo() +
                        "\n";
            }
            if (resultado.equals("")) {
                resultado = "No se ha encontrado ningún personaje con ese nombre\n";
            }
            // Devolver el resultado
            return resultado;
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    // Buscar todos los personajes de una casa
    @Override
    public String buscarPersonajesCasa(String casa) {
        try{
            // setencia sql para buscar personajes por casa
            String sql = "SELECT p FROM Personaje p INNER JOIN p.casa c WHERE c.nombre = :casa";
            // Crear la consulta y pasarle el nombre de la casa
            var personajeConsulta = em.createQuery(sql, Personaje.class);
            personajeConsulta.setParameter("casa", casa);
            // Crear un ArrayList de tipo Personaje y guardar los datos de la consulta
            personajes = (ArrayList<Personaje>) personajeConsulta.getResultList();
            // Crear un StringBuilder para guardar los datos de los personajes
            StringBuilder resultado = new StringBuilder();
            // Recorrer el ArrayList de personajes y guardar los datos en el StringBuilder
            for (Personaje p : personajes) {
                resultado.append("Nombre: ").append(p.getNombre())
                        .append(" - Casa: ").append(p.getCasa().getNombre())
                        .append(" - Edad: ").append(p.getEdad())
                        .append(" - Titulo: ").append(p.getTitulo())
                        .append("\n");
            }
            if (resultado.toString().equals("")) {
                resultado = new StringBuilder("No se ha encontrado ningún personaje de esa casa\n");
            }
            // Devolver el resultado
            return resultado.toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

    // Buscar una casa por nombre
    @Override
    public String buscarCasa(String nombreCasa) {
        try {
            // setencia sql para buscar una casa por nombre
            String sql = "SELECT c FROM Casa c WHERE c.nombre = :nombreCasa";
            // Crear la consulta y pasarle el nombre de la casa
            var casaConsulta = em.createQuery(sql, Casa.class);
            casaConsulta.setParameter("nombreCasa", nombreCasa);
            // Crear un ArrayList de tipo Casa y guardar los datos de la consulta
            casas= (ArrayList<Casa>) casaConsulta.getResultList();
            // Crear un String para guardar los datos de la casa
            String resultado = "";
            // Recorrer el ArrayList de casas y guardar los datos de la casa
            for (Casa c : casas) {
                resultado = "Nombre: " + c.getNombre() +
                        "\nLema: " + c.getLema() +
                        "\nEscudo: " + c.getEscudo()+
                        "\n";
            }
            if (resultado.equals("")) {
                resultado = "No se ha encontrado ninguna casa con ese nombre\n";
            }
            // Devolver el resultado
            return resultado;
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }
    // Buscar todos los personajes
    @Override
    public String allPersonajes() {
        try {
            // Crear un StringBuilder para guardar los datos de los personajes
            StringBuilder resultado = new StringBuilder();
            // Recorrer el ArrayList de personajes y guardar los datos en el StringBuilder
            for (Personaje p : personajes) {
                resultado.append("Nombre: ").append(p.getNombre())
                        .append(" - Casa: ").append(p.getCasa().getNombre())
                        .append(" - Edad: ").append(p.getEdad())
                        .append(" - Titulo: ").append(p.getTitulo())
                        .append("\n");
            }
            // Devolver el resultado
            return resultado.toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }

    }
    // Buscar todas las casas
    @Override
    public String allCasas() {
        try {
            // Crear un StringBuilder para guardar los datos de las casas
            StringBuilder resultado = new StringBuilder();
            // Recorrer el ArrayList de casas y guardar los datos en el StringBuilder
            for (Casa c : casas) {
                resultado.append("Nombre: ").append(c.getNombre())
                        .append(" - Lema: ").append(c.getLema())
                        .append(" - Escudo: ").append(c.getEscudo())
                        .append("\n");
            }
            // Devolver el resultado
            return resultado.toString();
        }
        catch (Exception e){
            return "Error: " + e.getMessage();
        }

    }
}
