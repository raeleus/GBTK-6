package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.JamGame.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineMenuLoading.*;

public class MenuScreen extends JamScreen {
    private Stage stage;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    public static int counter;
    private SpineDrawable spineDrawable;
    
    @Override
    public void show() {
        super.show();
    
        Skeleton skeleton = new Skeleton(skeletonData);
        AnimationState animationState = new AnimationState(animationData);
        spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.getAnimationState().setAnimation(0, animationAnimation, true);
        
        stage = new Stage(new FillViewport(1024, 576), batch);
        Gdx.input.setInputProcessor(stage);
    
        sceneBuilder.build(stage, skin, Gdx.files.internal("menus/main.json"));
    
        var changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (counter) {
                    case 0:
                        var dialog = new Dialog("MISSING INSTALLER FILES", skin) {
                            @Override
                            protected void result(Object object) {
                                Gdx.input.setInputProcessor(null);
                                bgm_war.stop();
                                core.transition(new GameDragonScreen());
                            }
                        };
                        
                        var label = new Label("Game is not installed. Download game files?", skin, "wise");
                        label.setWrap(true);
                        label.setAlignment(Align.center);
                        dialog.getContentTable().add(label).growX();
                        
                        dialog.getButtonTable().padBottom(10).align(Align.right);
                        var button = new TextButton("OK", skin, "dialog");
                        dialog.button(button, true);
    
                        button = new TextButton("Cancel", skin, "dialog");
                        button.setDisabled(true);
                        dialog.button(button, true);
                        
                        dialog.show(stage);
                        break;
                    case 1:
                        var dialog2 = new Dialog("MISSING VIDEO DRIVER", skin) {
                            @Override
                            protected void result(Object object) {
                                Gdx.input.setInputProcessor(null);
                                bgm_war.stop();
                                core.transition(new GameDrugScreen());
                            }
                        };
    
                        label = new Label("Video driver is not installed. Download the latest Game Ready Driver from Invidiar?", skin, "wise");
                        label.setWrap(true);
                        label.setAlignment(Align.center);
                        dialog2.getContentTable().add(label).growX();
    
                        dialog2.getButtonTable().padBottom(10).align(Align.right);
                        button = new TextButton("OK", skin, "dialog");
                        dialog2.button(button, true);
    
                        button = new TextButton("Cancel", skin, "dialog");
                        button.setDisabled(true);
                        dialog2.button(button, true);
    
                        dialog2.show(stage);
                        break;
                    case 2:
                        var dialog3 = new Dialog("WINDOWS UPDATE IS PENDING", skin) {
                            @Override
                            protected void result(Object object) {
                                Gdx.input.setInputProcessor(null);
                                bgm_war.stop();
                                core.transition(new GameDonutScreen());
                            }
                        };
    
                        label = new Label("You have a pending Windows Update. Game installation cannot continue until" +
                                "this is resolved. We usually just abruptly restart your computer, but I guess it's" +
                                "better to ask for permission.", skin, "wise");
                        label.setWrap(true);
                        label.setAlignment(Align.center);
                        dialog3.getContentTable().add(label).growX();
    
                        dialog3.getButtonTable().padBottom(10).align(Align.right);
                        button = new TextButton("OK", skin, "dialog");
                        dialog3.button(button, true);
    
                        button = new TextButton("Cancel", skin, "dialog");
                        button.setDisabled(true);
                        dialog3.button(button, true);
    
                        dialog3.show(stage);
                        break;
                }
                
                counter++;
            }
        };
    
        var videoDrawable = new VideoDrawable(Gdx.files.internal("video/menu.webm"));
        videoDrawable.setMinSize(480, 270);
        videoDrawable.videoPlayer.setOnCompletionListener(file -> {
            try {
                videoDrawable.videoPlayer.play(Gdx.files.internal("video/menu.webm"));
            } catch (Exception e) {}
        });
        Image image = stage.getRoot().findActor("logo");
        image.setDrawable(videoDrawable);
    
        TextButton textButton = stage.getRoot().findActor("play1");
        textButton.addListener(sndChangeListener);
        textButton.addListener(changeListener);
        if (counter == 0) {
            textButton.setTouchable(Touchable.disabled);
            textButton.setColor(1, 1, 1, 0);
            textButton.addAction(Actions.sequence(
                    Actions.delay(46),
                    Actions.fadeIn(2f),
                    Actions.touchable(Touchable.enabled)
            ));
        }
    
        textButton = stage.getRoot().findActor("play2");
        textButton.addListener(sndChangeListener);
        textButton.addListener(changeListener);
        if (counter == 0) {
            textButton.setTouchable(Touchable.disabled);
            textButton.setColor(1, 1, 1, 0);
            textButton.addAction(Actions.sequence(
                    Actions.delay(47),
                    Actions.fadeIn(2f),
                    Actions.touchable(Touchable.enabled)
            ));
        }
    
        textButton = stage.getRoot().findActor("play3");
        textButton.addListener(sndChangeListener);
        textButton.addListener(changeListener);
        if (counter == 0) {
            textButton.setTouchable(Touchable.disabled);
            textButton.setColor(1, 1, 1, 0);
            textButton.addAction(Actions.sequence(
                    Actions.delay(48),
                    Actions.fadeIn(2f),
                    Actions.touchable(Touchable.enabled)
            ));
        }
        
        if (counter == 0) {
            image = new Image(spineDrawable);
            image.pack();
            stage.addActor(image);
            image.setPosition(512, 100, Align.bottom);
            image.addAction(Actions.sequence(
                    Actions.delay(45),
                    Actions.run(() -> spineDrawable.getAnimationState().setAnimation(0, animationHide, false)),
                    Actions.removeActor()
            ));
        }
        
        bgm_war.setLooping(true);
        bgm_war.play();
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        spineDrawable.update(delta);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
