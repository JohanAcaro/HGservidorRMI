package org.example.database.remote;
import org.example.database.entities.Casa;
import org.example.database.entities.Personaje;

import java.rmi.Remote;
import java.util.ArrayList;

public interface JuegoDeTronosInterfaceRMI extends Remote {

    public String allPersonajes() throws Exception;
    public String allCasas() throws Exception;

    public String buscarPersonaje(String nombre) throws Exception;
    public String buscarPersonajesCasa(String casa) throws Exception;
    public String buscarCasa(String nombreCasa) throws Exception;

}
