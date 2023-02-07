package org.example;

import org.example.database.remote.JuegoDeTronosRMI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String host;
        int puerto = 5055;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("No se ha podido obtener la dirección IP");
            System.out.println(e.getMessage());
            return;
        }
        try {
            Registry registro = LocateRegistry.createRegistry(puerto);
            JuegoDeTronosRMI juegoTronos = new JuegoDeTronosRMI();
            registro.rebind("misPersonajes", juegoTronos);
            System.out.println("Servicio registrado en host " + host + " y puerto " + puerto);
        } catch (RemoteException e) {
            System.out.println("No se ha podido registrar el servicio");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
