/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.hilos;

/**
 *
 * @author unaih
 */
public class Zombi extends Thread{
    private final String id;
    private int zonaActual;
    private int muertes=0;
    private boolean atacando;
    
    public Zombi(String id,int zonaActual){
        this.id=id;
        this.zonaActual=zonaActual;
    }
    public void run(){
    
    };
}
