package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.SpineDrugDrug.*;

public class DrugEntity extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, Core.nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
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
