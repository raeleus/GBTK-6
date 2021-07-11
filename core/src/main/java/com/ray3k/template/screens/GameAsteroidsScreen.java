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

public class GameAsteroidsScreen extends JamScreen {
    public static GameAsteroidsScreen game;
    public Stage stage;
    public static final Color BG_COLOR = new Color(Color.BLACK);
    private ProgressBar progressBar;
    public float progress;
    public int asteroidSpawn = 3;
    public float respawnCounter = -1;
    public boolean noPoints;
    
    @Override
    public void show() {
        super.show();
        bgm_minigame6.setLooping(true);
        bgm_minigame6.play();
        
        game = this;
        shapeDrawer = new ShapeDrawer(batch, skin.getRegion("white"));
        shapeDrawer.setPixelSize(.5f);
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        stage = new Stage(new FitViewport(1024, 576), batch);
        
        var root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        root.row();
        var stack = new Stack();
        root.add(stack).size(579, 78).expandY().bottom();
        
        progressBar = new ProgressBar(0, 100, 1, false, skin, "ads");
        progressBar.setAnimateDuration(2f);
        stack.add(progressBar);
        
        var label = new Label("Loading menu", skin);
        stack.add(label);
        
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
        
        entityController.clear();
        var bg = new AsteroidsBackgroundEntity();
        entityController.add(bg);
        
        var player = new AsteroidsPlayerEntity();
        entityController.add(player);
        player.setPosition(512, 288);
        player.teleportCollisionBox(true);
        
        spawnAsteroids();
        
        camera.position.set(512, 288, 0);
    }
    
    @Override
    public void act(float delta) {
        entityController.act(delta);
        noPoints = false;
        
        var asteroidCount = 0;
        for (var entity : entityController.entities) {
            if (entity instanceof AsteroidsAsteroidEntity) asteroidCount++;
        }
        
        if (asteroidCount == 0) spawnAsteroids();
        
        progressBar.setValue(progress);
        if (progress >= 100) {
            core.transition(new MenuScreen());
            bgm_minigame6.stop();
        }
        
        if (respawnCounter >= 0) {
            respawnCounter -= delta;
            if (respawnCounter < 0) {
                noPoints = true;
                for (var entity : entityController.entities) {
                    if (entity instanceof AsteroidsAsteroidEntity) entity.destroy = true;
                }
    
                var player = new AsteroidsPlayerEntity();
                entityController.add(player);
                player.setPosition(512, 288);
                player.teleportCollisionBox(true);
    
                asteroidSpawn = 3;
                spawnAsteroids();
            }
        }
        
        stage.act(delta);
    }
    
    public void spawnAsteroids() {
        for (int i = 0; i < asteroidSpawn; i++) {
            var asteroid = new AsteroidsAsteroidEntity(SpineAsteroidsAsteroid.animationLarge);
            entityController.add(asteroid);
            
            float spawnX, spawnY;
            
            do {
                spawnX = MathUtils.random(1024);
                spawnY = MathUtils.random(576);
            } while (Utils.pointDistance(spawnX, spawnY, AsteroidsPlayerEntity.player.x, AsteroidsPlayerEntity.player.y) < 200f);
            
            asteroid.setPosition(spawnX, spawnY);
            asteroid.teleportCollisionBox(true);
            asteroid.animationState.setAnimation(0, SpineAsteroidsAsteroid.animationLarge, false);
            asteroid.setMotion(100, MathUtils.random(360f));
        }
        asteroidSpawn++;
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
