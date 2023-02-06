package org.example.database;


import java.rmi.Remote;

public interface JuegoDeTronosInterfaceRMI extends Remote {
    public String buscarPersonaje(String nombre) throws Exception;
    public String buscarCasa(String casa) throws Exception;
    public String buscarLema(String lema) throws Exception;
    public String buscarEscudo(String escudo) throws Exception;

}
