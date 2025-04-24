/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.areasriesgo;

import java.util.Random;
import poo.apocalipsiszombie.Logger;


/**
 *
 * @author unaih
 */
public class AreaRiesgo {
    private Logger log;
    ZonaRiesgo zona1=new ZonaRiesgo(1,log);
    ZonaRiesgo zona2=new ZonaRiesgo(2,log);
    ZonaRiesgo zona3=new ZonaRiesgo(3,log);
    ZonaRiesgo zona4=new ZonaRiesgo(4,log);
    public AreaRiesgo(Logger log){
        this.log=log;
    }

    @Override
    public String toString() {
        return "AreaRiesgo{" + "zona1=" + zona1 + ", zona2=" + zona2 + ", zona3=" + zona3 + ", zona4=" + zona4 + '}';
    }
    
    
    
    public ZonaRiesgo getZona1() {
        return zona1;
    }

    public ZonaRiesgo getZona2() {
        return zona2;
    }

    public ZonaRiesgo getZona3() {
        return zona3;
    }

    public ZonaRiesgo getZona4() {
        return zona4;
    }
    public ZonaRiesgo getZonaRiesgoAleatoria(){
        Random random = new Random();
        int num = random.nextInt(4); // 0, 1, 2 o 3
        switch (num) {
            case 0: return zona1;
            case 1: return zona2;
            case 2: return zona3;
            case 3: return zona4;
            default: return zona1;
        }
    }
}
