/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
/**
 *
 * @author unaih
 */

public interface SimulacionRemota extends Remote {
    // Estadísticas
    int getNumeroHumanosRefugio() throws RemoteException;
    List<Integer> getNumeroHumanosTuneles() throws RemoteException;
    List<Integer> getNumeroHumanosZonasRiesgo() throws RemoteException;
    List<Integer> getNumeroZombisZonasRiesgo() throws RemoteException;
    List<String> getZombisMasLetales() throws RemoteException;
    
    // Control
    boolean isPausado() throws RemoteException;
    void togglePausa() throws RemoteException;
}

