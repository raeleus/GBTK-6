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

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class GameTriviaScreen extends JamScreen {
    public Stage stage;
    private int index;
    private ProgressBar progressBar;
    private float progress;
    
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
    
        var label = new Label("SMORT PEOPLE TRIVIA/PUZZLES", skin, "minigame-title");
        label.setWrap(true);
        label.setAlignment(Align.center);
        root.add(label).growX();
        
        root.row();
        Table table = new Table();
        table.setColor(1,1,1,0);
        root.add(table).grow();
        loadTable(table);
    
        root.row();
        var stack = new Stack();
        root.add(stack).size(579, 78);
    
        progressBar = new ProgressBar(0, 100, 1, false, skin, "ads");
        progressBar.setAnimateDuration(1f);
        stack.add(progressBar);
    
        label = new Label("Loading Menu", skin, "minigame-progress");
        label.setAlignment(Align.center);
        stack.add(label);
        
        bgm_minigame1.setLooping(true);
        bgm_minigame1.play();
    }
    
    private int buttonsClicked;
    private boolean choseNewton;
    private void loadTable(Table table) {
        table.addAction(sequence(
                run(() -> {
                    table.setTouchable(Touchable.disabled);
                    progress += 100 / 30f;
                }),
                fadeOut(2f),
                run(() -> {
                    table.clearChildren();
                    table.setTouchable(Touchable.childrenOnly);
                    index++;
                    switch (index - 1) {
                        case 0:
                            var label = new Label("Please play this game while you wait for the main menu to load", skin, "trivia");
                            label.setWrap(true);
                            table.add(label).growX();
            
                            table.row();
                            var textButton = new TextButton("Click to Begin", skin, "trivia");
                            table.add(textButton);
                            Listeners.change(textButton,() -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 1:
                            label = new Label("What is the multiplicative inverse of 6 modulo 666?", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
    
                            buttonsClicked = 0;
    
                            table.row();
                            var textButton1 = new TextButton("1", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                sfx_uhuh.play();
                                textButton1.setText("wrong");
                                textButton1.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
    
                            table.row();
                            var textButton2 = new TextButton("6", skin, "trivia");
                            table.add(textButton2);
                            Listeners.change(textButton2, () -> {
                                sfx_uhuh.play();
                                textButton2.setText("wrong");
                                textButton2.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
    
                            table.row();
                            var textButton3 = new TextButton("111", skin, "trivia");
                            table.add(textButton3);
                            Listeners.change(textButton3, () -> {
                                sfx_uhuh.play();
                                textButton3.setText("wrong");
                                textButton3.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
    
                            table.row();
                            var textButton4 = new TextButton("777", skin, "trivia");
                            table.add(textButton4);
                            Listeners.change(textButton4, () -> {
                                sfx_uhuh.play();
                                textButton4.setText("wrong");
                                textButton4.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
                            break;
                        case 2:
                            var image = new Image(skin, "trivia-find1");
                            table.add(image);
                            image.setTouchable(Touchable.enabled);
                            Listeners.click(image, () -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 3:
                            label = new Label("What is the derivative of the Weierstrass function when x is 1.5?", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
        
                            buttonsClicked = 0;
        
                            table.row();
                            textButton1 = new TextButton("Infinity", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                sfx_uhuh.play();
                                textButton1.setText("wrong");
                                textButton1.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
        
                            table.row();
                            textButton2 = new TextButton("Negative Infinity", skin, "trivia");
                            table.add(textButton2);
                            Listeners.change(textButton2, () -> {
                                sfx_uhuh.play();
                                textButton2.setText("wrong");
                                textButton2.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
        
                            table.row();
                            textButton3 = new TextButton("i^2", skin, "trivia");
                            table.add(textButton3);
                            Listeners.change(textButton3, () -> {
                                sfx_uhuh.play();
                                textButton3.setText("wrong");
                                textButton3.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
        
                            table.row();
                            textButton4 = new TextButton("no u", skin, "trivia");
                            table.add(textButton4);
                            Listeners.change(textButton4, () -> {
                                sfx_uhuh.play();
                                textButton4.setText("wrong");
                                textButton4.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
                            break;
                        case 4:
                            image = new Image(skin, "trivia-find2");
                            table.add(image);
                            image.setTouchable(Touchable.enabled);
                            Listeners.click(image, () -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 5:
                            label = new Label("Who discovered calculus?", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
        
                            table.row();
                            textButton1 = new TextButton("Newton", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                sfx_uhuh.play();
                                textButton1.setText("wrong");
                                textButton1.setDisabled(true);
                                choseNewton = true;
                                loadTable(table);
                            });
        
                            table.row();
                            textButton2 = new TextButton("Leibniz", skin, "trivia");
                            table.add(textButton2);
                            Listeners.change(textButton2, () -> {
                                sfx_uhuh.play();
                                textButton2.setText("wrong");
                                textButton2.setDisabled(true);
                                loadTable(table);
                            });
                            break;
                        case 6:
                            label = new Label("The correct answer was " + (choseNewton ? "Leibniz" : "Newton"), skin, "trivia");
                            label.setAlignment(Align.center);
                            label.setWrap(true);
                            table.add(label).growX();
        
                            table.row();
                            textButton = new TextButton("OK", skin, "trivia");
                            table.add(textButton);
                            Listeners.change(textButton,() -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 7:
                            label = new Label("Where is the blackbird?", skin, "trivia");
                            label.setAlignment(Align.center);
                            label.setWrap(true);
                            table.add(label).growX();
                            
                            table.row();
                            image = new Image(skin, "trivia-bird1");
                            table.add(image);
                            image.setTouchable(Touchable.enabled);
                            Listeners.click(image, () -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 8:
                            label = new Label("Correct!", skin, "trivia");
                            label.setAlignment(Align.center);
                            label.setWrap(true);
                            table.add(label).growX();
        
                            table.row();
                            image = new Image(skin, "trivia-bird2");
                            table.add(image);
                            image.setTouchable(Touchable.enabled);
                            Listeners.click(image, () -> loadTable(table));
    
                            table.row();
                            textButton = new TextButton("OK", skin, "trivia");
                            table.add(textButton);
                            Listeners.change(textButton,() -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 9:
                            label = new Label("When was the loadscreen game patented?", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
        
                            table.row();
                            textButton1 = new TextButton("1998", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                textButton1.setText("YES!");
                                textButton1.setDisabled(true);
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 10:
                            label = new Label("When did the loadscreen game patent expire?", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
        
                            table.row();
                            textButton1 = new TextButton("2015", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                textButton1.setText("YES!");
                                textButton1.setDisabled(true);
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 11:
                            label = new Label("How can you patent a fucking loadscreen game?", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
        
                            table.row();
                            textButton1 = new TextButton("Let's patent a goddamned rectangle while we're at it.", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                textButton1.setText("HOORAY FOR CORPORATE GREED!");
                                textButton1.setDisabled(true);
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 12:
                            image = new Image(skin, "trivia-find3");
                            table.add(image);
                            image.setTouchable(Touchable.enabled);
                            Listeners.click(image, () -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 13:
                            label = new Label("The door to paradise is guarded by two mighty beasts, a zebra and a " +
                                    "panda, and is accompanied by the door to eternal torment. One beast always tells " +
                                    "the truth, and the other always lies. You can ask one question before they grow " +
                                    "hungry and devour you.", skin, "trivia");
                            label.setWrap(true);
                            label.setAlignment(Align.center);
                            table.add(label).growX();
        
                            buttonsClicked = 0;
        
                            table.row();
                            textButton1 = new TextButton("Ask the panda", skin, "trivia");
                            table.add(textButton1);
                            Listeners.change(textButton1, () -> {
                                sfx_uhuh.play();
                                textButton1.setText("The panda points to the door to the right\n" +
                                        "You enter and fall into a world of eternal torment");
                                textButton1.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
        
                            table.row();
                            textButton2 = new TextButton("Ask the zebra", skin, "trivia");
                            table.add(textButton2);
                            Listeners.change(textButton2, () -> {
                                sfx_uhuh.play();
                                textButton2.setText("The zebra points to the door to the left\n" +
                                        "You enter and fall into a world of eternal torment");
                                textButton2.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
        
                            table.row();
                            textButton3 = new TextButton("Ask the panda what the zebra would say", skin, "trivia");
                            table.add(textButton3);
                            Listeners.change(textButton3, () -> {
                                sfx_uhuh.play();
                                textButton3.setText("The panda says \"20 bucks\" and then devours you");
                                textButton3.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
        
                            table.row();
                            textButton4 = new TextButton("Ask the zebra what the panda would say", skin, "trivia");
                            table.add(textButton4);
                            Listeners.change(textButton4, () -> {
                                sfx_uhuh.play();
                                textButton4.setText("The zebra says \"20 bucks\" and then tickles you");
                                textButton4.setDisabled(true);
                                buttonsClicked++;
                                if (buttonsClicked >= 4) loadTable(table);
                            });
                            break;
                        case 14:
                            image = new Image(skin, "trivia-find4");
                            table.add(image);
                            image.setTouchable(Touchable.enabled);
                            Listeners.click(image, () -> {
                                loadTable(table);
                                sfx_uhHuh.play();
                            });
                            break;
                        case 15:
                            progress += 100;
                            break;
                    }
                }),
                fadeIn(2f)
        ));
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
    
        if (!MathUtils.isEqual(progressBar.getValue(), progress)) progressBar.setValue(progress);
        if (progress >= 100) {
            core.transition(new MenuScreen());
            bgm_minigame1.stop();
        }
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
