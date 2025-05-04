/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import poo.apocalipsiszombie.ControlPausa;
import poo.apocalipsiszombie.Logger;

/**
 *
 * @author unaih
 */
public class Refugio {
    private Logger log;
    private ControlPausa controlPausa;
    private interfaz.Interfaz interfaz;
    private Comedor comedor;
    private Comun comun;
    private Descanso descanso;
    
    
    public Refugio(Logger log,interfaz.Interfaz interfaz,ControlPausa controlPausa) {
        this.log=log;
        this.controlPausa=controlPausa;
        this.interfaz=interfaz;
        comun=new Comun(log,interfaz);
        descanso=new Descanso(log,interfaz);
        comedor=new Comedor(log,interfaz,controlPausa);
    }
    /**
     * Contador para la interfaz del cliente.
     * @return Devuelve el número de personas total en el Refugio entre todas las salas.
     */
    public int contarPersonas(){
        int total=0;
        total+=descanso.getPersonas().size();
        total+=comedor.getPersonas().size();
        total+=comun.getPersonas().size();
        return total;
    }
    
    public Comedor getComedor() {
        return comedor;
    }

    public Comun getComun() {
        return comun;
    }

    public Descanso getDescanso() {
        return descanso;
    }
    
}
