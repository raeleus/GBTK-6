package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Skeleton;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;

import static com.ray3k.template.Core.*;

public class NvidiaScreen extends JamScreen {
    public Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private boolean downloading;
    private Label downloadingLabel;
    private Label downloadingPercent;
    
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        stage = new Stage(viewport, batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.pad(10);
        root.setBackground(skin.getDrawable("nvidia-tile-tiled"));
        stage.addActor(root);
        Gdx.input.setInputProcessor(stage);
        
        spineDrawables = new Array<>();
        var skeleton = new Skeleton(SpineNvidia0.skeletonData);
        var animationState = new AnimationState(SpineNvidia0.animationData);
        animationState.setAnimation(0, SpineNvidia0.animationStanding, false);
        animationState.apply(skeleton);
        animationState.addAnimation(0, SpineNvidia0.animation3080, false, 0);
        animationState.addAnimation(0, SpineNvidia0.animationInfinty, false, 0);
        animationState.addAnimation(0, SpineNvidia0.animationFwho, false, 0);
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (entry.getAnimation() == SpineNvidia0.animationFwho) {
                    animationState.addAnimation(0, SpineNvidia0.animation3080, false, 0);
                    animationState.addAnimation(0, SpineNvidia0.animationInfinty, false, 0);
                    animationState.addAnimation(0, SpineNvidia0.animationFwho, false, 0);
                }
            }
        });
        var spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.setCrop(148, 69,719, 457);
        spineDrawables.add(spineDrawable);
        var table = new Table();
        table.setBackground(spineDrawable);
        root.add(table);
        
        var subTable = new Table();
        table.add(subTable).padTop(20);
        
        subTable.defaults().space(30);
        var imageButton = new ImageButton(skin, "nvidia-download");
        subTable.add(imageButton);
        
        var label = new Label("GeeForce Game Ready Driver\nVersion-NEW", skin, "nvidia");
        subTable.add(label);
        
        var progressTable = new Table();
        subTable.add(progressTable);
        
        downloadingLabel = new Label("Ready to DOWNLOAD", skin, "nvidia");
        progressTable.add(downloadingLabel);
        
        downloadingPercent = new Label("", skin, "nvidia");
        progressTable.add(downloadingPercent).expandX().right();
        
        progressTable.row();
        var progressBar = new ProgressBar(0, 100, .1f, false, skin, "nvidia-small");
        progressTable.add(progressBar).colspan(2).size(169, 12);
        
        imageButton = new ImageButton(skin, "nvidia-pause");
        subTable.add(imageButton).spaceRight(10);
        
        imageButton = new ImageButton(skin, "nvidia-stop");
        subTable.add(imageButton).spaceLeft(10);
        
        var textButton = new TextButton("DOWNLOAD", skin, "nvidia");
        subTable.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                downloading = true;
                var textButton = (TextButton) actor;
                textButton.setDisabled(true);
                
                downloadingLabel.setText("Downloading...");
                downloadingPercent.addAction(Actions.sequence(new TemporalAction(10f, Interpolation.bounce) {
                    @Override
                    protected void update(float percent) {
                        downloadingPercent.setText(MathUtils.floor(percent * 100) + "%");
                        progressBar.setValue(percent * 100);
                    }
                }, Actions.delay(2f), Actions.run(() -> addInstallingTable1())));
            }
        });
        
        table.row();
        subTable = new Table();
        table.add(subTable).expandY().bottom();
        
        
    }
    
    private void addInstallingTable1() {
        var root = new Table();
        root.setBackground(skin.getDrawable("nvidia-dialog-bg"));
        root.setFillParent(true);
        stage.addActor(root);
        
        var skeleton = new Skeleton(SpineNvidia1.skeletonData);
        var animationState = new AnimationState(SpineNvidia1.animationData);
        animationState.setAnimation(0, SpineNvidia1.animationStanding, false);
        animationState.apply(skeleton);
        animationState.addAnimation(0, SpineNvidia1.animationAnimation, false, 0f);
        var spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.setCrop(102, 83, 905 - 102, 512 - 83);
        spineDrawables.add(spineDrawable);
        
        var table = new Table();
        table.setBackground(spineDrawable);
        table.padLeft(40);
        root.add(table);
        
        table.defaults().expandX().left();
        var label = new Label("BS2.0", skin, "nvidia-title");
        table.add(label);
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.fadeIn(1f));
        
        table.row();
        label = new Label("MAX FPS\nMAX QUALITY\nPOWERED BY BULLSHIT", skin, "nvidia-large");
        table.add(label).padBottom(200);
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.delay(.5f,Actions.fadeIn(1f)));
        
        root.row();
        label = new Label("GeeForce Game Ready Driver", skin, "nvidia-white");
        root.add(label).left();
        
        root.row();
        var progressBar = new ProgressBar(0, 100, .1f, false, skin, "nvidia");
        root.add(progressBar).size(760, 23).left();
        progressBar.addAction(Actions.sequence(
                new TemporalAction(10f, Interpolation.circleIn) {
                    @Override
                    protected void update(float percent) {
                        progressBar.setValue(percent * 100);
                    }
                },
                Actions.delay(2f),
                Actions.run(() -> {
                    root.remove();
                    addInstallingTable2();
                })
        ));
    
        root.row();
        label = new Label("Installing Graphics Driver", skin, "nvidia-white");
        root.add(label).left();
    }
    
    private void addInstallingTable2() {
        var root = new Table();
        root.setBackground(skin.getDrawable("nvidia-dialog-bg"));
        root.setFillParent(true);
        stage.addActor(root);
        
        var skeleton = new Skeleton(SpineNvidia2.skeletonData);
        var animationState = new AnimationState(SpineNvidia2.animationData);
        animationState.setAnimation(0, SpineNvidia2.animationStanding, false);
        animationState.apply(skeleton);
        animationState.addAnimation(0, SpineNvidia2.animationAnimation, false, 0f);
        var spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.setCrop(102, 83, 905 - 102, 512 - 83);
        spineDrawables.add(spineDrawable);
        
        var table = new Table();
        table.setBackground(spineDrawable);
        table.padLeft(40);
        root.add(table);
        
        table.defaults().expandX().left();
        var label = new Label("INVIDIAR VTX 3080", skin, "nvidia-title");
        table.add(label).padTop(10);
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.fadeIn(1f));
        
        table.row();
        label = new Label("\"BY PAYING MORE YOU\nBECOME A BETTER PLAYER\"", skin, "nvidia-large");
        table.add(label);
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.delay(.5f,Actions.fadeIn(1f)));
    
        table.row();
        label = new Label("-OUR MARKETING DEPARTMENT", skin, "nvidia-medium");
        table.add(label).expandY().top();
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.delay(.7f,Actions.fadeIn(1f)));
        
        root.row();
        label = new Label("GeeForce Game Ready Driver", skin, "nvidia-white");
        root.add(label).left();
        
        root.row();
        var progressBar = new ProgressBar(0, 100, .1f, false, skin, "nvidia");
        root.add(progressBar).size(760, 23).left();
        progressBar.addAction(Actions.sequence(
                new TemporalAction(10f, Interpolation.circleIn) {
                    @Override
                    protected void update(float percent) {
                        progressBar.setValue(percent * 100);
                    }
                },
                Actions.delay(2f),
                Actions.run(() -> {
                    root.remove();
                    addInstallingTable3();
                })
        ));
        
        root.row();
        label = new Label("Installing USB C Driver", skin, "nvidia-white");
        root.add(label).left();
    }
    
    private void addInstallingTable3() {
        var root = new Table();
        root.setBackground(skin.getDrawable("nvidia-dialog-bg"));
        root.setFillParent(true);
        stage.addActor(root);
        
        var skeleton = new Skeleton(SpineNvidia3.skeletonData);
        var animationState = new AnimationState(SpineNvidia3.animationData);
        animationState.setAnimation(0, SpineNvidia3.animationStanding, false);
        animationState.apply(skeleton);
        animationState.addAnimation(0, SpineNvidia3.animationAnimation, false, 0f);
        var spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.setCrop(102, 83, 905 - 102, 512 - 83);
        spineDrawables.add(spineDrawable);
        
        var table = new Table();
        table.setBackground(spineDrawable);
        table.padLeft(40);
        root.add(table);
        
        table.defaults().space(30);
        var label = new Label("EXPERIENCE THE\nGEEFORCE SEXPERIENCE", skin, "nvidia-title");
        label.setAlignment(Align.center);
        table.add(label).padTop(10);
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.fadeIn(1f));
        
        table.row();
        label = new Label("Our Anti-Crypto Technology puts\nmore GPUs in the hands of players*", skin, "nvidia-large");
        label.setAlignment(Align.center);
        table.add(label);
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.delay(.5f,Actions.fadeIn(1f)));
        
        table.row();
        label = new Label("*unfortunately they were all bought out by scalpers", skin, "nvidia-medium");
        table.add(label).expand().top().right();
        label.setColor(1, 1, 1, 0);
        label.addAction(Actions.delay(.7f,Actions.fadeIn(1f)));
        
        root.row();
        label = new Label("GeeForce Game Ready Driver", skin, "nvidia-white");
        root.add(label).left();
        
        root.row();
        var progressBar = new ProgressBar(0, 100, .1f, false, skin, "nvidia");
        root.add(progressBar).size(760, 23).left();
        progressBar.addAction(Actions.sequence(
                new TemporalAction(10f, Interpolation.circleIn) {
                    @Override
                    protected void update(float percent) {
                        progressBar.setValue(percent * 100);
                    }
                },
                Actions.delay(2f),
                Actions.run(() -> {
                    root.remove();
                    core.transition(new MenuScreen());
                })
        ));
        
        root.row();
        label = new Label("Installing Buggy GeeForce UX", skin, "nvidia-white");
        root.add(label).left();
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        for (var spineDrawable : spineDrawables) {
            spineDrawable.update(delta);
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
