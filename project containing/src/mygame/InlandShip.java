/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author levi
 */
public class InlandShip extends Node{
    int x=0;
    int y=0;
    int z=0;
    
    public InlandShip(AssetManager assetManager) {
        Box boxMesh = new Box(5f, .4f, 30f);
        Geometry boxGeo = new Geometry("Colored Box", boxMesh);
        Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        boxMat.setColor("Color", ColorRGBA.Blue);
        boxGeo.setMaterial(boxMat);
        //boxGeo.setLocalTranslation(2.5f, -.6f, 15);
        boxGeo.rotate(0, FastMath.PI/2 ,0);
        attachChild(boxGeo);
    }
    
    public void attachContainer(Container container){
        this.attachChild(container);
        container.setLocalTranslation(-25f+13f/3*z, .8f+2.5f/3*y, 2.5f+2.5f/3*x);
        
        
        if(x<5)
            x++;
        else if(y<5)
            y++;
        else 
            z++;
        
    }
    
}
