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
    private interfaz.Interfaz interfaz;
    
    /**
     *
     * @param refugio
     * @param tuneles
     * @param areaRiesgo
     * @param controlPausa
     * @param interfaz
     * @throws RemoteException
     */
    public SimulacionRemotaImpl(Refugio refugio, Tuneles tuneles, AreaRiesgo areaRiesgo, ControlPausa controlPausa, interfaz.Interfaz interfaz) throws RemoteException {
        this.refugio = refugio;
        this.tuneles = tuneles;
        this.areaRiesgo = areaRiesgo;
        this.controlPausa = controlPausa;
        this.interfaz = interfaz;
    }
    
    /**
     * Método que llama a la función de contar humanos en la clase Refugio.
     * @return Número de humanos en el Refugio
     * @throws RemoteException
     */
    @Override
    public int getNumeroHumanosRefugio() throws RemoteException {
        return refugio.contarPersonas();
    }
    
    /**
     * Método que llama a la función de contar humanos en la clase Tuneles.
     * @return Numero de humanos en los Túneles
     * @throws RemoteException
     */
    @Override
    public List<Integer> getNumeroHumanosTuneles() throws RemoteException {
        return tuneles.getNumHumanosPorTunel();
    }
    
    /**
     * Método que llama a la función de contar humanos en la clase AreaRiesgo.
     * @return Numero de humanos en las Zonas de Riesgo
     * @throws RemoteException
     */
    @Override
    public List<Integer> getNumeroHumanosZonasRiesgo() throws RemoteException {
        return areaRiesgo.getNumHumanosPorZona();
    }
    
    /**
     * Método que llama a la función de contar zombis en la clase AreaRiesgo.
     * @return Numero de zombis en las Zonas de Riesgo
     * @throws RemoteException
     */
    @Override
    public List<Integer> getNumeroZombisZonasRiesgo() throws RemoteException {
          return areaRiesgo.getNumZombisPorZona();
    }
    
    /**
     * Método que llama a la función de rankingZombis en la clase Zombi.
     * @return Una lista de String con el Top 3 zombis.
     * @throws RemoteException
     */
    @Override
    public List<String> getZombisMasLetales() throws RemoteException {
        return Zombi.getRankingLetales(); // Debes implementar este método en la clase Zombi
    }
    
    /**
     * Comprueba si la simulación está en pausa.
     * @return True si está pausada, False si no lo está.
     * @throws RemoteException
     */
    @Override
    public boolean isPausado() throws RemoteException {
        return controlPausa.isEnPausa();
    }
    
    /**
     * Función para el botón de la interfaz cliente. 
     * Intercambia entre estado de pausa o acción.
     * @throws RemoteException
     */
    @Override
    public void togglePausa() throws RemoteException {
        if (controlPausa.isEnPausa()) {
            controlPausa.reanudar();
            interfaz.reanudarBoton();
        } else {
            controlPausa.pausar();
            interfaz.pausarBoton();
        }
    }
}
