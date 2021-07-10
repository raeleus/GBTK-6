package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineAsteroidsBullet.*;

public class AsteroidsBulletEntity extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, bulletCollisionFilter);
        setMotion(400, 180);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (isOutside(0,  0,  1024, 576, 100)) {
            destroy = true;
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
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
            if (collision.other.userData instanceof AsteroidsAsteroidEntity) {
                sfx_explosion.play();
                destroy = true;
                var asteroid = (AsteroidsAsteroidEntity) collision.other.userData;
                asteroid.destroy = true;
                
                var animation = asteroid.animationState.getCurrent(0).getAnimation();
                if (animation != SpineAsteroidsAsteroid.animationSmall) asteroid.spawnAsteroids(animation == SpineAsteroidsAsteroid.animationLarge ? SpineAsteroidsAsteroid.animationMed : SpineAsteroidsAsteroid.animationSmall);
                GameAsteroidsScreen.game.progress += 2;
            }
        }
    }
    
    public static class BulletCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof AsteroidsAsteroidEntity) return Response.cross;
            return null;
        }
    }
    
    public final static BulletCollisionFilter bulletCollisionFilter = new BulletCollisionFilter();
}
