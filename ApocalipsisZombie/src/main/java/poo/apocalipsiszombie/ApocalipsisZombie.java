/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package poo.apocalipsiszombie;


import interfaz.Interfaz;
import java.util.Random;
import poo.apocalipsiszombie.areasriesgo.AreaRiesgo;
import poo.apocalipsiszombie.hilos.Humano;
import poo.apocalipsiszombie.hilos.Zombi;
import poo.apocalipsiszombie.tuneles.Tuneles;
import poo.apocalipsiszombie.zonas.Refugio;
import rmi.SimulacionRemotaImpl;
import rmi.SimulacionRemota;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author unaih
 */
public class ApocalipsisZombie {

    public static void main(String[] args) throws InterruptedException {
        ControlPausa controlPausa = new ControlPausa();
        Interfaz interfaz = new Interfaz(controlPausa);
        interfaz.setVisible(true);
        Random random = new Random();
        Logger logger = new Logger();
        AreaRiesgo areaRiesgo = new AreaRiesgo(logger, interfaz);
        Zombi zombi = new Zombi("Z0000", areaRiesgo, logger, interfaz, controlPausa);
        Refugio refugio = new Refugio(logger, interfaz, controlPausa);
        Tuneles tuneles = new Tuneles(logger, interfaz, controlPausa);
        System.out.println(refugio);
        System.out.println(areaRiesgo);
        System.out.println(tuneles);
        zombi.start();

        try {
            // Crea y exporta el objeto remoto
            SimulacionRemota simulacionRemota = new SimulacionRemotaImpl(refugio, tuneles, areaRiesgo, controlPausa,interfaz);

            // Arranca el registro RMI en el puerto 1099 (si no está ya iniciado)
            LocateRegistry.createRegistry(1099);

            // Registra el objeto remoto con el nombre "SimulacionZombis"
            java.rmi.Naming.rebind("SimulacionZombis", simulacionRemota);

            System.out.println("Servidor RMI iniciado correctamente");
        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }

        for (int i = 0; i < 10000; i++) {
            controlPausa.esperarSiPausado();
            String id = String.format("H%04d", i);
            Humano h = new Humano(id, refugio, tuneles, areaRiesgo, logger, interfaz, controlPausa);
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
