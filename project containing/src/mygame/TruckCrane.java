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
 * Making a truckcrane.
 * Giving it the possibility to place a container to a truck.
 */
public class TruckCrane extends Node {
    boolean heeftOpdracht = false;
    int switchcase = 0;
    static int numberOfTruckCraness;
    boolean drive = false;
    Timer timer = new Timer();
    float testing;
    int testinggggg = 0;

    /// Making of the crane
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

    /// moving forward to the idle position
    public boolean moveForward(float tpf) {
        if (this.getLocalTranslation().z > -11f) {//Drive with speed of 3 becouse crane is not loaded
            this.move(0, 0, -tpf * (20 / 3.6f / 3) ); // (20 km/h) / (3.6 = m/s) / 3 our scale
            return false;
        } else  // return true if Crane reached its destenation
            return true;
    }

    /// moving back towards the truck
    public boolean moveBack(float tpf) {
        if (this.getLocalTranslation().z < (-2.2f - 13 / 6)) {
            this.move(0, 0, tpf * (15 / 3.6f / 3)); // (15 km/h) / (3.6 = m/s) / 3 our scale 
            return false;
        } else { // return true if Crane reached its destenation
            return true;
        }
    }

    /// Pick up a container from agv and place it on the truck and let the truck departure
    public boolean loadContainer(Container container, Truck truck, float tpf) {
        
        switch (switchcase) {
            case 0: 
                if(timer.counter(30, tpf)) // after 30 seconds the container is attached to the crane
                     switchcase++;         //next case
                break;
            case 1:
                if(container.getLocalTranslation().y < 2.5f / 3 * 4) {//here the container gets lifted up
                    container.move(0, tpf * 0.055f, 0);}
                
                if(timer.counter(60, tpf)) // while the container gets lifted a timer starts when it reaches 60 the 
                {                          //container is supposed to be at the top if not it will be set there
                    this.attachChild(container);// attach the container to the crane
                    container.setLocalTranslation(0,2.5f / 3 * 4, 2.5f / 3 * 1.5f);//set the correct coordinates to the container within the crane 
                    container.rotate(0, FastMath.PI / 2, 0);// rotate the container
                    switchcase += 1;// next case
                }
                return false;
            case 2:
                if (this.moveBack(tpf)) {// move the crane with the container towards the truck
                    switchcase += 1;// next case
                }
                return false;
            case 3:
                if(container.getLocalTranslation().y > 2.5 / 3) // lower the container until it reaches the right height
                    container.move(0, -0.048f * tpf, 0);
                
                if(timer.counter(90, tpf)){ // while the container lowers a timer starts when it reaches 90 the 
                                            //container is supposed to be at the bottom if not it will be set there
                    this.detachChild(container); // detach the container
                    truck.attachChild(container); // attach to the truck
                    container.setLocalTranslation(-0.9f, 0.65f, 0);// set the container to the right location on the truck
                    switchcase += 1; // next case
                    drive = false; // set drive false (this is a helper, so the truck won't move twice)
                }
                return false;

            case 4:
                if (this.moveForward(tpf)) //if the crane is back at the start position the truck is allowed to move
                    drive = true;
                
                if( drive == true){ //if the truck is allowed to move it wil move
                    if (truck.departure(tpf)){ //the truck will leave, and wil 'spawn' back at the beginning
                        truck.detachChild(container); // remove the container from truck
                        switchcase = 0; // end the switch case
                        return true; // return true so the method will stop with repeating
                    }
                }
                return false;
        }
        return false;

    }
}
