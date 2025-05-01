/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.hilos.Humano;

/**
 *
 * @author unaih
 */
public class Comun{
    private Logger log;
    private Queue<Humano> personas = new ConcurrentLinkedQueue<>();
    private interfaz.Interfaz interfaz;
    
    public Comun(Logger log, interfaz.Interfaz interfaz) {
        this.log=log; 
        this.interfaz=interfaz;
    }
    public Queue<Humano> getPersonas() {
        return personas;
    }

    public void agregarPersona(Humano humano) {
        personas.add(humano);
        SwingUtilities.invokeLater(()
            -> interfaz.actualizarZonaComun(personas)
        );
    }

    public void quitarPersona(Humano humano) {
        personas.remove(humano);
        SwingUtilities.invokeLater(()
            -> interfaz.actualizarZonaComun(personas)
        );
    }

}
