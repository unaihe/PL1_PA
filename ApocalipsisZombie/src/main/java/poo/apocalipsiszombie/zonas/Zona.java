package poo.apocalipsiszombie.zonas;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import poo.apocalipsiszombie.hilos.Humano;


/**
 *
 * @author unaih
 */
public abstract class Zona {
    private Queue<Humano> personas = new ConcurrentLinkedQueue<>();

    public Queue<Humano> getPersonas() {
        return personas;
    }

    public void agregarPersona(Humano humano) {
        personas.add(humano);
    }

    public void quitarPersona(Humano humano) {
        personas.remove(humano);
    }
}
