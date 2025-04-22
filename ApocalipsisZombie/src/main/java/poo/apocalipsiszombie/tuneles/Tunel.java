/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.tuneles;
import java.util.HashSet;
import java.util.Set;
import poo.apocalipsiszombie.hilos.Humano;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author unaih
 */
public class Tunel {
    private final int id;
    private Set<Humano> personas = new HashSet<>();
    private CyclicBarrier barrier=new CyclicBarrier(3);
    private final Lock lock = new ReentrantLock();
    private final Condition puedeCruzar = lock.newCondition();
    private int humanosEnTunel = 0;
    private int esperandoRefugio = 0;
    
    public Tunel(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }
    
    public Set<Humano> getPersonas() {
        return personas;
    }

    public void agregarPersona(Humano humano) {
        personas.add(humano);
    }

    public void quitarPersona(Humano humano) {
        personas.remove(humano);
    }
    
    public void esperarGrupo() throws InterruptedException, BrokenBarrierException{
        barrier.await();
    }
    
    public void cruzarTunel(boolean entrandoRefugio) throws InterruptedException {
        lock.lock();
        try {
            if (entrandoRefugio) {
                esperandoRefugio++;
                while (humanosEnTunel > 0) {
                    puedeCruzar.await();
                }
                esperandoRefugio--;
            } else {
                while (humanosEnTunel > 0 || esperandoRefugio > 0) {
                    puedeCruzar.await();
                }
            }
            humanosEnTunel++;
        } finally {
            lock.unlock();
        }

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
