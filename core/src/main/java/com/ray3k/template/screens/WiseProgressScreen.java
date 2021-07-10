package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class WiseProgressScreen extends JamScreen {
    public Stage stage;
    
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        stage = new Stage(viewport, batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.pad(10);
        root.setBackground(skin.getDrawable("wise-bg"));
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);
        
        var window = new Table();
        window.setBackground(skin.getDrawable("wise-window-10"));
        root.add(window);
        
        var label = new Label("INSTALLING", skin, "wise");
        window.add(label);
        
        window.row();
        var progressBar = new ProgressBar(0, 100, .1f, false, skin, "wise");
        progressBar.setAnimateDuration(10f);
        progressBar.setValue(100);
        window.add(progressBar).size(328, 55);
        progressBar.addAction(Actions.sequence(new Action() {
            @Override
            public boolean act(float delta) {
                return MathUtils.isEqual(progressBar.getVisualPercent(), 1f);
            }
        }, Actions.delay(5f), Actions.run(() -> core.transition(new GameAsteroidsScreen()))));
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            viewport.update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
    }
    
    @Override
    public void hide() {
        super.hide();
    }
}
