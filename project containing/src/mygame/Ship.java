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
 * @author Levi
 */
public class Ship extends Node {

    public Container[][][] container = new Container[100][10][10];

    public Ship(int totalContainers, AssetManager assetManager) {
        int nrContainers = 0;
        for (int z = 0; z < 100; z++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (totalContainers > nrContainers) {
                        container[x][y][z] = new Container(assetManager);//, totalContainers in cs
                        container[x][y][z].setLocalTranslation(2.5f * x / 3, 2.5f * y / 3, 13.1f * z / 3);
                        container[x][y][z].rotate(0, FastMath.PI / 2, 0);
                        attachChild(container[x][y][z]);
                        nrContainers++;
                    } else {
                    }
                }
            }
        }/* A colored lit cube. Needs light source! */
        Box boxMesh = new Box(5f, .4f, 30f);
        Geometry boxGeo = new Geometry("Colored Box", boxMesh);
        Material boxMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        boxMat.setColor("Color", ColorRGBA.Blue);
        boxGeo.setMaterial(boxMat);
        boxGeo.setLocalTranslation(3.75f, -.6f, 15);
        attachChild(boxGeo);
    }
}
