package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.utils.SkeletonDrawable;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineWindowsUpdate.*;

public class WindowsUpdateScreen extends JamScreen {
    private Stage stage;
    private SpineDrawable spineDrawable;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private ObjectSet<Sound> sounds;
    private float percent;
    private Label label;
    private boolean complete;
    
    @Override
    public void show() {
        super.show();

        sounds = new ObjectSet<>();
        
        Skeleton skeleton = new Skeleton(skeletonData);
        AnimationState animationState = new AnimationState(animationData);
        spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.getAnimationState().setAnimation(0, animationStand, false);
        spineDrawable.getAnimationState().apply(spineDrawable.getSkeleton());
        spineDrawable.setCrop(-30,-30, 60, 60);
        
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
    
        Image image = new Image(spineDrawable);
        image.setScaling(Scaling.none);
        root.add(image);
        spineDrawable.getAnimationState().setAnimation(0, animationStart, false);
        spineDrawable.getAnimationState().addAnimation(0, animationAnimation, true, 0);
    
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {
                if (entry.getAnimation() == animationEnd) {
                    core.transition(new WindowsHiScreen());
                }
            }
    
            @Override
            public void event(AnimationState.TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null && !event.getData().getAudioPath().equals("")) {
                    Sound sound = assetManager.get("sfx/" + event.getData().getAudioPath());
                    sound.play(sfx);
                    sounds.add(sound);
                }
            }
        });
        
        root.row();
        label = new Label("", skin);
        root.add(label);
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        
        spineDrawable.update(delta);
        
        if (!complete) {
            percent += delta * 1.5f;
            if (percent > 120) {
                percent = 120;
                complete = true;
                spineDrawable.getAnimationState().addAnimation(0, animationEnd, false, 0);
            }
            float display = percent < 100 ? percent : 99;
            
            label.setText("Working on updates " + MathUtils.floor(display) + "%");
        }
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 92/256f, 159/256f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void hide() {
        super.hide();
        for (Sound sound : sounds) {
            sound.stop();
        }
    }
}