package com.ray3k.template.entities;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;

import static com.ray3k.template.Resources.SpineDonutPackage.*;
import static com.ray3k.template.screens.GameDonutScreen.*;

public class DonutPackageEntity extends Entity {
    int state;
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, Core.nullCollisionFilter);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (game.isButtonJustPressed(Buttons.LEFT) && skeletonBounds.containsPoint(game.mouseX, game.mouseY) != null) {
            DonutDonutEntity.donut.animationState.setAnimation(0, SpineDonutDonut.animationAnger6, false);
            DonutDonutEntity.donut.state++;
            DonutDonutEntity.donut.setPosition(512, 288);
            DonutDonutEntity.donut.setSpeed(0);
            game.progress = 99;
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
    
    }
}
