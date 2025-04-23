/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.apocalipsiszombie.zonas;

/**
 *
 * @author unaih
 */
public class Comedor extends Zona{
    private int nComida=0;
    
    //Crear semaforo de contador
    public Comedor() {
    }
    public void dejarComida(){
        nComida+=2;
        signalAll();
    }
    public void cogerComida(){
        if(nComida==0){
            nComida-=1;}
        else{
        
        }
    }
    
}
