package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;
import com.ray3k.template.entities.*;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameDrugScreen extends JamScreen {
    public static GameDrugScreen game;
    public Stage stage;
    public static final Color BG_COLOR = new Color(Color.TEAL);
    private ProgressBar progressBar;
    public float progress;
    
    @Override
    public void show() {
        super.show();
        
        bgm_minigame4.setLooping(true);
        bgm_minigame4.play();
        
        game = this;
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("white"));
        shapeDrawer.setPixelSize(.5f);
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        stage = new Stage(new FitViewport(1024, 576), batch);
        
        var root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        var label = new Label("CLICK TO GET THE DRUGS!", skin, "minigame-title");
        label.setWrap(true);
        label.setAlignment(Align.center);
        root.add(label).growX().expandY().top();
        
        root.row();
        var stack = new Stack();
        root.add(stack).size(579, 78);
        
        progressBar = new ProgressBar(0, 100, 1, false, skin, "ads");
        stack.add(progressBar);
        
        label = new Label("Downloading dRIVER Files", skin);
        stack.add(label);
        
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
        
        var map = new DrugBackgroundEntity();
        map.setPosition(100, 100);
        entityController.add(map);
        map.depth = 10;
        
        camera.position.set(512, 288, 0);
    }
    
    @Override
    public void act(float delta) {
        entityController.act(delta);
        
        progressBar.setValue(progress);
        if (progress >= 100) {
            core.transition(new CinematicScreen(skin.getDrawable("drug-cinematic1"),
                    new CinematicScreen(skin.getDrawable("drug-cinematic2"),
                            new NvidiaScreen())));
            bgm_minigame4.stop();
        }
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(delta);
        batch.end();
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            viewport.update(width, height);
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {

    }
    
    @Override
    public void hide() {
        super.hide();
        entityController.dispose();
    }
}
