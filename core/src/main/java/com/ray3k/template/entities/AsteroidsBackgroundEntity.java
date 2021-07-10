package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Resources.SpineSandBackground.*;

public class AsteroidsBackgroundEntity extends Entity {
    public static AsteroidsBackgroundEntity asteroidsBackgroundEntity;
    
    @Override
    public void create() {
        asteroidsBackgroundEntity = this;
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationAsteroids, false);
        depth = 100;
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
