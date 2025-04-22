/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.areasriesgo;

/**
 *
 * @author unaih
 */
public class ZonaRiesgo {
    private int hZona; 
    private final int id;
    public ZonaRiesgo(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ZonaRiesgo{" + "hZona=" + hZona + ", id=" + id + '}';
    }
    
}
