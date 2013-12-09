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
public class Train extends Node {

    public Train(AssetManager assetManager) {
        Box boxshape1 = new Box(Vector3f.ZERO, 200f, .5f, 2.8f/3f );
        Geometry Train = new Geometry("Train", boxshape1);
        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex.setColor("Color", ColorRGBA.Pink);
        Train.setMaterial(mat_tex);
        this.attachChild(Train);
        this.setLocalTranslation(290, .5f, -197);
    }

    public void Departure(float tpf) {
        if (this.getLocalTranslation().x < 700) {
            this.move(48f * tpf, 0, 0);
        } else {
            this.removeFromParent();
        }
    }
}
