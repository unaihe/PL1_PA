/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;


import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.tuneles.Tuneles;
import poo.apocalipsiszombie.tuneles.Tunel;
import poo.apocalipsiszombie.zonas.Refugio;

/**
 *
 * @author unaih
 */
public class Humano extends Thread{
    private final String id;
    private boolean marcado=false;
    private boolean vivo=true;
    private String zonaActual;
    private Refugio refugio;
    private int comidaRecolectada=0;
    private boolean necesitaDescanso=false;
    private Logger log;
    private Tunel tunel;
    private Tuneles tuneles;
    private AreaRiesgo areaRiesgo;

    @Override
    public String toString() {
        return "Humano{" + "id=" + id + '}';
    }
    
    public Humano(String id, Refugio refugio, Tuneles tuneles, AreaRiesgo areas,Logger logger){
        this.id=id;
        this.refugio=refugio;    
        log=logger;
        this.tuneles=tuneles;
        this.areaRiesgo=areas;
    }
    public void run(){
        
    }
}
