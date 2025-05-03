/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.tuneles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import poo.apocalipsiszombie.ControlPausa;
import poo.apocalipsiszombie.Logger;

/**
 *
 * @author unaih
 */
public class Tuneles {

    private Logger logger;
    private Tunel tunel1, tunel2, tunel3, tunel4;
    private interfaz.Interfaz interfaz;
    private ControlPausa controlPausa;

    public Tuneles(Logger logger, interfaz.Interfaz interfaz, ControlPausa controlPausa) {
        this.logger = logger;
        this.interfaz = interfaz;
        this.controlPausa = controlPausa;
        tunel1 = new Tunel(1, logger, interfaz, controlPausa);
        tunel2 = new Tunel(2, logger, interfaz, controlPausa);
        tunel3 = new Tunel(3, logger, interfaz, controlPausa);
        tunel4 = new Tunel(4, logger, interfaz, controlPausa);
    }

    @Override
    public String toString() {
        return "Tuneles{" + "tunel1=" + tunel1 + ", tunel2=" + tunel2 + ", tunel3=" + tunel3 + ", tunel4=" + tunel4 + '}';
    }

    public Tunel getTunel1() {
        return tunel1;
    }

    public Tunel getTunel2() {
        return tunel2;
    }

    public Tunel getTunel3() {
        return tunel3;
    }

    public Tunel getTunel4() {
        return tunel4;
    }

    public Tunel getTunelAleatorio() {
        Random random = new Random();
        int num = random.nextInt(4); // 0, 1, 2 o 3
        switch (num) {
            case 0:
                return tunel1;
            case 1:
                return tunel2;
            case 2:
                return tunel3;
            case 3:
                return tunel4;
            default:
                return tunel1;
        }
    }

    public List<Integer> getNumHumanosPorTunel() {
        List<Integer> resultado = new ArrayList<>();
        resultado.add(tunel1.getPersonasRefugio().size() + tunel1.getPersonasRiesgo().size());
        resultado.add(tunel2.getPersonasRefugio().size() + tunel2.getPersonasRiesgo().size());
        resultado.add(tunel3.getPersonasRefugio().size() + tunel3.getPersonasRiesgo().size());
        resultado.add(tunel4.getPersonasRefugio().size() + tunel4.getPersonasRiesgo().size());
        return resultado;
    }
}
