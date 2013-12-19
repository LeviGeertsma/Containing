/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author levi
 */
public class Timer {
    public float time=0;
    
    public Timer(){
        
    }
    
    public boolean counter(int endTime, float tpf){
        if(time >=endTime){
            time = 0;
            return true;
        }
        else{
            time += tpf;
            return false;
        }
    }
}
