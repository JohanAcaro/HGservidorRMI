package org.example.database;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class JuegoDeTronosRMI extends UnicastRemoteObject implements JuegoDeTronosInterfaceRMI{
    private static final long serialVersionUID = -4817856459999901795L;
    private ArrayList<Personaje> personajes;

    public JuegoDeTronosRMI() throws Exception {
        super();
    }


    @Override
    public String buscarPersonaje(String nombre) throws Exception {
        return null;
    }

    @Override
    public String buscarCasa(String casa) throws Exception {
        return null;
    }

    @Override
    public String buscarLema(String lema) throws Exception {
        return null;
    }

    @Override
    public String buscarEscudo(String escudo) throws Exception {
        return null;
    }
}
