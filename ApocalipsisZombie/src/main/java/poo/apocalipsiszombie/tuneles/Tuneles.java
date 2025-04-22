/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.tuneles;

import java.util.Random;

/**
 *
 * @author unaih
 */
public class Tuneles {
    Tunel tunel1=new Tunel(1);
    Tunel tunel2=new Tunel(2);
    Tunel tunel3=new Tunel(3);
    Tunel tunel4=new Tunel(4);
    public Tuneles(){
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
    
    public Tunel getTunelAleatorio(){
        Random random = new Random();
        int num = random.nextInt(4); // 0, 1, 2 o 3
        switch (num) {
            case 0: return tunel1;
            case 1: return tunel2;
            case 2: return tunel3;
            case 3: return tunel4;
            default: return tunel1;
        }
    }
}
