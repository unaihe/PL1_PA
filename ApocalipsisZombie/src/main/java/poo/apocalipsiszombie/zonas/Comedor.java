/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.ControlPausa;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.hilos.Humano;

/**
 *
 * @author unaih
 */
public class Comedor {

    private int nComida = 0;
    private final Semaphore semComida;
    private Logger log;
    private interfaz.Interfaz interfaz;
    private Queue<Humano> personas = new ConcurrentLinkedQueue<>();
    private ControlPausa controlPausa;

    /**
     * Constructor de la clase COmedor.
     *
     * @param log
     * @param interfaz
     * @param controlPausa
     */
    public Comedor(Logger log, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        this.semComida = new Semaphore(0, true); //Crear semaforo de contador
        this.log = log;
        this.controlPausa = controlPausa;
        this.interfaz = interfaz;
    }

    /**
     * Añade dos unidades de comida al almacén del refugio. Libera dos permisos
     * en el semáforo de comida, notifica a los hilos que puedan estar esperando
     * y actualiza la interfaz gráfica para reflejar el nuevo número de unidades
     * de comida disponibles.
     */
    public synchronized void dejarComida() {
        nComida += 2;
        semComida.release(2); // Libera 2 permisos (2 unidades de comida)
        notifyAll(); // Notifica a los hilos que esperan comida
        SwingUtilities.invokeLater(()
                -> interfaz.actualizarComida(nComida)
        );
    }

    /**
     * Permite a un humano coger una unidad de comida del almacén del refugio.
     * El método respeta la pausa global y bloquea al hilo si no hay comida
     * disponible. Tras adquirir la comida, actualiza la interfaz gráfica para
     * reflejar el cambio.
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     * por comida.
     */
    public void cogerComida() throws InterruptedException {
        controlPausa.esperarSiPausado();
        semComida.acquire(); // Intenta adquirir 1 permiso (1 unidad de comida)
        controlPausa.esperarSiPausado();
        synchronized (this) {
            nComida--;
        }
        SwingUtilities.invokeLater(()
                -> interfaz.actualizarComida(nComida)
        );
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
