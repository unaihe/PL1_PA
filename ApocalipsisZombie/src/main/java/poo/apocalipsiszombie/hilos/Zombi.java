/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.ControlPausa;
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
    private ControlPausa controlPausa;
    private static final java.util.List<Zombi> zombis = new java.util.concurrent.CopyOnWriteArrayList<>();

    public Zombi(String id, AreaRiesgo areaRiesgo, Logger log, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        this.id = id;
        this.controlPausa = controlPausa;
        this.areaRiesgo = areaRiesgo;
        zonaActual = areaRiesgo.getZonaRiesgoAleatoria();
        this.log = log;
        this.interfaz = interfaz;
        zombis.add(this);
    }

    public Zombi(String id, ZonaRiesgo zonaRiesgo, AreaRiesgo areaRiesgo, Logger log, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        zonaActual = zonaRiesgo;
        this.areaRiesgo = areaRiesgo;
        this.id = id;
        this.controlPausa = controlPausa;
        this.log = log;
        this.interfaz = interfaz;
        zombis.add(this);
    }

    public String getZombiId() {
        return id;
    }

    public static java.util.List<String> getRankingLetales() {
        java.util.List<Zombi> copia = new java.util.ArrayList<>(zombis);
        copia.sort((a, b) -> Integer.compare(b.muertes, a.muertes)); // De mayor a menor

        java.util.List<String> ranking = new java.util.ArrayList<>();
        for (int i = 0; i < Math.min(3, copia.size()); i++) {
            Zombi z = copia.get(i);
            ranking.add(z.getZombiId() + " - " + z.muertes + " muertes");
        }
        return ranking;
    }

    public void run() {
        while (true) {
            try {
                controlPausa.esperarSiPausado();

                int tiempo = ThreadLocalRandom.current().nextInt(500, 1501);
                zonaActual.agregarZombi(this);
                log.escribir("El zombi " + id + " entra en la zona de riesgo " + zonaActual.getId() + ".");

                controlPausa.esperarSiPausado();

                if (zonaActual.hayHumanos()) {
                    Humano victima = zonaActual.seleccionarHumanoAleatorio();
                    log.escribir("El zombi " + id + " selecciona al humano " + victima.getId() + " para atacar.");
                    try {
                        controlPausa.esperarSiPausado();
                        victima.serAtacado(tiempo, this);

                        for (int t = 0; t < tiempo; t += 100) {
                            controlPausa.esperarSiPausado();
                            Thread.sleep(100);
                        }

                        if (!victima.isVivo()) {
                            muertes += 1;
                            log.escribir("El zombi Z" + id + " ha matado al humano H" + victima.getId() + ". Número de muertes: " + muertes);
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }

                int reposo = ThreadLocalRandom.current().nextInt(2000, 3001);
                for (int t = 0; t < reposo; t += 100) {
                    controlPausa.esperarSiPausado();
                    Thread.sleep(100);
                }

                zonaActual.quitarZombi(this);
                log.escribir("El zombi " + id + " sale de la zona de riesgo " + zonaActual.getId() + ".");

                controlPausa.esperarSiPausado();
                zonaActual = areaRiesgo.getZonaRiesgoAleatoria();

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
