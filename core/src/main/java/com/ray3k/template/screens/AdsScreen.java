package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class AdsScreen extends JamScreen {
    public Stage stage;
    private int index;
    private float progress;
    private ProgressBar progressBar;
    
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        stage = new Stage(viewport, batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.pad(10);
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);
        
        var label = new Label("Please click the ad to boost loading speed!", skin, "minigame-title");
        root.add(label);
        label.addAction(sequence(alpha(0), delay(3f), fadeIn(.5f)));
        
        var drawables = new Array<>(new Drawable[] {
                skin.getDrawable("blue-screen"),
                skin.getDrawable("find-hot-single"),
                skin.getDrawable("game-plays"),
                skin.getDrawable("game-plays-itself"),
                skin.getDrawable("loading1"),
                skin.getDrawable("pooh"),
                skin.getDrawable("shrek"),
                skin.getDrawable("monke-antivirus"),
                skin.getDrawable("monke-facxe"),
                skin.getDrawable("nuke"),
                skin.getDrawable("pooping-monke"),
                skin.getDrawable("sexy-sob"),
                skin.getDrawable("shrek-character"),
                skin.getDrawable("special-offer"),
                skin.getDrawable("virus"),
                skin.getDrawable("warning"),
                skin.getDrawable("winner"),
                skin.getDrawable("mail"),
                skin.getDrawable("yoda")});
        
        root.row();
        var container = new Container<Image>();
        root.add(container).grow();
        var image = new Image(drawables.get(index));
        container.setActor(image);
        image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index++;
                if (index >= drawables.size) index = 0;
                image.setDrawable(drawables.get(index));
                container.padLeft(MathUtils.random(container.getWidth() - image.getWidth())).padBottom(MathUtils.random(container.getHeight() - image.getHeight())).align(Align.bottomLeft);
                progress += 5;
                if (progress > 100) {
                    core.transition(new MenuScreen());
                    bgm_minigame2.stop();
                }
            }
        });
        
        root.row();
        var stack = new Stack();
        root.add(stack).size(579, 78);
        
        progressBar = new ProgressBar(0, 100, .1f, false, skin, "ads");
        stack.add(progressBar);
        
        label = new Label("DOWNLOADING GAME ASSETS", skin, "minigame-progress");
        label.setAlignment(Align.center);
        stack.add(label);
        
        bgm_minigame2.setLooping(true);
        bgm_minigame2.play();
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        
        progress -= 5f * delta;
        if (progress < 0) progress = 0;
        progressBar.setValue(progress);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(206 / 255f, 106 / 255f, 169 / 255f, 1);
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
