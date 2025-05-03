/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import poo.apocalipsiszombie.ControlPausa;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.hilos.Zombi;
import poo.apocalipsiszombie.zonas.*;
import poo.apocalipsiszombie.tuneles.Tuneles;
/**
 *
 * @author unaih
 */

public class SimulacionRemotaImpl extends UnicastRemoteObject implements SimulacionRemota {
    private Refugio refugio;
    private Tuneles tuneles;
    private AreaRiesgo areaRiesgo;
    private ControlPausa controlPausa;
    
    public SimulacionRemotaImpl(Refugio refugio, Tuneles tuneles, AreaRiesgo areaRiesgo, ControlPausa controlPausa) throws RemoteException {
        this.refugio = refugio;
        this.tuneles = tuneles;
        this.areaRiesgo = areaRiesgo;
        this.controlPausa = controlPausa;
    }
    
    @Override
    public int getNumeroHumanosRefugio() throws RemoteException {
        return refugio.contarPersonas();
    }
    
    @Override
    public List<Integer> getNumeroHumanosTuneles() throws RemoteException {
        return tuneles.getNumHumanosPorTunel();
    }
    
    @Override
    public List<Integer> getNumeroHumanosZonasRiesgo() throws RemoteException {
        return areaRiesgo.getNumHumanosPorZona();
    }
    
    @Override
    public List<Integer> getNumeroZombisZonasRiesgo() throws RemoteException {
          return areaRiesgo.getNumZombisPorZona();
    }
    
    @Override
    public List<String> getZombisMasLetales() throws RemoteException {
        return Zombi.getRankingLetales(); // Debes implementar este método en la clase Zombi
    }
    
    @Override
    public boolean isPausado() throws RemoteException {
        return controlPausa.isEnPausa();
    }
    
    @Override
    public void togglePausa() throws RemoteException {
        if (controlPausa.isEnPausa()) {
            controlPausa.reanudar();
        } else {
            controlPausa.pausar();
        }
    }
}
