/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.areasriesgo.ZonaRiesgo;
import poo.apocalipsiszombie.tuneles.Tuneles;
import poo.apocalipsiszombie.tuneles.Tunel;
import poo.apocalipsiszombie.zonas.Refugio;

/**
 *
 * @author unaih
 */
public class Humano extends Thread {

    private final String id;
    private boolean marcado = false;
    private boolean vivo = true;
    private String zonaActual;
    private Refugio refugio;
    private int comidaRecolectada = 0;
    private boolean necesitaDescanso = false;
    private Logger log;
    private Tunel tunel;
    private Tuneles tuneles;
    private AreaRiesgo areaRiesgo;
    private ZonaRiesgo zonaRiesgo;
    private boolean siendoAtacado;
    private boolean ataqueFinalizado;
    private interfaz.Interfaz interfaz;

    @Override
    public String toString() {
        return "Humano{" + "id=" + id + '}';
    }

    public Humano(String id, Refugio refugio, Tuneles tuneles, AreaRiesgo areas, Logger logger, interfaz.Interfaz interfaz) {
        this.id = id;
        this.refugio = refugio;
        log = logger;
        this.tuneles = tuneles;
        this.areaRiesgo = areas;
        this.interfaz = interfaz;
    }

    public boolean isVivo() {
        return vivo;
    }

    public String getHumanoId() {
        return id;
    }

    public synchronized void serAtacado(int tiempo, Zombi zombi) throws InterruptedException {
        ataqueFinalizado = false;
        siendoAtacado = true;
        log.escribir("El zombi " + zombi.getId() + " ataca al humano " + id + " en la zona de riesgo " + zonaRiesgo.getId() + ".");
        Thread.sleep(tiempo);
        boolean sobrevive = ThreadLocalRandom.current().nextInt(3) < 2;
        if (sobrevive) {
            this.marcado = true;
            this.comidaRecolectada = 0;
            this.necesitaDescanso = true;
        } else {
            this.vivo = false;
            zonaRiesgo.quitarPersona(this);
        }
        ataqueFinalizado = true;
        siendoAtacado = false;
        notifyAll();
    }

    public void run() {
        while (isVivo()) {
            try {
                refugio.getComun().agregarPersona(this);
                log.escribir("El humano " + id + " entra en la zona común del refugio.");
                int tiempoComun = ThreadLocalRandom.current().nextInt(1000, 2001);
                Thread.sleep(tiempoComun);
                refugio.getComun().quitarPersona(this);
                log.escribir("El humano " + id + " sale de la zona común del refugio.");
                tunel = tuneles.getTunelAleatorio();
                tunel.agregarPersonaRefugio(this);
                log.escribir("El humano " + id + " espera en el túnel " + tunel.getId() + " para salir al exterior.");
                try {
                    log.escribir("El humano " + id + " espera en el grupo");
                    tunel.esperarGrupo();
                    tunel.cruzarTunel(false, this);
                    int numero = tunel.getId();
                    switch (numero) {
                        case 1:
                            zonaRiesgo = areaRiesgo.getZona1();
                            break;
                        case 2:
                            zonaRiesgo = areaRiesgo.getZona2();
                            break;
                        case 3:
                            zonaRiesgo = areaRiesgo.getZona3();
                            break;
                        case 4:
                            zonaRiesgo = areaRiesgo.getZona4();
                            break;
                    }
                    zonaRiesgo.agregarPersona(this);
                    log.escribir("El humano " + id + " entra en la zona de riesgo " + zonaRiesgo.getId() + ".");
                    long tiempoRecoleccion = ThreadLocalRandom.current().nextLong(3000, 5001);
                    long tiempoPasado = 0;
                    long inicio = System.currentTimeMillis();

                    while (tiempoPasado < tiempoRecoleccion) {
                        if (siendoAtacado) {
                            log.escribir("El humano " + id + " está siendo atacado por un zombi en la zona de riesgo " + zonaRiesgo.getId() + ".");
                            synchronized (this) {
                                while (!ataqueFinalizado) {
                                    try {
                                        wait();
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                        return;
                                    }
                                }
                            }
                            if (!isVivo()) {
                                log.escribir("El humano " + id + " ha muerto en la zona de riesgo " + zonaRiesgo.getId() + ".");
                                String idZombi = "Z" + this.id.substring(1);
                                Zombi nuevoZombi = new Zombi(idZombi, zonaRiesgo, areaRiesgo, log, interfaz);
                                nuevoZombi.start();
                                return; // Termina el hilo humano
                            } else if (marcado) {
                                log.escribir("El humano " + id + " ha sobrevivido al ataque y queda marcado.");
                            }
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                        tiempoPasado = System.currentTimeMillis() - inicio;
                    }
                    zonaRiesgo.quitarPersona(this);
                    log.escribir("El humano " + id + " sale de la zona de riesgo " + zonaRiesgo.getId() + ".");
                    tunel.agregarPersonaRiesgo(this);
                    log.escribir("El humano " + id + " cruza el túnel " + tunel.getId() + " de vuelta al refugio.");
                    tunel.cruzarTunel(true, this);
                    if (!marcado) {
                        // Recolecta comida normalmente
                        this.comidaRecolectada = 2;
                        refugio.getComedor().dejarComida();
                        log.escribir("El humano " + id + " deja comida en el comedor.");
                        this.comidaRecolectada = 0;
                    }
                    refugio.getDescanso().agregarPersona(this);
                    log.escribir("El humano " + id + " entra en la zona de descanso.");
                    int tiempoDescanso = ThreadLocalRandom.current().nextInt(2000, 4001);
                    Thread.sleep(tiempoDescanso);
                    refugio.getDescanso().quitarPersona(this);
                    log.escribir("El humano " + id + " sale de la zona de descanso.");
                    refugio.getComedor().agregarPersona(this);
                    log.escribir("El humano " + id + " entra en el comedor.");
                    refugio.getComedor().cogerComida();
                    log.escribir("El humano " + id + " coge una pieza de comida del comedor.");
                    int tiempoComida = ThreadLocalRandom.current().nextInt(3000, 5001);
                    Thread.sleep(tiempoComida);
                    refugio.getComedor().quitarPersona(this);
                    log.escribir("El humano " + id + " sale del comedor.");
                    if (marcado) {
                        refugio.getDescanso().agregarPersona(this);
                        log.escribir("El humano " + id + " entra en la zona de descanso para recuperarse tras ser atacado.");
                        int tiempoMarcado = ThreadLocalRandom.current().nextInt(3000, 5001);
                        Thread.sleep(tiempoMarcado);
                        refugio.getDescanso().quitarPersona(this);
                        log.escribir("El humano " + id + " sale de la zona de descanso tras recuperarse.");
                    }

                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
