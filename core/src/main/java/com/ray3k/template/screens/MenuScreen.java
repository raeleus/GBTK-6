package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.JamGame.*;
import static com.ray3k.template.Resources.*;

public class MenuScreen extends JamScreen {
    private Stage stage;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    public static int counter;
    
    @Override
    public void show() {
        super.show();
        
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
    
        sceneBuilder.build(stage, skin, Gdx.files.internal("menus/main.json"));
    
        var changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                bgm_war.stop();
                
                switch (counter) {
                    case 0:
                        core.transition(new WiseScreen());
                        break;
                    case 1:
                        core.transition(new NvidiaScreen());
                        break;
                    case 2:
                        core.transition(new WindowsUpdateScreen());
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
    
        textButton = stage.getRoot().findActor("play2");
        textButton.addListener(sndChangeListener);
        textButton.addListener(changeListener);
    
        textButton = stage.getRoot().findActor("play3");
        textButton.addListener(sndChangeListener);
        textButton.addListener(changeListener);
        
        bgm_war.setLooping(true);
        bgm_war.play();
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
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
