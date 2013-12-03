/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Chris H
 */
public class AGV extends Node {
    final float HEIGHT = 0.1f;
    final float WIDTH = 0.3f;
    final float DEPTH = 1.4f;
    
    boolean isLoaded;
    Vector3f location;
    
    public AGV(AssetManager assetManager, boolean isLoaded, Vector3f location){

        this.isLoaded = isLoaded;
        this.location = location;
        
        Box container1 = new Box(WIDTH, HEIGHT, DEPTH);
        Geometry cont1 = new Geometry("Container", container1);
        Material cont1Mat = new Material(assetManager, 
        "Common/MatDefs/Misc/Unshaded.j3md");
        cont1.setMaterial(cont1Mat);
        cont1.setLocalTranslation(location);
        attachChild(cont1);
    }
    
    void getRoute(){
    }
}
