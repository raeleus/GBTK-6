package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.SkeletonBounds;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;
import com.ray3k.template.*;

import java.lang.reflect.Array;

import static com.ray3k.template.Resources.SpineDrugMap.*;
import static com.ray3k.template.screens.GameDrugScreen.*;

public class DrugBackgroundEntity extends Entity {
    private float[] verts = null;
    public static DrugBackgroundEntity drugBackgroundEntity;
    
    @Override
    public void create() {
        drugBackgroundEntity = this;
        setSkeletonData(skeletonData, animationData);
        var bones = skeleton.getRootBone().getChildren();
        for (var bone : bones) {
            if (bone.getData().getName().equals("player")) {
                var player = new PlayerEntity();
                Core.entityController.add(player);
                player.setPosition(bone.getWorldX(), bone.getWorldY());
                Core.world.move(player.item, player.x, player.y, Core.nullCollisionFilter);
            } else {
                var drug = new DrugEntity();
                Core.entityController.add(drug);
                drug.setPosition(bone.getWorldX(), bone.getWorldY());
            }
        }
    
        for (var slot : skeleton.getSlots()) {
            var attachment =  slot.getAttachment();
            if (attachment instanceof BoundingBoxAttachment) {
                var bbox = (BoundingBoxAttachment) attachment;
    
                float minX = Float.MAX_VALUE;
                float minY = Float.MAX_VALUE;
                float maxX = 0;
                float maxY = 0;
                
                if (verts == null || verts.length < bbox.getWorldVerticesLength()) verts = new float[bbox.getWorldVerticesLength()];
                bbox.computeWorldVertices(slot, 0, bbox.getWorldVerticesLength(), verts, 0, 2);
                for (int i = 0; i < bbox.getWorldVerticesLength(); i += 2) {
                    if (verts[i] < minX) minX = verts[i];
                    if (verts[i] > maxX) maxX = verts[i];
                    if (verts[i+1] < minY) minY = verts[i+1];
                    if (verts[i+1] > maxY) maxY = verts[i+1];
                }
    
                var wallEntity = new WallEntity();
                Core.entityController.add(wallEntity);
                wallEntity.setCollisionBox(minX, minY, maxX - minX, maxY - minY, Core.nullCollisionFilter);
            }
        }
        
        skeletonBounds.update(skeleton, true);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (x < -100) {
            destroy = true;
            game.progress += 2;
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
