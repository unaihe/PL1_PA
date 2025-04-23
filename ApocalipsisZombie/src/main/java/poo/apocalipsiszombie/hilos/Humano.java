/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ThreadLocalRandom;
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

    @Override
    public String toString() {
        return "Humano{" + "id=" + id + '}';
    }

    public Humano(String id, Refugio refugio, Tuneles tuneles, AreaRiesgo areas, Logger logger) {
        this.id = id;
        this.refugio = refugio;
        log = logger;
        this.tuneles = tuneles;
        this.areaRiesgo = areas;
    }

    public void serAtacado(int tiempo, Zombi zombi) throws InterruptedException {
        siendoAtacado = true;
        Thread.sleep(tiempo);
        boolean sobrevive = ThreadLocalRandom.current().nextInt(3) < 2;
        if (sobrevive) {
            this.marcado = true;
            this.comidaRecolectada = 0;
        } else {
            this.vivo = false;
            zonaRiesgo.quitarPersona(this);
            
            String idZombi = "Z" + this.id.substring(1); 
            Zombi nuevoZombi = new Zombi(idZombi, areaRiesgo);
            
        }
        siendoAtacado=false;
    }

    public void run() {
        try {
            refugio.getComun().agregarPersona(this);
            int tiempoComun = ThreadLocalRandom.current().nextInt(1000, 2001); // 1000 a 2000 ms
            Thread.sleep(tiempoComun);
            refugio.getComun().quitarPersona(this);
            tunel = tuneles.getTunelAleatorio();
            tunel.agregarPersona(this);
            try {
                tunel.esperarGrupo();
                tunel.cruzarTunel(false);
                tunel.quitarPersona(this);
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
                long tiempoRecoleccion = ThreadLocalRandom.current().nextLong(3000, 5001);
                long tiempoPasado = 0;
                long inicio = System.currentTimeMillis();

                while (tiempoPasado < tiempoRecoleccion) {
                    if (siendoAtacado) {
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
                        // Aquí termina el ataque, decide si sobrevive o no (probabilidad 2/3)
                        boolean sobrevive = ThreadLocalRandom.current().nextInt(3) < 2;
                        if (sobrevive) {
                            // Marcado, vuelve sin comida
                            this.marcado = true;
                            // salir de la zona y volver al refugio...
                        } else {
                            // Muere y renace como zombi
                            zonaRiesgo.quitarPersona(this);
                            // Crear nuevo zombi con el mismo id numérico...
                            return; // Termina el hilo humano
                        }
                        break;
                    }
                    try {
                        Thread.sleep(100); // Chequeo periódico
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    tiempoPasado = System.currentTimeMillis() - inicio;
                }

                if (!siendoAtacado) {
                    // Recolecta comida normalmente
                    this.comidaRecolectada = 2;
                }

                zonaRiesgo.quitarPersona(this);

            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
