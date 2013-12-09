/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author User
 */
public class Truck extends Node {

    static int numberOfTrucks;
    
    public Truck(AssetManager assetManager) {
        super("Truck " + numberOfTrucks++ + " ");

        Box boxshape1 = new Box(Vector3f.ZERO, 20/6f, 1.25f/6f, 0.5f);
        Box boxshape2 = new Box(Vector3f.ZERO, 5/6f, 0.5f, 0.5f);

        Geometry bottom = new Geometry("the bottom of the truck", boxshape1);
        Geometry cabin = new Geometry("the cabin of the truck", boxshape2);
        

        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex.setColor("Color", ColorRGBA.Gray);

        bottom.setMaterial(mat_tex);
        cabin.setMaterial(mat_tex);

        attachChild(bottom);
        attachChild(cabin);

        bottom.setLocalTranslation(0, 0, 0);
        cabin.setLocalTranslation(15f/6, 2.5f/6, 0);
    }
}
