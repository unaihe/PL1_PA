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
    private Comedor comedor=new Comedor(log);
    private Comun comun=new Comun(log);
    private Descanso descanso=new Descanso(log);
    
    public Refugio(Logger log) {
        this.log=log;
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
