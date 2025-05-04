/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie;

/**
 * Clase de utilidad para controlar la pausa y reanudación global de la simulación.
 * Permite que cualquier hilo participante en la simulación pueda comprobar si debe
 * detenerse temporalmente (pausa) y reanudar su ejecución cuando corresponda.
 * Los métodos están sincronizados para garantizar la correcta coordinación entre hilos.
 * @author unaih
 */
public class ControlPausa {
    private volatile boolean enPausa = false;
    /**
     * Activa la pausa global de la simulación.
     * Los hilos que llamen a esperarSiPausado() quedarán bloqueados hasta que se reanude.
     */
    public synchronized void pausar() {
        this.enPausa = true;
    }
    /**
     * Reanuda la simulación, despertando a todos los hilos que estaban esperando por la pausa.
     */
    public synchronized void reanudar() {
        this.enPausa = false;
        notifyAll();
    }
    /**
     * Hace que el hilo actual espere mientras la simulación esté en pausa.
     * Debe llamarse periódicamente desde los hilos participantes para respetar la pausa global.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public synchronized void esperarSiPausado() throws InterruptedException {
        while (enPausa) {
            wait();
        }
    }
    /**
     * Indica si la simulación está actualmente en pausa.
     * @return True si la simulación está en pausa, False en caso contrario.
     */
    public synchronized boolean isEnPausa() {
        return enPausa;
    }
}
