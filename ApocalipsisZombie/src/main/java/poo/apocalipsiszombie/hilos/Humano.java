/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.ControlPausa;
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
    private ControlPausa controlPausa;

    @Override
    public String toString() {
        return "Humano{" + "id=" + id + '}';
    }

    /**
     * Constructor de la clase humano(Hilo)
     * @param id
     * @param refugio
     * @param tuneles
     * @param areas
     * @param logger
     * @param interfaz
     * @param controlPausa
     */
    public Humano(String id, Refugio refugio, Tuneles tuneles, AreaRiesgo areas, Logger logger, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        this.id = id;
        this.refugio = refugio;
        log = logger;
        this.controlPausa = controlPausa;
        this.tuneles = tuneles;
        this.areaRiesgo = areas;
        this.interfaz = interfaz;
    }
    /**
     * Método para determinar el estado del humano.
     * @return true si el humano está vivo, false si ha muerto.
     */
    public boolean isVivo() {
        return vivo;
    }
    
    public String getHumanoId() {
        return id;
    }

    /**
     * Método sincronizado que gestiona el ataque de un zombi a este humano.
     * Marca al humano como atacado, espera el tiempo de ataque y determina
     * aleatoriamente si el humano sobrevive o muere. Si muere, actualiza el
     * estado y registra el evento.
     *
     * @param tiempo
     * @param zombi Instancia del zombi atacante.
     * @throws java.lang.InterruptedException
     */
    public synchronized void serAtacado(int tiempo, Zombi zombi) throws InterruptedException {
        ataqueFinalizado = false;
        siendoAtacado = true;
        log.escribir("El zombi " + zombi.getId() + " ataca al humano " + id + " en la zona de riesgo " + zonaRiesgo.getId() + ".");
        controlPausa.esperarSiPausado();
        Thread.sleep(tiempo);
        controlPausa.esperarSiPausado();
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

    /**
     * Método principal del hilo Humano. Simula el ciclo de vida del humano:
     * descanso, alimentación, espera, cruce de túneles, recolección de comida
     * en zona de riesgo y retorno al refugio, repitiendo el ciclo mientras el
     * humano siga vivo.
     */
    public void run() {
        while (isVivo()) {
            try {
                // PAUSA antes de entrar en zona común
                controlPausa.esperarSiPausado();
                refugio.getComun().agregarPersona(this);
                log.escribir("El humano " + id + " entra en la zona común del refugio.");

                int tiempoComun = ThreadLocalRandom.current().nextInt(1000, 2001);
                for (int t = 0; t < tiempoComun; t += 100) {
                    controlPausa.esperarSiPausado();
                    Thread.sleep(100);
                }

                refugio.getComun().quitarPersona(this);
                log.escribir("El humano " + id + " sale de la zona común del refugio.");

                // PAUSA antes de elegir túnel
                controlPausa.esperarSiPausado();
                tunel = tuneles.getTunelAleatorio();
                tunel.agregarPersonaRefugio(this);
                log.escribir("El humano " + id + " espera en el túnel " + tunel.getId() + " para salir al exterior.");

                controlPausa.esperarSiPausado();
                try {
                    log.escribir("El humano " + id + " espera en el grupo");
                    tunel.esperarGrupo();
                    controlPausa.esperarSiPausado();

                    tunel.cruzarTunel(false, this);
                    controlPausa.esperarSiPausado();

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
                    controlPausa.esperarSiPausado();

                    long tiempoRecoleccion = ThreadLocalRandom.current().nextLong(3000, 5001);
                    long tiempoPasado = 0;
                    long inicio = System.currentTimeMillis();

                    while (tiempoPasado < tiempoRecoleccion) {
                        if (siendoAtacado) {
                            log.escribir("El humano " + id + " está siendo atacado por un zombi en la zona de riesgo " + zonaRiesgo.getId() + ".");
                            synchronized (this) {
                                while (!ataqueFinalizado) {
                                    try {
                                        controlPausa.esperarSiPausado();
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
                                Zombi nuevoZombi = new Zombi(idZombi, zonaRiesgo, areaRiesgo, log, interfaz, controlPausa);
                                nuevoZombi.start();
                                return; // Termina el hilo humano
                            } else if (marcado) {
                                log.escribir("El humano " + id + " ha sobrevivido al ataque y queda marcado.");
                            }
                            break;
                        }
                        controlPausa.esperarSiPausado();
                        Thread.sleep(100);
                        tiempoPasado = System.currentTimeMillis() - inicio;
                    }

                    zonaRiesgo.quitarPersona(this);
                    log.escribir("El humano " + id + " sale de la zona de riesgo " + zonaRiesgo.getId() + ".");
                    controlPausa.esperarSiPausado();

                    tunel.agregarPersonaRiesgo(this);
                    log.escribir("El humano " + id + " cruza el túnel " + tunel.getId() + " de vuelta al refugio.");

                    tunel.cruzarTunel(true, this);
                    controlPausa.esperarSiPausado();

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
                    for (int t = 0; t < tiempoDescanso; t += 100) {
                        controlPausa.esperarSiPausado();
                        Thread.sleep(100);
                    }
                    refugio.getDescanso().quitarPersona(this);
                    log.escribir("El humano " + id + " sale de la zona de descanso.");

                    refugio.getComedor().agregarPersona(this);
                    log.escribir("El humano " + id + " entra en el comedor.");
                    refugio.getComedor().cogerComida();
                    log.escribir("El humano " + id + " coge una pieza de comida del comedor.");
                    int tiempoComida = ThreadLocalRandom.current().nextInt(3000, 5001);
                    for (int t = 0; t < tiempoComida; t += 100) {
                        controlPausa.esperarSiPausado();
                        Thread.sleep(100);
                    }
                    refugio.getComedor().quitarPersona(this);
                    log.escribir("El humano " + id + " sale del comedor.");

                    if (marcado) {
                        refugio.getDescanso().agregarPersona(this);
                        log.escribir("El humano " + id + " entra en la zona de descanso para recuperarse tras ser atacado.");
                        int tiempoMarcado = ThreadLocalRandom.current().nextInt(3000, 5001);
                        for (int t = 0; t < tiempoMarcado; t += 100) {
                            controlPausa.esperarSiPausado();
                            Thread.sleep(100);
                        }
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
