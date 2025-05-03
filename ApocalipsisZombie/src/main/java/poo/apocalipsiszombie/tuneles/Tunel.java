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

    public Tunel(int id, Logger log, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        this.id = id;
        this.controlPausa = controlPausa;
        this.log = log;
        this.interfaz = interfaz;
    }

    public Humano getPersonaCruzando() {
        return personaCruzando;
    }

    public boolean isCruzandoTunel() {
        return cruzandoTunel;
    }
    
    public int getId() {
        return id;
    }

    // --- Métodos para personasRefugio ---
    public Queue<Humano> getPersonasRefugio() {
        return personasRefugio;
    }

    public void agregarPersonaRefugio(Humano humano) {
        personasRefugio.add(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRefugio(id, personasRefugio);
        });
    }

    public void quitarPersonaRefugio(Humano humano) {
        personasRefugio.remove(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRefugio(id, personasRefugio);
        });
    }

    // --- Métodos para personasRiesgo ---
    public Queue<Humano> getPersonasRiesgo() {
        return personasRiesgo;
    }

    public void agregarPersonaRiesgo(Humano humano) {
        personasRiesgo.add(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRiesgo(id, personasRiesgo);
        });
    }

    public void quitarPersonaRiesgo(Humano humano) {
        personasRiesgo.remove(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarTunelRiesgo(id, personasRiesgo);
        });
    }

    public void esperarGrupo() throws InterruptedException, BrokenBarrierException {
        barrier.await();
    }

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
        cruzandoTunel=true;
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
            cruzandoTunel=false;
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
