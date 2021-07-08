package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class FakeUnloadScreen extends JamScreen {
    private ProgressBar progressBar;
    private Skin skin;
    private Stage stage;
    
    public FakeUnloadScreen() {
        MenuScreen.counter = 0;
        stage = new Stage(new ScreenViewport(), batch);
        skin = createSkin();
    
        createUI();
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    private Drawable createDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        
        Texture texture = new Texture(pixmap);
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(texture));
        textureRegionDrawable.setMinWidth(0);
        return textureRegionDrawable;
    }
    
    private Skin createSkin() {
        Skin returnValue = new Skin();
        
        returnValue.add("progress-bar", createDrawable(1, 20, Color.WHITE), Drawable.class);
        
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        
        progressBarStyle.knobBefore = returnValue.getDrawable("progress-bar");
        
        returnValue.add("default-horizontal", progressBarStyle);
        
        return returnValue;
    }
    
    private void createUI() {
        stage.clear();
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.pad(20);
        
        progressBar = new ProgressBar(0, 1, .01f, false, skin);
        progressBar.setValue(1);
        progressBar.setAnimateDuration(2f);
        progressBar.setValue(0);
        root.add(progressBar).growX();
        progressBar.addAction(Actions.sequence(new Action() {
            @Override
            public boolean act(float delta) {
                return MathUtils.isEqual(progressBar.getVisualPercent(), 0f);
            }
        }, Actions.fadeOut(1f), Actions.run(() -> core.transition(new SplashScreen()))));
    }
}
