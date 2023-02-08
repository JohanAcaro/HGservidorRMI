package org.example;

import org.example.database.remote.JuegoDeTronosRMI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class App
{
    public static void main( String[] args )
    {
        // Declara el host y el puerto
        String host;
        int puerto = 5055;
        try {
            // Obtiene la dirección IP del host
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            // Si no se puede obtener la dirección IP, se muestra un mensaje de error
            System.out.println("No se ha podido obtener la dirección IP");
            System.out.println(e.getMessage());
            return;
        }
        try {
            // Crea el registro en el host y puerto indicados
            Registry registro = LocateRegistry.createRegistry(puerto);
            // Crea el objeto remoto
            JuegoDeTronosRMI juegoTronos = new JuegoDeTronosRMI();
            // Lo registra en el registro
            registro.rebind("misPersonajes", juegoTronos);
            // Muestra un mensaje de confirmación
            System.out.println("Servicio registrado en host " + host + " y puerto " + puerto);
            } catch (RemoteException e) {
            // Si no se puede registrar el servicio, se muestra un mensaje de error
            System.out.println("No se ha podido registrar el servicio");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
