/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie;

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
    
    public Humano(String id, Refugio refugio){
        this.id=id;
        this.refugio=refugio;    
    }
    public void run(){
        
    }
}
