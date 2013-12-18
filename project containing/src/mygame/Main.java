package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
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
    Truck[] trucks = new Truck[20];
    public int[] trainSpacesFree = new int[87];
    int test = 1;
    public Train train;
    public Ship ship;

    public static void main(String[] args) {
        Main app = new Main();
        AppSettings settings = new AppSettings(true);
        app.setShowSettings(false);
        settings.setHeight(768);
        settings.setWidth(1200);
        settings.setVSync(true);
        settings.setTitle("Containing");
        app.setSettings(settings);
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

        initTrucks();
        initTrain();

        Node sceneNode = new Node("Scene");
        sceneNode.attachChild(SkyFactory.createSky(assetManager, "Textures/BrightSky.dds", false));
        rootNode.attachChild(sceneNode);


        //Spatial train = rootNode.getChild("Train");

//        for(int i = 0; i < 83; i++){
//            
//            train.attachChild(containers[i]);
//            containers[i].setLocalTranslation((13/3 * i * 1.2f) - 197, 1, 0);
//            containers[i].rotate(0,FastMath.PI / 2 ,0);
//        }

        //set the test u want to use here:
        // set test 1 for testing whole storageCrain
        // set test 3 for testing TruckCrain
        // 4 inland
        // 5 seacrane
        //test = 3;

        //truck.attachChild(containers[1]);
    }

    @Override
    public void simpleUpdate(float tpf) {
        seaCranes[0].getContainer(ship.container[2][9][0], tpf, -10);
//        switch (test) {
//            case 1: // testing the StorageCrane both methods
//                if (storageCranes[1].placeContainer(containers[0], 1, 0, 1, tpf)) {
//                    test += 1;// continue
//                }
//                break;
//            case 2: // testing the StorageCrane get container note that there has to be a container first
//                // this will be checked in the controller and not in the simulator so it can't give an error
//                if (storageCranes[1].getContainer(containers[0], 1, tpf)) {
//                    test = 0; // end the test
//                }
//                break;
//            case 3:
//                if (truckCranes[1].loadContainer(containers[0], trucks[1], tpf)) {
//                    test += 1; // end the test
//                }
//                break;
//            case 4:
////                if (inlandCranes[0].getContainer(containers[0], tpf, 30)) {
////                    test = 0;//end the test
////                }
//                if (truckCranes[1].loadContainer(containers[1], trucks[1], tpf)) {
//                    test = 0; // end the test
//                }
//                break;
//            case 5:
//                if (seaCranes[0].getContainer(containers[0], tpf, -10)) {
//                    test = 0;
//                }
//                break;
//            case 6:
//                if (trainCranes[1].setContainer(train, containers[0], tpf, 30, true)) {
//                    test = 0;
//                }
//                break;
//        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

    public void makecontainer() {
        for (int i = 0; i < 100; i++) {
            //    int i = 1;
            containers[i] = new Container(assetManager);
            rootNode.attachChild(containers[i]);
            containers[i].setLocalTranslation(100f, 2.5f / 6 + (2.5f / 3) * i, -50f);
            containers[i].rotate(0, FastMath.PI / 2, 0);
            //containers[i].setLocalTranslation(0, 2.5f/6, 0);
            //[i].setLocalTranslation(77f + 13f / 3 + 2.5f / 3 * 12, (2.5f / 3 * 14) + ((13/3) * i), -50f);
        }
    }

    public void initFloor() {
        Box box = new Box(Vector3f.ZERO, 100f, 0.02f, 250f);//13 bij 2.5
        Geometry grond = new Geometry("A Textured Box", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture text = assetManager.loadTexture("Textures/floor.png");
        mat.setTexture("ColorMap", text);
        grond.setMaterial(mat);
        rootNode.attachChild(grond);
        grond.setLocalTranslation(250f, -0.020f, -100f);
        grond.rotate(0, -FastMath.PI / 2, 0);
    }

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
            trainCranes[i].setLocalTranslation(90f + (125f * i), 0, -199f);
        }
    }

    public void initTruckCrane() {
        for (int i = 0; i < 20; i++) {
            truckCranes[i] = new TruckCrane(assetManager);
            rootNode.attachChild(truckCranes[i]);
            truckCranes[i].setLocalTranslation(400f + (4.5f * i), 0, -11f);
            truckCranes[i].rotate(0, FastMath.PI / 2, 0);
        }
    }

    public void initTrain() {
        train = new Train(assetManager);
        rootNode.attachChild(train);

        ship = new Ship(1000, assetManager);
        ship.setLocalTranslation(-10, -1, -10);
        rootNode.attachChild(ship);
    }

    public void initTrucks() {
        for (int i = 0; i < 20; i++) {
            trucks[i] = new Truck(assetManager);
            rootNode.attachChild(trucks[i]);
            trucks[i].setLocalTranslation(401.2f + (4.5f * i), 1.25f / 6, -3.5f);
            trucks[i].rotate(0, -FastMath.PI / 2, 0);
        }
    }
}
