/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.apocalipsiszombie;

import java.util.Random;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.hilos.Humano;
import poo.apocalipsiszombie.hilos.Zombi;
import poo.apocalipsiszombie.tuneles.Tuneles;
import poo.apocalipsiszombie.zonas.Refugio;

/**
 *
 * @author unaih
 */
public class ApocalipsisZombie {

    public static void main(String[] args) {
        Random random=new Random();
        Refugio refugio=new Refugio();
        AreaRiesgo areaRiesgo=new AreaRiesgo();
        Tuneles tuneles=new Tuneles();
        Zombi zombi=new Zombi("Z0000",areaRiesgo);
        Logger logger=new Logger();
        System.out.println(refugio);
        System.out.println(areaRiesgo);
        System.out.println(tuneles);
        for (int i = 0; i < 10000; i++){
            String id = String.format("H%04d", i);
            Humano h = new Humano(id, refugio, tuneles, areaRiesgo, logger);
            System.out.println(h); 
            h.start();
            int tiempoEspera = 500 + random.nextInt(1501); // 500 a 2000 ms
            try {
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
}
