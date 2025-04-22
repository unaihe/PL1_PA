/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

import java.util.ArrayList;
import java.util.List;
import poo.apocalipsiszombie.hilos.Humano;

/**
 *
 * @author unaih
 */
public abstract class  Zona{
    private List<Humano> personas = new ArrayList<>();

    public List<Humano> getPersonas() {
        return personas;
    }
    public void agregarPersona(Humano humano) {
        personas.add(humano);
    }
}
