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
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author User
 */
public class InlandCrane extends Node {
    //dezelfde clas als binnenvaartkraan alleen dan straks met andere
    //properties voor de kraan

    int getContainerInt = 0;
    Timer timer = new Timer();

    public InlandCrane(AssetManager assetManager) {


        Material binnenvaartkraanMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        binnenvaartkraanMat.setColor("Color", ColorRGBA.DarkGray);


        Spatial binnenvaartkraan1 = new Geometry("ColoredBox", new Box(1f, 7.5f, .5f));
        Spatial binnenvaartkraan2 = new Geometry("ColoredBox", new Box(1f, 7.5f, .5f));
        Spatial binnenvaartkraan3 = new Geometry("ColoredBox", new Box(1f, 7.5f, .5f));
        Spatial binnenvaartkraan4 = new Geometry("ColoredBox", new Box(1f, 7.5f, .5f));
        Spatial binnenvaartkraan5 = new Geometry("ColoredBox", new Box(1f, .5f, 3.5f));
        Spatial binnenvaartkraan6 = new Geometry("ColoredBox", new Box(1f, .5f, 3.5f));
        Spatial binnenvaartkraan7 = new Geometry("ColoredBox", new Box(10f, .5f, 1f));

        binnenvaartkraan1.setMaterial(binnenvaartkraanMat);
        binnenvaartkraan2.setMaterial(binnenvaartkraanMat);
        binnenvaartkraan3.setMaterial(binnenvaartkraanMat);
        binnenvaartkraan4.setMaterial(binnenvaartkraanMat);
        binnenvaartkraan5.setMaterial(binnenvaartkraanMat);
        binnenvaartkraan6.setMaterial(binnenvaartkraanMat);
        binnenvaartkraan7.setMaterial(binnenvaartkraanMat);

        binnenvaartkraan1.setLocalTranslation(0, 7, 0);
        binnenvaartkraan2.setLocalTranslation(0, 7, 6);
        binnenvaartkraan3.setLocalTranslation(4, 7, 0);
        binnenvaartkraan4.setLocalTranslation(4, 7, 6);
        binnenvaartkraan5.setLocalTranslation(0, 15, 3f);
        binnenvaartkraan6.setLocalTranslation(4, 15, 3f);
        binnenvaartkraan7.setLocalTranslation(-2, 16, 3f);

        attachChild(binnenvaartkraan1);
        attachChild(binnenvaartkraan2);
        attachChild(binnenvaartkraan3);
        attachChild(binnenvaartkraan4);
        attachChild(binnenvaartkraan5);
        attachChild(binnenvaartkraan6);
        attachChild(binnenvaartkraan7);
    }

    public boolean getContainer(Container container, InlandShip ship,float tpf, int location) {
        switch (getContainerInt) {
            case 0:
                if ((int) this.getLocalTranslation().x < location) {
                    this.move(tpf * 8 / 3f, 0, 0);
                } else if ((int) this.getLocalTranslation().x > location) {
                    this.move(-tpf * 8 / 3f, 0, 0);
                }
                if ((int) this.getLocalTranslation().x == location) {
                    getContainerInt++;
                }
                break;
            case 1:
                if (timer.counter(240, tpf)) {
                    getContainerInt++;
                    this.attachChild(container);
                    container.setLocalTranslation(7, 13.5f, 3);
                    container.rotate(0, FastMath.PI / 2, 0);
                }
                break;
            case 2:
                if ((int) container.getLocalTranslation().x > -10f) {
                    container.move(-5f / 3f * tpf, 0, 0);

                } else if (timer.counter(240, tpf)) {
                    getContainerInt++;
                    this.detachChild(container);
                    ship.attachContainer(container);                    
                    return true;
                }
                return false;
        }
        return false;
    }
}
