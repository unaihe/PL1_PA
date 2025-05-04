package poo.apocalipsiszombie.tuneles;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import poo.apocalipsiszombie.hilos.Humano;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.ControlPausa;
import poo.apocalipsiszombie.Logger;

/**
 *
 * @author unaih
 */
public class Tunel {

    private final int id;
    private Logger log;
    // Humanos esperando para salir del refugio (hacia el riesgo)
    private Queue<Humano> personasRefugio = new ConcurrentLinkedQueue<>();
    // Humanos esperando para volver del riesgo al refugio
    private Queue<Humano> personasRiesgo = new ConcurrentLinkedQueue<>();
    private CyclicBarrier barrier = new CyclicBarrier(3);
    private final Lock lock = new ReentrantLock();
    private final Condition puedeCruzar = lock.newCondition();
    private int humanosEnTunel = 0;
    private int esperandoRefugio = 0;
    private Humano personaCruzando;
    private boolean cruzandoTunel;
    private interfaz.Interfaz interfaz;
    private ControlPausa controlPausa;

    /**
     * Constructor de la clase Tunel.
     *
     * @param id
     * @param log
     * @param interfaz
     * @param controlPausa
     */
    public Tunel(int id, Logger log, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        this.id = id;
        this.controlPausa = controlPausa;
        this.log = log;
        this.interfaz = interfaz;
    }

    public Humano getPersonaCruzando() {
        return personaCruzando;
    }

    /**
     * Indica si actualmente hay un humano cruzando el túnel.
     *
     * @return true si hay un humano cruzando el túnel, false en caso contrario.
     */
    public boolean isCruzandoTunel() {
        return cruzandoTunel;
    }

    public int getId() {
        return id;
    }

    // Métodos para Lista personasRefugio
    public Queue<Humano> getPersonasRefugio() {
        return personasRefugio;
    }

    /**
     * Añade un humano a la lista de personas en el tunel en el lado del refugio
     *
     * @param humano
     */
    public void agregarPersonaRefugio(Humano humano) {
        personasRefugio.add(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRefugio(id, personasRefugio);
        });
    }

    /**
     * Elimina el humano introducido como argumento de la lista
     *
     * @param humano
     */
    public void quitarPersonaRefugio(Humano humano) {
        personasRefugio.remove(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRefugio(id, personasRefugio);
        });
    }

    //Métodos para Lista personasRiesgo 
    public Queue<Humano> getPersonasRiesgo() {
        return personasRiesgo;
    }

    /**
     * Añade un humano a la lista de personas en el tunel en el lado del riesgo.
     *
     * @param humano
     */
    public void agregarPersonaRiesgo(Humano humano) {
        personasRiesgo.add(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRiesgo(id, personasRiesgo);
        });
    }

    /**
     * Elimina el humano introducido como argumento de la lista
     *
     * @param humano
     */
    public void quitarPersonaRiesgo(Humano humano) {
        personasRiesgo.remove(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRiesgo(id, personasRiesgo);
        });
    }

    /**
     * Hace que el hilo espere hasta que se complete un grupo para cruzar el
     * túnel.
     *
     * @throws InterruptedException
     * @throws BrokenBarrierException
     */
    public void esperarGrupo() throws InterruptedException, BrokenBarrierException {
        barrier.await();
    }

    /**
     * Gestiona el proceso de cruce del túnel por un humano, controlando la
     * concurrencia, la actualización de la interfaz y el respeto a la pausa
     * global.
     *
     * @param entrandoRefugio true si el humano entra al refugio, false si sale
     * hacia la zona de riesgo.
     * @param humano humano que realiza el cruce.
     * @throws InterruptedException si el hilo es interrumpido durante el cruce.
     */
    public void cruzarTunel(boolean entrandoRefugio, Humano humano) throws InterruptedException {
        lock.lock();
        log.escribir("El humano" + humano.getHumanoId() + " espera a que el tunel esté libre");
        try {
            if (entrandoRefugio) {
                esperandoRefugio++;
                while (humanosEnTunel > 0) {
                    puedeCruzar.await();
                }
                esperandoRefugio--;
                personasRiesgo.remove(humano);
                SwingUtilities.invokeLater(() -> {
                    interfaz.actualizarTunelRiesgo(id, personasRiesgo);
                });
            } else {
                while (humanosEnTunel > 0 || esperandoRefugio > 0) {
                    puedeCruzar.await();
                }
                personasRefugio.remove(humano);
                SwingUtilities.invokeLater(() -> {
                    interfaz.actualizarTunelRefugio(id, personasRefugio);
                });
            }
            humanosEnTunel++;
        } finally {
            lock.unlock();
        }
        java.util.List<Humano> cruzando = java.util.Collections.singletonList(humano);
        SwingUtilities.invokeLater(() -> interfaz.actualizarTunelCruzando(id, cruzando));

        log.escribir("El humano " + humano.getHumanoId() + " cruza el túnel " + this.id + " hacia la zona de riesgo.");
        cruzandoTunel = true;
        int tiempoCruce = 1000; // total en ms
        int paso = 100;
        for (int t = 0; t < tiempoCruce; t += paso) {
            controlPausa.esperarSiPausado();
            Thread.sleep(paso);
        }
        SwingUtilities.invokeLater(() -> interfaz.actualizarTunelCruzando(id, java.util.Collections.emptyList()));
        lock.lock();
        try {
            humanosEnTunel--;
            cruzandoTunel = false;
            puedeCruzar.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Tunel{" + "id=" + id + '}';
    }
}
