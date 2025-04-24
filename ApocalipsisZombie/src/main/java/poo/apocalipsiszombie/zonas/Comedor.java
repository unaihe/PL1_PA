/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import java.util.concurrent.Semaphore;

/**
 *
 * @author unaih
 */
public class Comedor extends Zona{
    private int nComida=0;
    private final Semaphore semComida;

    //Crear semaforo de contador
    public Comedor() {
        this.semComida = new Semaphore(0,true);
    }
    public synchronized void dejarComida() {
        nComida += 2;
        semComida.release(2); // Libera 2 permisos (2 unidades de comida)
        notifyAll(); // Notifica a los hilos que esperan comida
    }
    public void cogerComida() throws InterruptedException {
        semComida.acquire(); // Intenta adquirir 1 permiso (1 unidad de comida)
        synchronized (this) {
            nComida--;
        }
    }
    
    public synchronized int getCantidadComida() {
        return nComida;
    }
    
}
