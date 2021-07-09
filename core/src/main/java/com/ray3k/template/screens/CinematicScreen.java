package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.*;

public class CinematicScreen extends JamScreen {
    public Stage stage;
    private Drawable drawable;
    private JamScreen jamScreen;
    
    public CinematicScreen(Drawable drawable, JamScreen jamScreen) {
        this.drawable = drawable;
        this.jamScreen = jamScreen;
    }
    
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        stage = new Stage(viewport, batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.pad(10);
        root.setTouchable(Touchable.enabled);
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);
        root.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                root.addAction(sequence(
                        delay(.5f),
                        run(() -> core.transition(jamScreen))
                ));
            }
        });
        
        var stack = new Stack();
        root.add(stack).grow();
        
        var image = new Image(drawable);
        image.setScaling(Scaling.fit);
        stack.add(image);
        
        var table = new Table();
        stack.add(table);
        
        var label = new Label("CLICK TO CONTINUE", skin, "minigame-progress");
        table.add(label).expand().bottom().padBottom(30);
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        if (drawable instanceof SpineDrawable) {
            ((SpineDrawable) drawable).update(delta);
        }
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
