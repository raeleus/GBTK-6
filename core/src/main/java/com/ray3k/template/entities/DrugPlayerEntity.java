package com.ray3k.template.entities;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Resources.SpineDrugPlayer.*;
import static com.ray3k.template.screens.GameDrugScreen.*;

public class DrugPlayerEntity extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, playerCollisionFilter);
        animationState.setAnimation(0, animationAnimation, true);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (game.isButtonPressed(Buttons.LEFT)) {
            moveTowards(800, game.mouseX, game.mouseY, delta);
        } else {
            setSpeed(0);
        }
    
        if (!DrugBackgroundEntity.drugBackgroundEntity.skeletonBounds.aabbContainsPoint(x, y)) {
            System.out.println("not containing");
            game.progress += 1 * delta;
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
            if (collision.other.userData instanceof DrugEntity) {
                var drug = (DrugEntity) collision.other.userData;
                drug.destroy = true;
                game.progress += 7;
            }
        }
    }
    
    public static class PlayerCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof DrugEntity) return Response.cross;
            if (other.userData instanceof WallEntity) return Response.slide;
            else return null;
        }
    }
    
    public static PlayerCollisionFilter playerCollisionFilter = new PlayerCollisionFilter();
}
