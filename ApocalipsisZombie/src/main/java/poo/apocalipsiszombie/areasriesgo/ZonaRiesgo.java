package poo.apocalipsiszombie.areasriesgo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import poo.apocalipsiszombie.hilos.Humano;
import poo.apocalipsiszombie.hilos.Zombi;

/**
 *
 * @author unaih
 */
public class ZonaRiesgo {

    private int hZona;
    private final int id;
    private Queue<Humano> personas = new ConcurrentLinkedQueue<>();
    private Queue<Zombi> zombis = new ConcurrentLinkedQueue<>();

    public ZonaRiesgo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Queue<Humano> getPersonas() {
        return personas;
    }

    public void agregarPersona(Humano humano) {
        personas.add(humano);
    }

    public void quitarPersona(Humano humano) {
        personas.remove(humano);
    }

    public Queue<Zombi> getZombis() {
        return zombis;
    }

    public void agregarZombi(Zombi zombi) {
        zombis.add(zombi);
    }

    public void quitarZombi(Zombi zombi) {
        zombis.remove(zombi);
    }

    public boolean hayHumanos() {
        return !personas.isEmpty();
    }

    public Humano seleccionarHumanoAleatorio() {
        if (personas.isEmpty()) {
            return null;
        }
        int idx = ThreadLocalRandom.current().nextInt(personas.size());
        return personas.stream().skip(idx).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "ZonaRiesgo{" + "hZona=" + hZona + ", id=" + id + '}';
    }
}
