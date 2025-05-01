/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.areasriesgo.ZonaRiesgo;

/**
 *
 * @author unaih
 */
public class Zombi extends Thread {

    private final String id;
    private Logger log;
    private ZonaRiesgo zonaActual;
    private int muertes = 0;
    private boolean atacando;
    private AreaRiesgo areaRiesgo;
    private interfaz.Interfaz interfaz;

    public Zombi(String id, AreaRiesgo areaRiesgo, Logger log, interfaz.Interfaz interfaz) {
        this.id = id;
        this.areaRiesgo = areaRiesgo;
        zonaActual = areaRiesgo.getZonaRiesgoAleatoria();
        this.log = log;
        this.interfaz = interfaz;
    }

    public Zombi(String id, ZonaRiesgo zonaRiesgo, AreaRiesgo areaRiesgo, Logger log, interfaz.Interfaz interfaz) {
        zonaActual = zonaRiesgo;
        this.areaRiesgo = areaRiesgo;
        this.id = id;
        this.log = log;
        this.interfaz = interfaz;
    }

    public String getZombiId() {
        return id;
    }

    public void run() {
        while (true) {
            int tiempo = ThreadLocalRandom.current().nextInt(500, 1501);
            zonaActual.agregarZombi(this);
            log.escribir("El zombi " + id + " entra en la zona de riesgo " + zonaActual.getId() + ".");
            if (zonaActual.hayHumanos()) {
                Humano victima = zonaActual.seleccionarHumanoAleatorio();
                log.escribir("El zombi " + id + " selecciona al humano " + victima.getId() + " para atacar.");
                try {
                    victima.serAtacado(tiempo, this);
                    
                    Thread.sleep(tiempo);
                    if (!victima.isVivo()) {
                        muertes += 1;
                        log.escribir("El zombi " + id + " ha matado al humano " + victima.getId() + ". Número de muertes: " + muertes);
                    
                    };
                    
                } catch (InterruptedException ex) {
                }
            }
            
            int reposo = ThreadLocalRandom.current().nextInt(2000, 3001);
            try {
                Thread.sleep(reposo);
            } catch (InterruptedException ex) {
            }
            zonaActual.quitarZombi(this);
            log.escribir("El zombi " + id + " sale de la zona de riesgo " + zonaActual.getId() + ".");
            zonaActual = areaRiesgo.getZonaRiesgoAleatoria();

        }
    }
;
}
