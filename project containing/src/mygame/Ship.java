/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.scene.Node;

/**
 *
 * @author Levi
 */
public class Ship extends Node{
    public Container[][][] container = new Container[10][100][100];
    
    public Ship(int nrContainers, AssetManager assetManager ){
        int totalContainers=0;
        for(int x=0; x<10;x++){
            for(int y=0; y<100;y++){
                for(int z=0; z<100;z++){
                    if(totalContainers<nrContainers){
                        container[x][y][z] = new Container(assetManager);//, totalContainers in cs
                        container[x][y][z].setLocalTranslation(2.5f*x/3, 2.5f*y/3, 13*z/3);
                        container[x][y][z].rotate(0, FastMath.PI/2, 0);
                        this.attachChild(container[x][y][z]);
                        
                    }
                }
            }
        }
        
            
    }
}
