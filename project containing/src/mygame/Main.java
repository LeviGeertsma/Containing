package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    StorageCrane[] storageCranes = new StorageCrane[45];
    public Container[] containers = new Container[100];
    SeaCrane[] seaCranes = new SeaCrane[4];
    InlandCrane[] inlandCranes = new InlandCrane[3];
    TrainCrane[] trainCranes = new TrainCrane[4];
    TruckCrane[] truckCranes = new TruckCrane[20];
    int test = 1;
     Train train;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(100f);
        initFloor();
        makecontainer();

        //kranen initialiseren en plaatsen
        initInlandCrane();
        initStorageCrane();
        initSeaCrane();
        initTrainCrane();
        initTruckCrane();


        Node sceneNode = new Node("Scene");
        sceneNode.attachChild(SkyFactory.createSky(assetManager, "Textures/BrightSky.dds", false));
        rootNode.attachChild(sceneNode);
        
        train = new Train(assetManager);
        rootNode.attachChild(train);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //train.Departure(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

    public void makecontainer() {
        for (int i = 0; i < 30; i++) {
            //    int i = 1;
            containers[i] = new Container(assetManager);
            rootNode.attachChild(containers[i]);
            containers[i].setLocalTranslation(100f, 2.5f / 6 + (2.5f / 3) * i, -50f);
            containers[i].rotate(0, FastMath.PI / 2, 0);

            //[i].setLocalTranslation(77f + 13f / 3 + 2.5f / 3 * 12, (2.5f / 3 * 14) + ((13/3) * i), -50f);
        }
    }

    public void initFloor() {
        Box box = new Box(Vector3f.ZERO, 250f, 0.02f, 100f);//13 bij 2.5

        Geometry grond = new Geometry("A Textured Box", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture text = assetManager.loadTexture("Textures/gras.jpg");
        mat.setTexture("ColorMap", text);

        grond.setMaterial(mat);
        rootNode.attachChild(grond);
        grond.setLocalTranslation(250f, -0.020f, -100f);
    }
    // 13 lang 2.5 breed en 2.5 hoog

    //1500 500 250 3
    public void initStorageCrane() {

        for (int i = 0; i < 45; i++) {

            storageCranes[i] = new StorageCrane(assetManager);
            rootNode.attachChild(storageCranes[i]);
            storageCranes[i].setLocalTranslation(60f + (9.3f * i), 0, -50f);
            storageCranes[i].rotate(0, FastMath.PI / 2, 0);
        }

    }

    public void initSeaCrane() {

        for (int i = 0; i < 4; i++) {
            seaCranes[i] = new SeaCrane(assetManager);
            rootNode.attachChild(seaCranes[i]);
            seaCranes[i].setLocalTranslation(1f, .5f, -(10f + (63f * i)));
        }
    }

    public void initInlandCrane() {
        for (int i = 0; i < 3; i++) {

            inlandCranes[i] = new InlandCrane(assetManager);
            rootNode.attachChild(inlandCranes[i]);
            inlandCranes[i].setLocalTranslation(25f + (80f * i), 0.5f, -1f);
            inlandCranes[i].rotate(0, FastMath.PI / 2, 0);
        }

    }

    public void initTrainCrane() {
        for (int i = 0; i < 4; i++) {

            trainCranes[i] = new TrainCrane(assetManager);
            rootNode.attachChild(trainCranes[i]);
            trainCranes[i].setLocalTranslation(100f + (125f * i), 0, -199f);
        }
    }

    public void initTruckCrane() {
        for (int i = 0; i < 20; i++) {

            truckCranes[i] = new TruckCrane(assetManager);
            rootNode.attachChild(truckCranes[i]);
            truckCranes[i].setLocalTranslation(400f + (4.5f * i), 0, -1f);
            truckCranes[i].rotate(0, FastMath.PI / 2, 0);
        }
    }
}
