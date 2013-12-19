/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import java.util.Random;

/**
 *
 * @author User
 */
public class Truck extends Node {

    static int numberOfTrucks;
    Random rand = new Random();
    public static ParticleEmitter fireEffect;

    public Truck(AssetManager assetManager) {
        super("Truck " + numberOfTrucks++ + " ");

        Box boxshape1 = new Box(Vector3f.ZERO, 20 / 6f, 1.25f / 6f, 0.5f);
        Box boxshape2 = new Box(Vector3f.ZERO, 5 / 6f, 0.5f, 0.5f);
        Cylinder cylinder = new Cylinder(32, 32, .05f, 1, true);

        Geometry bottom = new Geometry("the bottom of the truck", boxshape1);
        Geometry cabin = new Geometry("the cabin of the truck", boxshape2);
        Geometry exhaust = new Geometry("the exhaust of the truck", cylinder);


        Material mat_tex = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex.setColor("Color", ColorRGBA.Cyan);
        Material mat_tex2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_tex2.setColor("Color", ColorRGBA.Gray);

        bottom.setMaterial(mat_tex);
        cabin.setMaterial(mat_tex);
        exhaust.setMaterial(mat_tex2);

        attachChild(bottom);
        attachChild(cabin);
        attachChild(exhaust);

        bottom.setLocalTranslation(0, 0, 0);
        cabin.setLocalTranslation(15f / 6, 2.5f / 6, 0);
        exhaust.setLocalTranslation(1.6f, 0.5f, 2.5f / 6);
        exhaust.rotate(FastMath.PI / 2, 0, 0);

        /**
         * Uses Texture from jme3-test-data library!
         */
        fireEffect = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 20);
        Material fireMat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        fireMat.setTexture("Texture", assetManager.loadTexture("Effects/Smoke/Smoke.png"));
        fireEffect.setMaterial(fireMat);
        fireEffect.setImagesX(15);
        fireEffect.setImagesY(1); // 2x2 texture animation
        fireEffect.setEndColor(ColorRGBA.White);   // red
        fireEffect.setStartColor(ColorRGBA.White); // yellow
        fireEffect.getParticleInfluencer().setInitialVelocity(new Vector3f(0, .7f, 0));
        fireEffect.setStartSize(0.1f);
        fireEffect.setEndSize(0.4f);
        fireEffect.setGravity(0f, 0f, 0f);
        fireEffect.setLowLife(0.5f);
        fireEffect.setHighLife(1f);
        fireEffect.setFacingVelocity(true);
        fireEffect.getParticleInfluencer().setVelocityVariation(0.3f);
        fireEffect.setLocalTranslation(1.6f, 5.5f / 6, 2.5f / 6);
        



    }

    public boolean departure(float tpf) {
        this.attachChild(fireEffect);
        if (this.getLocalTranslation().z < 30 / 3) {
            this.move(0, 0, 5 * tpf);
            return false;
        } else {
            this.setLocalTranslation(this.getLocalTranslation().x, 1.25f / 6, -3.5f);
            Main.random = rand.nextInt(20);
            this.detachChild(fireEffect);
            return true;
            
        }
    }
}
