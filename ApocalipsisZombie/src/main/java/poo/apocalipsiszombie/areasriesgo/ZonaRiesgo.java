/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.areasriesgo;

import java.util.HashSet;
import java.util.Set;
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
    private Set<Humano> personas = new HashSet<>();
    private Set<Zombi> zombis = new HashSet<>();

    public ZonaRiesgo(int id) {
        this.id = id;
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

    public Set<Zombi> getZombis() {
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
