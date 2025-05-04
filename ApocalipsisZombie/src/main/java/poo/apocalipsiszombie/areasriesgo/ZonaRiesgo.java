package poo.apocalipsiszombie.areasriesgo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.SwingUtilities;
import poo.apocalipsiszombie.Logger;
import poo.apocalipsiszombie.hilos.Humano;
import poo.apocalipsiszombie.hilos.Zombi;

/**
 *
 * @author unaih
 */
public class ZonaRiesgo {

    private int hZona;
    private final int id;
    private interfaz.Interfaz interfaz;
    private Queue<Humano> personas = new ConcurrentLinkedQueue<>();
    private Queue<Zombi> zombis = new ConcurrentLinkedQueue<>();
    private Logger log;

    public ZonaRiesgo(int id, Logger log, interfaz.Interfaz interfaz) {
        this.id = id;
        this.log = log;
        this.interfaz = interfaz;
    }

    public int getId() {
        return id;
    }

    public Queue<Humano> getPersonas() {
        return personas;
    }

    public void agregarPersona(Humano humano) {
        personas.add(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarZonaRiesgoHumanos(id, personas);
        });
    }

    public void quitarPersona(Humano humano) {
        personas.remove(humano);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarZonaRiesgoHumanos(id, personas);
        });
    }

    public Queue<Zombi> getZombis() {
        return zombis;
    }

    public void agregarZombi(Zombi zombi) {
        zombis.add(zombi);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarZonaRiesgoZombis(id, zombis);
        });
    }

    public void quitarZombi(Zombi zombi) {
        zombis.remove(zombi);
        SwingUtilities.invokeLater(() -> {
            interfaz.actualizarZonaRiesgoZombis(id, zombis);
        });
    }

    /**
     * Método utilizado por zombis para comprobar
     * si hay humanos en la zona de riesgo en la que se encuentran.
     * @return True si hay humanos en la lista, False si no los hay.
     */
    public boolean hayHumanos() {
        return !personas.isEmpty();
    }

    /**
     * Método para seleccionar un humano aleatorio de la lista.
     * Se ejecuta si la función anterior es True y hay más de un humano.
     * @return Humano seleccionado para el ataque del zombi.
     */
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
