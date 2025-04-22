/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.tuneles;
import java.util.HashSet;
import java.util.Set;
import poo.apocalipsiszombie.hilos.Humano;
/**
 *
 * @author unaih
 */
public class Tunel {
    private final int id;
    private Set<Humano> personas = new HashSet<>();

    public Tunel(int id){
        this.id=id;
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
    
    @Override
    public String toString() {
        return "Tunel{" + "id=" + id + '}';
    } 
}
