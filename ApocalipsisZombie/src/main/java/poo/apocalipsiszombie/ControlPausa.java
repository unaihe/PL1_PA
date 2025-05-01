/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie;

/**
 *
 * @author unaih
 */
public class ControlPausa {
    private volatile boolean enPausa = false;

    public synchronized void pausar() {
        this.enPausa = true;
    }

    public synchronized void reanudar() {
        this.enPausa = false;
        notifyAll();
    }

    public synchronized void esperarSiPausado() throws InterruptedException {
        while (enPausa) {
            wait();
        }
    }

    public synchronized boolean isEnPausa() {
        return enPausa;
    }
}
