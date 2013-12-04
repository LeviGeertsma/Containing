/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author User
 */
public class TrainCrane extends Node {

    int getContainerInt = 1;
    static int numberTrainCranes = 0;

    public TrainCrane(AssetManager assetManager) {
        super("NumberTrainCranes " + numberTrainCranes++ + " ");
        Box boxshape1 = new Box(Vector3f.ZERO, 1f, 4f, 0.5f);
        Box boxshape2 = new Box(Vector3f.ZERO, 1f, 0.5f, 4f);

        Geometry zijkant1 = new Geometry("A Textured Box", boxshape1);
        Geometry bovenkant = new Geometry("A Textured Box", boxshape2);
        Geometry zijkant2 = new Geometry("A Textured Box", boxshape1);

        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex.setColor("Color", ColorRGBA.Yellow);

        zijkant1.setMaterial(mat_tex);
        zijkant2.setMaterial(mat_tex);
        bovenkant.setMaterial(mat_tex);

        attachChild(zijkant1);
        attachChild(zijkant2);
        attachChild(bovenkant);

        zijkant1.setLocalTranslation(0, 4f, 0);
        zijkant2.setLocalTranslation(0, 4f, 8f);
        bovenkant.setLocalTranslation(0, 8, 4);
    }

    public void setContainer(Container container, int location, float tpf, int row,
            boolean isLoaded) {

        String numberOfTrainCranes = "";
        switch (getContainerInt) {

            case 1:
                if (super.name.charAt(13) != ' ') {
                    // hier zet je de 2 mogelijke nummers van de huidige kraan neer
                    numberOfTrainCranes += super.name.charAt(18) + super.name.charAt(19);
                } else {
                    numberOfTrainCranes += super.name.charAt(18);
                }
                
                if ((int) this.getLocalTranslation().x < (13 / 3 * row * 1.2f) +100f + (125f * Integer.parseInt(numberOfTrainCranes)) ) {
                    this.move(tpf * 1f, 0, 0);
                } else if ((int) this.getLocalTranslation().x > (13 / 3 * row * 1.2f) +100f + (125f * Integer.parseInt(numberOfTrainCranes))) {
                    this.move(-tpf * 1f, 0, 0);
                }

                if ((int) this.getLocalTranslation().x == (13 / 3 * row * 1.2f) +100f + (125f * Integer.parseInt(numberOfTrainCranes))) {
                    getContainerInt++;
                    this.attachChild(container);
                    container.setLocalTranslation(0, 6, 1.5f);
                    container.rotate(0, FastMath.PI / 2, 0);
                }
                break;
            case 2:
                if (container.getLocalTranslation().z < 6.5f) {
                    container.move(0, 0, 1 * tpf);
                } else {
                    getContainerInt++;
                    this.detachChild(container);
                   //Spatial  train = this.parent.getChild(train);
                    Train train = new train()
                    container.setLocalTranslation(this.getLocalTranslation().x, 1, this.getLocalTranslation().z + 6.5f);
                }
                break;
        }
    }
}
