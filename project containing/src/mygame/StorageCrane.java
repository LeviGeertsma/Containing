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
 *
 * @author User
 */
public class StorageCrane extends Node {

    static int numberStorageCranes;
    int switchcase = 0;
    Timer timer = new Timer();

    public StorageCrane(AssetManager assetManager) {
        super("Opslagkraan " + numberStorageCranes++ + " ");

        Box boxshape1 = new Box(Vector3f.ZERO, 1f, 4f, 0.5f);
        Box boxshape2 = new Box(Vector3f.ZERO, 1f, 0.5f, 4f);

        Geometry side1 = new Geometry("A Textured Box", boxshape1);
        Geometry topside = new Geometry("A Textured Box", boxshape2);
        Geometry side2 = new Geometry("A Textured Box", boxshape1);

        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex.setColor("Color", ColorRGBA.Blue);

        side1.setMaterial(mat_tex);
        side2.setMaterial(mat_tex);
        topside.setMaterial(mat_tex);

        attachChild(side1);
        attachChild(side2);
        attachChild(topside);

        side1.setLocalTranslation(0, 4f, 0);
        side2.setLocalTranslation(0, 4f, 8f);
        topside.setLocalTranslation(0, 8, 4);
    }

    public boolean moveForward(boolean isloaded, float tpf, int rij) {


        if (isloaded == false && this.getLocalTranslation().z > (-50f - (13.5f / 3 * (rij + 1)))) {//rij heen met snelheid 3 omdat hij niet geladen is
            this.move(0, 0, -tpf * 3);
            return false;

        } else if (this.getLocalTranslation().z > (-50f - (13.5f / 3 * (rij + 1)))) {//rij heen met snelheid 2 omdat hij wel geladen is
            this.move(0, 0, -tpf * 2);
            return false;

        } else { // return true als hij zijn bestemming heeft berijkt
            return true;

        }
    }

    public boolean moveBack(boolean isloaded, float tpf) {
        // vector van de huidige locatie van de kraan

        if (this.getLocalTranslation().z < -50 && isloaded == false) {//terugrijden met snelheid 3 als ongeladen is
            this.move(0, 0, tpf * 3);
            return false;

        } else if (this.getLocalTranslation().z < -50 && isloaded == true) {//terugrijden met snelheid 2 als geladen is
            this.move(0, 0, tpf * 2);
            return false;

        } else {
            return true;// hij is weer bij het begin punt (bestemming berijkt)
        }
    }

    public boolean placeContainer(Container container, int rij, int hoogte, int kolom, float tpf) {

        String numberStorageCranesCounter = ""; // string leeg maken

        switch (switchcase) {
            case 0:
                this.attachChild(container);// container vast maken aan de kraan zodat hij meerijdt
                container.setLocalTranslation(0, 2.5f / 3 * 8, 2.5f / 3 * 5);//goed neerzetten 
                
                switchcase += 1;// ga naar volgende case
                break;

            case 1: // deze case herhalen todat hij eindbestemming berijkt heeft
                if (this.moveForward(true, tpf, rij)) {
                    switchcase += 1;
                }
                return false;

            case 2:
                this.detachChild(container);
                super.parent.attachChild(container);
                container.rotate(0, FastMath.PI / 2, 0);

                if (super.name.charAt(13) != ' ') {
                    // hier zet je de 2 mogelijke nummers van de huidige kraan neer
                    numberStorageCranesCounter += super.name.charAt(12) + super.name.charAt(13);
                } else {
                    numberStorageCranesCounter += super.name.charAt(12);
                }

                container.setLocalTranslation(62f + (9.3f
                        * Integer.parseInt(numberStorageCranesCounter)) + 2.5f / 3 * kolom, // de x
                        2.5f / 6 + 2.5f / 3f * hoogte, // de y = de hoogte
                        (-50f - 13.5f / 3 * (rij + 1))); // de z = de diepte

                switchcase += 1;
                return false;

            case 3:
                if (this.moveBack(false, tpf)) {
                    switchcase = 0;
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean getContainer(Container container, int rij, float tpf) {
        String numberStorageCranesCounter = ""; // string leeg maken

        switch (switchcase) {
            case 0:
                if (this.moveForward(false, tpf, rij)) {
                    switchcase += 1;
                }
                return false;
            case 1:
                this.attachChild(container);// container vast maken aan de kraan zodat hij meerijdt
                container.setLocalTranslation(0, 2.5f / 3 * 8, 2.5f / 3 * 5);//goed neerzetten 
                container.rotate(0, FastMath.PI / 2, 0);// een kwart slag draaien
                switchcase += 1;// ga naar volgende case
                break;
            case 2:
                if (this.moveBack(false, tpf)) {
                    switchcase += 1;
                }
                return false;
            case 3:
                this.detachChild(container);
                super.parent.attachChild(container);
                container.rotate(0, FastMath.PI / 2, 0);

                if (super.name.charAt(13) != ' ') {
                    // hier zet je de 2 mogelijke nummers van de huidige kraan neer
                    numberStorageCranesCounter += super.name.charAt(12) + super.name.charAt(13);
                } else {
                    numberStorageCranesCounter += super.name.charAt(12);
                }

                container.setLocalTranslation(62f + (9.3f
                        * Integer.parseInt(numberStorageCranesCounter)) + 2.5f / 3 * 3, // de x
                        2.5f / 6, // de y = de hoogte
                        (-50)); // de z = de diepte
                switchcase = 0;
                return true;
                
        }
        return false;

    }
}
