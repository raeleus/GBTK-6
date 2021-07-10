package com.ray3k.template.entities;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Event;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.SpineDonutDonut.*;
import static com.ray3k.template.screens.GameDonutScreen.*;

public class DonutDonutEntity extends Entity {
    public static DonutDonutEntity donut;
    public int state;
    
    @Override
    public void create() {
        donut = this;
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationAnger0, true);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds, Core.nullCollisionFilter);
        
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (entry.getAnimation() == animationAnger6) {
                    game.progress += 100;
                }
            }
    
            @Override
            public void event(TrackEntry entry, Event event) {
                var path = event.getData().getAudioPath();
                if (path != null) {
                    Sound sound = Core.assetManager.get(path);
                    sound.play();
                }
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        switch (state) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                setMotion(200 * state, Utils.pointDirection(game.mouseX, game.mouseY, x, y));
                game.progress -= 2 * delta;
                break;
        }
        
        if (game.isButtonJustPressed(Buttons.LEFT) && skeletonBounds.containsPoint(game.mouseX, game.mouseY) != null) {
            Resources.sfx_mmmmm.play();
            setPosition(MathUtils.random(1024), MathUtils.random(576));
            if (state < 6) game.progress += 3;
            
            if (state < 1 && game.progress > 15) {
                state = 1;
                animationState.setAnimation(0, animationAnger1, true);
            } else if (state < 2 && game.progress > 30) {
                state = 2;
                animationState.setAnimation(0, animationAnger2, true);
            } else if (state < 3 && game.progress > 45) {
                state = 3;
                animationState.setAnimation(0, animationAnger3, true);
            } else if (state < 4 && game.progress > 60) {
                state = 4;
                animationState.setAnimation(0, animationAnger4, true);
            } else if (state < 5 && game.progress > 75) {
                state = 5;
                animationState.setAnimation(0, animationAnger5, true);
            } else if (state < 6 && game.progress > 90) {
                state = 6;
                var pack = new DonutPackageEntity();
                Core.entityController.add(pack);
                pack.setPosition(400, 250);
            }
        }
        
        final float border = 100;
        if (x < border) x = border;
        else if (x > 1024 - border) x = 1024 - border;
    
        if (y < border) y = border;
        else if (y > 576 - border) y = 576 - border;
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
