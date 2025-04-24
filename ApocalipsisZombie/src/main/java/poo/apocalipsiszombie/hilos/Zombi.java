/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.areasriesgo.ZonaRiesgo;

/**
 *
 * @author unaih
 */
public class Zombi extends Thread{
    private final String id;
    private ZonaRiesgo zonaActual;
    private int muertes=0;
    private boolean atacando;
    private AreaRiesgo areaRiesgo;
    
    public Zombi(String id,AreaRiesgo areaRiesgo){
        this.id=id;
        this.areaRiesgo=areaRiesgo;
        zonaActual=areaRiesgo.getZonaRiesgoAleatoria();
        zonaActual.agregarZombi(this);
    }
    
    public Zombi(String id, ZonaRiesgo zonaRiesgo, AreaRiesgo areaRiesgo){
        zonaActual=zonaRiesgo;
        this.areaRiesgo=areaRiesgo;
        this.id=id;
    }
        
    public void run(){
        while(true){
            int tiempo = ThreadLocalRandom.current().nextInt(500, 1501);
            
            zonaActual.agregarZombi(this);
            if (zonaActual.hayHumanos()){
                Humano victima=zonaActual.seleccionarHumanoAleatorio();
                try {
                    victima.serAtacado(tiempo,this);
                    Thread.sleep(tiempo);
                    if (!victima.isVivo()){
                        muertes+=1;
                    };
                } catch (InterruptedException ex) {}
            }
            int reposo = ThreadLocalRandom.current().nextInt(2000, 3001);
            try {
                Thread.sleep(reposo);
            } catch (InterruptedException ex) {}
            zonaActual.quitarZombi(this);
            zonaActual=areaRiesgo.getZonaRiesgoAleatoria();         
                    
        }
    };
}
