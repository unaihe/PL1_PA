/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import poo.apocalipsiszombie.Logger;

/**
 *
 * @author unaih
 */
public class Refugio {
    private Logger log;
    private interfaz.Interfaz interfaz;
    private Comedor comedor;
    private Comun comun;
    private Descanso descanso;
    
    public Refugio(Logger log,interfaz.Interfaz interfaz) {
        this.log=log;
        this.interfaz=interfaz;
        comun=new Comun(log,interfaz);
        descanso=new Descanso(log,interfaz);
        comedor=new Comedor(log,interfaz);
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
