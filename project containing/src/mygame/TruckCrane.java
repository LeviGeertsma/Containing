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
 * http://www.playstation4club.nl/ps4-nieuws/ps4-launch-bij-duitse-media-markt-een-grote-chaos-video/
 * leuk filmpie
 * 
 * @author Niels :D
 *
 * kraan naar de container rijden container oppakken container opvrachtwagen
 * zetten terugrijden
 */
public class TruckCrane extends Node {

    int switchcase = 0;
    static int numberOfTruckCraness;

    public TruckCrane(AssetManager assetManager) {
        super("TruckCrane " + numberOfTruckCraness++ + " ");

        Box boxshape1 = new Box(Vector3f.ZERO, 1f, 2f, 0.25f);
        Box boxshape2 = new Box(Vector3f.ZERO, 1f, 0.25f, 1.5f);

        Geometry side1 = new Geometry("A Textured Box", boxshape1);
        Geometry topSide = new Geometry("A Textured Box", boxshape2);
        Geometry side2 = new Geometry("A Textured Box", boxshape1);

        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex.setColor("Color", ColorRGBA.Red);

        side1.setMaterial(mat_tex);
        side2.setMaterial(mat_tex);
        topSide.setMaterial(mat_tex);

        attachChild(side1);
        attachChild(side2);
        attachChild(topSide);

        side1.setLocalTranslation(0, 2f, 0);
        side2.setLocalTranslation(0, 2f, 2.5f);
        topSide.setLocalTranslation(0, 4, 1.25f);
    }

    public boolean moveForward(float tpf) {
        if (this.getLocalTranslation().z > -10f) {//rij heen met snelheid 3 omdat hij niet geladen is
            this.move(0, 0, -tpf * 3);
            return false;

        } else { // return true als hij zijn bestemming heeft berijkt
            return true;

        }
    }

    public boolean moveBack(float tpf, boolean drivingTowardsTruck) {
        if (this.getLocalTranslation().z < -1f && drivingTowardsTruck == false) {//rij heen met snelheid 3 omdat hij niet geladen is
            this.move(0, 0, tpf * 3);
            return false;
        } else if (drivingTowardsTruck == true && this.getLocalTranslation().z < (-1 - 13 / 6)) {
            this.move(0, 0, tpf * 2);
            return false;
        } else { // return true als hij zijn bestemming heeft berijkt
            return true;
        }

    }

    public boolean loadContainer(Container container, float tpf) {
        String numberOfTruckCranes = ""; // string leeg maken

        switch (switchcase) {
            case 0: // repeat this case until it has reached it's final desteny
                if (this.moveForward(tpf)) {
                    switchcase += 1;
                }
                return false;
            case 1: // attach the container to crane
                this.attachChild(container);// attach the container to the crane
                container.setLocalTranslation(0, 2.5f / 3 * 4, 2.5f / 3 * 1.5f);//set the correct coordinates to the container within the crane 
                //container.rotate(0, FastMath.PI / 2, 0);// rotate the container
                switchcase += 1;// next case
                break;
            case 2:// move to Truck
                if (this.moveBack(tpf, true)) {//move towards truck
                    switchcase += 1;
                    
                }
                return false;
            case 3:// put the container to rootNode
                this.detachChild(container);
                super.parent.attachChild(container); // attach to rootNode
                container.rotate(0, FastMath.PI / 2, 0);
               // System.out.println(super.name + " 1e ");
                if (super.name.charAt(12) != ' ') {
                    // hier zet je de 2 mogelijke nummers van de huidige kraan neer
                    numberOfTruckCranes += super.name.charAt(11) + super.name.charAt(12);
                } else {
                    numberOfTruckCranes += super.name.charAt(11);
                }
                container.setLocalTranslation(401.25f + (4.5f
                        * Integer.parseInt(numberOfTruckCranes)), // de x
                        2.5f / 6, // de y = de hoogte = op de grond
                        (-13/6-1)); // de z = de diepte

                switchcase += 1;
                return false;
            case 4:
                System.out.println("123test");
                  if (this.moveBack(tpf, false)) {//move towards truck
                    switchcase = 0;
                    return true;
                }
                
            // return true;
        }
        return false;

    }
}
