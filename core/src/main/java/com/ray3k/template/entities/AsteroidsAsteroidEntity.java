package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineAsteroidsAsteroid.*;

public class AsteroidsAsteroidEntity extends Entity implements Enemy {
    private Animation animation;
    
    public AsteroidsAsteroidEntity(Animation animation) {
        this.animation = animation;
    }
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animation, false);
        animationState.apply(skeleton);
        skeleton.updateWorldTransform();
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, Core.nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        
        
        final var BORDER = 50;
    
        if (x < -BORDER) x = 1024 + BORDER;
        if (x > 1024 + BORDER) x = -BORDER;
    
        if (y < -BORDER) y = 576 + BORDER;
        if (y > 576 + BORDER) y = -BORDER;
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    public void spawnAsteroids(Animation animation) {
        for (int i = 0; i < 3; i++) {
            var asteroid = new AsteroidsAsteroidEntity(animation);
            entityController.add(asteroid);
            
            asteroid.setPosition(x, y);
            asteroid.teleportCollisionBox(true);
            asteroid.animationState.setAnimation(0, animation, false);
            asteroid.animationState.apply(skeleton);
            skeleton.updateWorldTransform();
            setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, Core.nullCollisionFilter);
            skeletonBounds.update(skeleton, true);
            asteroid.setMotion(getSpeed() * 1.5f, MathUtils.random(360f));
        }
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
