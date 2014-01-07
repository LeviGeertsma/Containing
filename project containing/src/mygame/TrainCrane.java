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
import com.jme3.scene.shape.Box;

/**
 * Making a traincrane
 * + allowing the crane to attach the container to the train
 */
public class TrainCrane extends Node {

    int switchcase = 1;
    static int numberTrainCranes = 0;
    Timer timer = new Timer();

    ///making the crane
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

    // setting a container to the train
    public boolean setContainer(Train train, Container container, float tpf, int row) {
        

        switch (switchcase) {
            case 1://moving the crane
                if ((int) this.getLocalTranslation().x < (int)(13 / 3 * row * 1.2f) + (96f)) {
                    this.move(tpf * 3f / 3, 0, 0); // divide by 3 becouse our scale
                } else if ((int) this.getLocalTranslation().x > (int)(13 / 3 * row * 1.2f) + (96f)) {
                    this.move(-tpf * 2f / 3, 0, 0); // divide by 3 becouse of our scale
                }

                if ((int) this.getLocalTranslation().x == (int)(13 / 3 * row * 1.2f) + (96f)) {
                    if(timer.counter(30, tpf)) //start lifting the container
                    switchcase++;
                }
                
                return false;
            case 2://lifting the container
                    container.move(0,tpf * 0.0504f ,0);
                
                    if(timer.counter(120,tpf)) {
                        this.attachChild(container);
                        container.setLocalTranslation(0, 7, 6.7f); //
                        container.rotate(0, FastMath.PI / 2, 0);
                        switchcase++;
                    }
                return false;
            case 3:// moving the container towards the train (horizontal)
                if (container.getLocalTranslation().z > 1.7f) {
                    container.move(0, 0, 5 / 3 * -tpf);// divide by 3 becouse of our scale
                } else 
                    switchcase++;
                return false;
            case 4://letting the container decrease to the crain
                container.move(0, -tpf * 0.065f, 0);
                
                if(timer.counter(60,tpf)){
                    this.detachChild(container);
                    train.attachChild(container);
                    container.setLocalTranslation(-199 + (13 / 3 * row * 1.2f) + 13 / 3, 1, 0);
                    switchcase = 1;
                    return true;
                }
           
                return false;
                
        }
        return false;
    }
    
    public Vector3f getLocation(){
        return this.getLocalTranslation();
    }
}
