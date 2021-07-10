package com.ray3k.template.entities;

import com.badlogic.gdx.Input.Buttons;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineAsteroidsPlayer.*;
import static com.ray3k.template.screens.GameAsteroidsScreen.*;

public class AsteroidsPlayerEntity extends Entity {
    private float rotation;
    private float bulletTimer;
    public static AsteroidsPlayerEntity player;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, playerCollisionFilter);
        player = this;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        bulletTimer -= delta;
        if (game.isButtonPressed(Buttons.LEFT)) {
            rotation = Utils.approach360(rotation, Utils.pointDirection(x, y, game.mouseX, game.mouseY), 400f * delta);
            addMotion(300f * delta, rotation);
            skeleton.getRootBone().setRotation(rotation);
    
            if (bulletTimer < 0) {
                sfx_boom.play();
                var bullet = new AsteroidsBulletEntity();
                Core.entityController.add(bullet);
                bullet.setMotion(500f, rotation);
                bullet.deltaX += deltaX;
                bullet.deltaY += deltaY;
                bullet.skeleton.getRootBone().setRotation(rotation);
                bullet.setPosition(x, y);
                bullet.teleportCollisionBox(true);
                bulletTimer = .2f;
                bullet.depth = 10;
            }
        }
        
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
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            sfx_explosion.play();
            var collision = collisions.get(i);
            var asteroid = (AsteroidsAsteroidEntity) collision.other.userData;
            asteroid.destroy = true;
            destroy = true;
            game.progress -= 10f;
            if (game.progress < 0) game.progress = 0;
            GameAsteroidsScreen.game.respawnCounter = 2f;
        }
    }
    
    public static class PlayerCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof AsteroidsAsteroidEntity) return Response.cross;
            else return null;
        }
    }
    
    public static PlayerCollisionFilter playerCollisionFilter = new PlayerCollisionFilter();
}
