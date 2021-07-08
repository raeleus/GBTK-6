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

public class WiseEulaScreen extends JamScreen {
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
        
        var label = new Label("Please accept the following EULA to continue.", skin, "wise");
        window.add(label);
        
        window.row();
        label = new Label("END USER LICENSE AGREEMENT\n" +
                "================================\n" +
                "PLEASE READ THIS END USER LICENSE AGREEMENT (\"EULA\") BEFORE USING THIS SOFTWARE, BY USING THIS SOFTWARE YOU ARE AGREEING TO BE BOUND BY THE TERMS OF THIS LICENSE. IF YOU DO NOT AGREE TO THESE TERMMS, PROMPTLY CEASE ALL FURTHER INSTALLATION OR USE OF THE SOFTWARE.\n\n" +
                "You hereby grant Raeleus the sole proprietorship of your soul. He hereby claims that which owed. In this case, it's 50% of all your wages from inception to your death.\n\n" +
                "Who cares if EULA's are non-enforceable by law? If you see this block of big, scary text, you will be gobsmacked into submission and forced to comply to my every little request. While we're on this subject, send me all of your passwords and personal photos. Actually, don't. I already have access to those because I installed a \"potentially unwanted software\" under the guise of Anti-Piracy/Anti-Cheat mechanisms.",  skin, "wise");
        label.setWrap(true);
        var scrollPane = new ScrollPane(label, skin, "wise");
        scrollPane.setFadeScrollBars(false);
        window.add(scrollPane);
        
        window.row();
        var image = new Image(skin.getDrawable("wise-line-10"));
        window.add(image).growX();

        window.row();
        var table = new Table();
        table.align(Align.right);
        window.add(table).colspan(2).growX();

        var changeListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                core.transition(new WiseSelectScreen());
            }
        };
        
        table.defaults().space(10);
        var textButton = new TextButton("I accept >", skin, "wise");
        table.add(textButton);
        textButton.addListener(changeListener);

        textButton = new TextButton("Cancel", skin, "wise");
        table.add(textButton);
        textButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                var textButton = (TextButton) event.getListenerActor();
                textButton.setText("I accept >");
            }
        });
        textButton.addListener(changeListener);
        
        stage.setScrollFocus(scrollPane);
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
