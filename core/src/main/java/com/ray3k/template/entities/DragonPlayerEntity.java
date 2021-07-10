package com.ray3k.template.entities;

import com.badlogic.gdx.Input.Buttons;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineDragon.*;
import static com.ray3k.template.screens.GameDragonScreen.*;

public class DragonPlayerEntity extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, dragonCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (game.isButtonJustPressed(Buttons.LEFT)) {
            Resources.sfx_woof.play();
            setMotion(800, 90);
            setGravity(2000, 270);
            animationState.setAnimation(0, animationBlaze, false);
            animationState.addAnimation(0, animationStand, false, .5f);
        }
        
        if (y < -175) {
            y = -175;
            deltaY = 0;
            setGravity(0, 270);
        } else if (y > 400) {
            y = 400;
            deltaY = 0;
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
            sfx_ohMyHead.play();
            var collision = collisions.get(i);
            var tree = (DragonTreeEntity) collision.other.userData;
            tree.destroy = true;
            destroy = true;
            game.respawnTimer = 2f;
            game.progress -= 25;
            if (game.progress < 0) game.progress = 0;
        }
    }
    
    public static class DragonCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof DragonTreeEntity) return Response.cross;
            else return null;
        }
    }
    
    public static DragonCollisionFilter dragonCollisionFilter = new DragonCollisionFilter();
}
