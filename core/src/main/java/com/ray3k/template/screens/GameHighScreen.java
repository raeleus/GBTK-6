package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameHighScreen extends JamScreen {
    public Stage stage;
    private int input;
    private Label responseLabel;
    private ProgressBar progressBar;
    public float progress;
    
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
        
        var label = new Label("ENTER THE HIGHEST NUMBER", skin, "minigame-title");
        root.add(label);
        
        root.row();
        var table = new Table();
        root.add(table).growX();
        
        responseLabel = new Label("", skin, "high");
        table.add(responseLabel).expandX();
        
        var subTable = new Table();
        table.add(subTable);
        
        subTable.defaults().space(10);
        var imageButton = new ImageButton(skin, "high-7");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(7));
    
        imageButton = new ImageButton(skin, "high-8");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(8));
    
        imageButton = new ImageButton(skin, "high-9");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(9));
    
        subTable.row();
        imageButton = new ImageButton(skin, "high-4");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(4));
    
        imageButton = new ImageButton(skin, "high-5");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(5));
    
        imageButton = new ImageButton(skin, "high-6");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(6));
    
        subTable.row();
        imageButton = new ImageButton(skin, "high-1");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(1));
    
        imageButton = new ImageButton(skin, "high-2");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(2));
    
        imageButton = new ImageButton(skin, "high-3");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(3));
    
        subTable.row();
        imageButton = new ImageButton(skin, "high-0");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> enterNumber(0));
    
        imageButton = new ImageButton(skin, "high-submit");
        subTable.add(imageButton);
        Listeners.change(imageButton, () -> {
            if (progress < 100) {
                if (input == 420) {
                    responseLabel.setText("4:20\nGOOD JOB");
                    progress = 100;
                    responseLabel.addAction(Actions.delay(3,Actions.run(() -> {
                        core.transition(new MenuScreen());
                        bgm_minigame3.stop();
                    })));
                    sfx_uhHuh.play();
                } else if (input < 420) {
                    responseLabel.setText(input + "\nTOO LOW");
                    progress++;
                    sfx_uhuh.play();
                    if (progress >= 100) {
                        responseLabel.addAction(Actions.delay(3,Actions.run(() -> {
                            core.transition(new MenuScreen());
                            bgm_minigame3.stop();
                        })));
                    }
                } else {
                    responseLabel.setText(input + "\nTOO HIGH");
                    progress++;
                    sfx_uhuh.play();
                    if (progress >= 100) {
                        responseLabel.addAction(Actions.delay(3,Actions.run(() -> {
                            core.transition(new MenuScreen());
                            bgm_minigame3.stop();
                        })));
                    }
                }
            }
        });
        
        root.row();
        var stack = new Stack();
        root.add(stack).size(579, 78);
        
        progressBar = new ProgressBar(0, 100, 1, false, skin, "ads");
        progressBar.setAnimateDuration(1f);
        stack.add(progressBar);
    
        label = new Label("Loading menu", skin);
        stack.add(label);
        
        bgm_minigame3.setLooping(true);
        bgm_minigame3.setVolume(.3f);
        bgm_minigame3.play();
    }
    
    private void enterNumber(int number) {
        switch (number) {
            case 1:
                input++;
                sfx_plus1.play();
                break;
            case 2:
                input *= 2;
                sfx_multiplyby2.play();
                break;
            case 3:
                input /= 3;
                sfx_divideby3.play();
                break;
            case 4:
                input += 10;
                sfx_add10.play();
                break;
            case 5:
                input--;
                if (input < 0) input = 0;
                sfx_subtract1.play();
                break;
            case 6:
                input += 15;
                sfx_add15.play();
                break;
            case 7:
                input -= 5;
                if (input < 0) input = 0;
                sfx_subtract5.play();
                break;
            case 8:
                var s = Integer.toString(input);
                input = Integer.parseInt(s.substring(0, Math.max(1, s.length() - 1)));
                sfx_backspace.play();
                break;
            case 9:
                input = 0;
                sfx_reset.play();
                break;
            case 0:
                input = Integer.parseInt(input + "0");
                sfx_concatenate0.play();
                break;
        }
        
        responseLabel.setText(input);
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
    
        progressBar.setValue(progress);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(199 / 255f, 255 / 255f, 212 / 255f, 1);
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
