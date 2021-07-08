package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class WiseScreen extends JamScreen {
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
        
        window.add().size(200, 300);
        
        var table = new Table();
        window.add(table).growX();
        
        var subTable = new Table();
        table.add(subTable).growX();
        
        var image = new Image(skin.getDrawable("wise-icon"));
        subTable.add(image).space(10);
        
        var label = new Label("Welcome to the Murderzone 3 Setup program. This program will install Murderzone 3 on your computer.",  skin, "wise");
        label.setWrap(true);
        subTable.add(label).growX();
        
        table.row();
        label = new Label("\nIt is strongly recommended that you exit all other programs before running this Setup program. In fact, I'd go as far as uninstalling Steam so you don't have to worry about being suckered by \"Summer Sales\" and buying tons of shitty games you'll never play.\n\nClick Cancel to quit Setup and go on with your miserable life. Hey, you're the one that insisted on being here. Don't blame me for your failures and missed opportunities.", skin, "wise");
        label.setWrap(true);
        table.add(label).growX();
        
        window.row();
        image = new Image(skin.getDrawable("wise-line-10"));
        window.add(image).growX().colspan(2);

        window.row();
        table = new Table();
        table.align(Align.right);
        window.add(table).colspan(2).growX();

        var changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                core.transition(new WiseEulaScreen());
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
