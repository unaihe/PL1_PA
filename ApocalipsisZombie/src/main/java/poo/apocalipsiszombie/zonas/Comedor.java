/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.hilos.Humano;

/**
 *
 * @author unaih
 */
public class Comedor{
    private int nComida=0;
    private final Semaphore semComida;
    private Logger log;
    private interfaz.Interfaz interfaz;
    private Queue<Humano> personas = new ConcurrentLinkedQueue<>();

    //Crear semaforo de contador
    public Comedor(Logger log,interfaz.Interfaz interfaz) {
        this.semComida = new Semaphore(0,true);
        this.log=log;
        this.interfaz=interfaz;
    }
    public synchronized void dejarComida() {
        nComida += 2;
        semComida.release(2); // Libera 2 permisos (2 unidades de comida)
        notifyAll(); // Notifica a los hilos que esperan comida
        SwingUtilities.invokeLater(()
            -> interfaz.actualizarComida(nComida)
        );
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
    
    public Queue<Humano> getPersonas() {
        return personas;
    }

    public void agregarPersona(Humano humano) {
        personas.add(humano);
        SwingUtilities.invokeLater(()
            -> interfaz.actualizarComedor(personas)
        );
    }

    public void quitarPersona(Humano humano) {
        personas.remove(humano);
        SwingUtilities.invokeLater(()
            -> interfaz.actualizarComedor(personas)
        );
    }
    
}
