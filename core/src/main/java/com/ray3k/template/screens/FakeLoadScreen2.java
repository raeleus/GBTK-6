package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class FakeLoadScreen2 extends JamScreen {
    private ProgressBar progressBar;
    private Skin skin;
    private Skin skin2;
    private Stage stage;
    private int index;
    
    public FakeLoadScreen2() {
        stage = new Stage(new FitViewport(1024, 576), batch);
        skin = Core.skin;
        skin2 = createSkin();
    
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
        Gdx.input.setInputProcessor(stage);
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.pad(20);
        
        var label = new Label("", skin);
        
        progressBar = new ProgressBar(0, 1, .01f, false, skin2);
        progressBar.setValue(0);
        progressBar.setAnimateDuration(5f);
        progressBar.setValue(1);
        root.add(progressBar).growX();
        progressBar.setTouchable(Touchable.enabled);
        progressBar.addAction(Actions.forever(Actions.sequence(new Action() {
            @Override
            public boolean act(float delta) {
                return MathUtils.isEqual(progressBar.getVisualPercent(), 1f);
            }
        }, Actions.delay(3f), Actions.run(() -> {
            progressBar.setValue(0);
            progressBar.updateVisualValue();
            progressBar.setValue(1);
            System.out.println(index);
            switch (index) {
                case 0:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Reticulating Splines");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 1:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Over-waxing Bannisters");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 2:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Stroking Rods");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 3:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Nipping buds");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 4:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Please excuse our mess while we tidy up...");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 5:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Almost there...");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 6:
                    label.addAction(Actions.sequence(Actions.moveToAligned(0, 318, Align.right, 1f),
                            Actions.delay(.5f),
                            Actions.run(() -> {
                                label.setText("Just one more second...");
                                label.pack();
                                label.setPosition(1024, 318, Align.left);
                            }),
                            Actions.moveToAligned(512, 318, Align.center, 1f)));
                    break;
                case 7:
                    core.setScreen(new AdsScreen());
                    break;
            }
            index++;
        }))));
        
        stage.addActor(label);
        label.pack();
        label.setPosition(512, 318, Align.center);
    }
}
