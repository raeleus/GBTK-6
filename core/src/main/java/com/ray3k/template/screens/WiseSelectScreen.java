package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class WiseSelectScreen extends JamScreen {
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
        
        var buttonGroup = new ButtonGroup<>();
        var checkbox = new CheckBox("Install the super pro edition (trial). Guarantees better graphics! Extra loot!*", skin, "wise-radio");
        window.add(checkbox).left();
        buttonGroup.add(checkbox);
    
        window.row();
        checkbox = new CheckBox("Install the basic ass bitch version. Lots of ads.", skin, "wise-radio");
        window.add(checkbox).left();
        buttonGroup.add(checkbox);
        
        window.row();
        var label = new Label("*Usefullness of loot is a subjective experience.", skin, "wise");
        window.add(label).expandY().bottom();
        
        window.row();
        var image = new Image(skin.getDrawable("wise-line-10"));
        window.add(image).growX().colspan(2);

        window.row();
        var table = new Table();
        table.align(Align.right);
        window.add(table).colspan(2).growX();

        var changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                core.transition(new WiseProgressScreen());
            }
        };
        
        table.defaults().space(10);
        var textButton = new TextButton("Next >", skin, "wise");
        table.add(textButton);
        textButton.addListener(changeListener);

        textButton = new TextButton("Cancel", skin, "wise");
        table.add(textButton);
        textButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                var textButton = (TextButton) event.getListenerActor();
                textButton.setText("Next >");
            }
        });
        textButton.addListener(changeListener);
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
