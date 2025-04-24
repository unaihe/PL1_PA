package poo.apocalipsiszombie.tuneles;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import poo.apocalipsiszombie.hilos.Humano;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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

    public Tunel(int id,Logger log) {
        this.id = id;
        this.log=log;
    }

    public Humano getPersonaCruzando() {
        return personaCruzando;
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
    }

    public void quitarPersonaRefugio(Humano humano) {
        personasRefugio.remove(humano);
    }

    // --- Métodos para personasRiesgo ---
    public Queue<Humano> getPersonasRiesgo() {
        return personasRiesgo;
    }

    public void agregarPersonaRiesgo(Humano humano) {
        personasRiesgo.add(humano);
    }

    public void quitarPersonaRiesgo(Humano humano) {
        personasRiesgo.remove(humano);
    }

    public void esperarGrupo() throws InterruptedException, BrokenBarrierException {
        barrier.await();
    }

    public void cruzarTunel(boolean entrandoRefugio,Humano humano) throws InterruptedException {
        lock.lock();
        log.escribir("El humano"+ humano.getHumanoId() + " espera a que el tunel esté libre");
        try {
            if (entrandoRefugio) {
                esperandoRefugio++;
                while (humanosEnTunel > 0) {
                    puedeCruzar.await();
                }
                esperandoRefugio--;
                personasRiesgo.remove(humano);
            } else {
                while (humanosEnTunel > 0 || esperandoRefugio > 0) {
                    puedeCruzar.await();
                }
                personasRefugio.remove(humano);
            }
            humanosEnTunel++;
        } finally {
            lock.unlock();
        }
        log.escribir("El humano " + humano.getHumanoId() + " cruza el túnel " + this.id + " hacia la zona de riesgo.");
        Thread.sleep(1000);

        lock.lock();
        try {
            humanosEnTunel--;
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
