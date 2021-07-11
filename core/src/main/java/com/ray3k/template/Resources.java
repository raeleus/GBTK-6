package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;
import java.lang.String;

public class Resources {
    public static Skin skin_skin;

    public static TextureAtlas textures_output;

    public static Sound sfx_add10;

    public static Sound sfx_add15;

    public static Sound sfx_backspace;

    public static Sound sfx_bang;

    public static Sound sfx_boom;

    public static Sound sfx_chomp;

    public static Sound sfx_click;

    public static Sound sfx_concatenate0;

    public static Sound sfx_divideby3;

    public static Sound sfx_explosion;

    public static Sound sfx_hah;

    public static Sound sfx_mmmmm;

    public static Sound sfx_multiplyby2;

    public static Sound sfx_ohMyHead;

    public static Sound sfx_ouch;

    public static Sound sfx_plus1;

    public static Sound sfx_reset;

    public static Sound sfx_subtract1;

    public static Sound sfx_subtract5;

    public static Sound sfx_uhHuh;

    public static Sound sfx_uhuh;

    public static Sound sfx_woof;

    public static Music bgm_minigame1;

    public static Music bgm_minigame2;

    public static Music bgm_minigame3;

    public static Music bgm_minigame4;

    public static Music bgm_minigame5;

    public static Music bgm_war;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        SpineAsteroidsAsteroid.skeletonData = assetManager.get("spine/asteroids-asteroid.json");
        SpineAsteroidsAsteroid.animationData = assetManager.get("spine/asteroids-asteroid.json-animation");
        SpineAsteroidsAsteroid.animationLarge = SpineAsteroidsAsteroid.skeletonData.findAnimation("large");
        SpineAsteroidsAsteroid.animationMed = SpineAsteroidsAsteroid.skeletonData.findAnimation("med");
        SpineAsteroidsAsteroid.animationSmall = SpineAsteroidsAsteroid.skeletonData.findAnimation("small");
        SpineAsteroidsAsteroid.skinDefault = SpineAsteroidsAsteroid.skeletonData.findSkin("default");
        SpineAsteroidsBullet.skeletonData = assetManager.get("spine/asteroids-bullet.json");
        SpineAsteroidsBullet.animationData = assetManager.get("spine/asteroids-bullet.json-animation");
        SpineAsteroidsBullet.animationAnimation = SpineAsteroidsBullet.skeletonData.findAnimation("animation");
        SpineAsteroidsBullet.skinDefault = SpineAsteroidsBullet.skeletonData.findSkin("default");
        SpineAsteroidsPlayer.skeletonData = assetManager.get("spine/asteroids-player.json");
        SpineAsteroidsPlayer.animationData = assetManager.get("spine/asteroids-player.json-animation");
        SpineAsteroidsPlayer.animationAnimation = SpineAsteroidsPlayer.skeletonData.findAnimation("animation");
        SpineAsteroidsPlayer.skinDefault = SpineAsteroidsPlayer.skeletonData.findSkin("default");
        SpineDonutBg.skeletonData = assetManager.get("spine/donut-bg.json");
        SpineDonutBg.animationData = assetManager.get("spine/donut-bg.json-animation");
        SpineDonutBg.animationAnimation = SpineDonutBg.skeletonData.findAnimation("animation");
        SpineDonutBg.skinDefault = SpineDonutBg.skeletonData.findSkin("default");
        SpineDonutDonut.skeletonData = assetManager.get("spine/donut-donut.json");
        SpineDonutDonut.animationData = assetManager.get("spine/donut-donut.json-animation");
        SpineDonutDonut.animationAnger0 = SpineDonutDonut.skeletonData.findAnimation("anger-0");
        SpineDonutDonut.animationAnger1 = SpineDonutDonut.skeletonData.findAnimation("anger-1");
        SpineDonutDonut.animationAnger2 = SpineDonutDonut.skeletonData.findAnimation("anger-2");
        SpineDonutDonut.animationAnger3 = SpineDonutDonut.skeletonData.findAnimation("anger-3");
        SpineDonutDonut.animationAnger4 = SpineDonutDonut.skeletonData.findAnimation("anger-4");
        SpineDonutDonut.animationAnger5 = SpineDonutDonut.skeletonData.findAnimation("anger-5");
        SpineDonutDonut.animationAnger6 = SpineDonutDonut.skeletonData.findAnimation("anger-6");
        SpineDonutDonut.skinDefault = SpineDonutDonut.skeletonData.findSkin("default");
        SpineDonutPackage.skeletonData = assetManager.get("spine/donut-package.json");
        SpineDonutPackage.animationData = assetManager.get("spine/donut-package.json-animation");
        SpineDonutPackage.animationAnimation = SpineDonutPackage.skeletonData.findAnimation("animation");
        SpineDonutPackage.skinDefault = SpineDonutPackage.skeletonData.findSkin("default");
        SpineDragon.skeletonData = assetManager.get("spine/dragon.json");
        SpineDragon.animationData = assetManager.get("spine/dragon.json-animation");
        SpineDragon.animationBlaze = SpineDragon.skeletonData.findAnimation("blaze");
        SpineDragon.animationStand = SpineDragon.skeletonData.findAnimation("stand");
        SpineDragon.skinDefault = SpineDragon.skeletonData.findSkin("default");
        SpineDrugDrug.skeletonData = assetManager.get("spine/drug-drug.json");
        SpineDrugDrug.animationData = assetManager.get("spine/drug-drug.json-animation");
        SpineDrugDrug.animationAnimation = SpineDrugDrug.skeletonData.findAnimation("animation");
        SpineDrugDrug.skinDefault = SpineDrugDrug.skeletonData.findSkin("default");
        SpineDrugMap.skeletonData = assetManager.get("spine/drug-map.json");
        SpineDrugMap.animationData = assetManager.get("spine/drug-map.json-animation");
        SpineDrugMap.animationAnimation = SpineDrugMap.skeletonData.findAnimation("animation");
        SpineDrugMap.skinDefault = SpineDrugMap.skeletonData.findSkin("default");
        SpineDrugPlayer.skeletonData = assetManager.get("spine/drug-player.json");
        SpineDrugPlayer.animationData = assetManager.get("spine/drug-player.json-animation");
        SpineDrugPlayer.animationAnimation = SpineDrugPlayer.skeletonData.findAnimation("animation");
        SpineDrugPlayer.skinDefault = SpineDrugPlayer.skeletonData.findSkin("default");
        SpineMenuLoading.skeletonData = assetManager.get("spine/menu-loading.json");
        SpineMenuLoading.animationData = assetManager.get("spine/menu-loading.json-animation");
        SpineMenuLoading.animationAnimation = SpineMenuLoading.skeletonData.findAnimation("animation");
        SpineMenuLoading.animationHide = SpineMenuLoading.skeletonData.findAnimation("hide");
        SpineMenuLoading.skinDefault = SpineMenuLoading.skeletonData.findSkin("default");
        SpineNvidia0.skeletonData = assetManager.get("spine/nvidia-0.json");
        SpineNvidia0.animationData = assetManager.get("spine/nvidia-0.json-animation");
        SpineNvidia0.animation3080 = SpineNvidia0.skeletonData.findAnimation("3080");
        SpineNvidia0.animationFwho = SpineNvidia0.skeletonData.findAnimation("fwho");
        SpineNvidia0.animationInfinty = SpineNvidia0.skeletonData.findAnimation("infinty");
        SpineNvidia0.animationStanding = SpineNvidia0.skeletonData.findAnimation("standing");
        SpineNvidia0.skinDefault = SpineNvidia0.skeletonData.findSkin("default");
        SpineNvidia1.skeletonData = assetManager.get("spine/nvidia-1.json");
        SpineNvidia1.animationData = assetManager.get("spine/nvidia-1.json-animation");
        SpineNvidia1.animationAnimation = SpineNvidia1.skeletonData.findAnimation("animation");
        SpineNvidia1.animationStanding = SpineNvidia1.skeletonData.findAnimation("standing");
        SpineNvidia1.skinDefault = SpineNvidia1.skeletonData.findSkin("default");
        SpineNvidia2.skeletonData = assetManager.get("spine/nvidia-2.json");
        SpineNvidia2.animationData = assetManager.get("spine/nvidia-2.json-animation");
        SpineNvidia2.animationAnimation = SpineNvidia2.skeletonData.findAnimation("animation");
        SpineNvidia2.animationStanding = SpineNvidia2.skeletonData.findAnimation("standing");
        SpineNvidia2.skinDefault = SpineNvidia2.skeletonData.findSkin("default");
        SpineNvidia3.skeletonData = assetManager.get("spine/nvidia-3.json");
        SpineNvidia3.animationData = assetManager.get("spine/nvidia-3.json-animation");
        SpineNvidia3.animationAnimation = SpineNvidia3.skeletonData.findAnimation("animation");
        SpineNvidia3.animationStanding = SpineNvidia3.skeletonData.findAnimation("standing");
        SpineNvidia3.skinDefault = SpineNvidia3.skeletonData.findSkin("default");
        SpineSandBackground.skeletonData = assetManager.get("spine/sand-background.json");
        SpineSandBackground.animationData = assetManager.get("spine/sand-background.json-animation");
        SpineSandBackground.animationAsteroids = SpineSandBackground.skeletonData.findAnimation("asteroids");
        SpineSandBackground.animationInvaders = SpineSandBackground.skeletonData.findAnimation("invaders");
        SpineSandBackground.skinDefault = SpineSandBackground.skeletonData.findSkin("default");
        SpineTree.skeletonData = assetManager.get("spine/tree.json");
        SpineTree.animationData = assetManager.get("spine/tree.json-animation");
        SpineTree.animationAnimation = SpineTree.skeletonData.findAnimation("animation");
        SpineTree.skinDefault = SpineTree.skeletonData.findSkin("default");
        SpineWindowsHi.skeletonData = assetManager.get("spine/windows-hi.json");
        SpineWindowsHi.animationData = assetManager.get("spine/windows-hi.json-animation");
        SpineWindowsHi.animationAnimation = SpineWindowsHi.skeletonData.findAnimation("animation");
        SpineWindowsHi.animationStand = SpineWindowsHi.skeletonData.findAnimation("stand");
        SpineWindowsHi.skinDefault = SpineWindowsHi.skeletonData.findSkin("default");
        SpineWindowsUpdate.skeletonData = assetManager.get("spine/windows-update.json");
        SpineWindowsUpdate.animationData = assetManager.get("spine/windows-update.json-animation");
        SpineWindowsUpdate.animationAnimation = SpineWindowsUpdate.skeletonData.findAnimation("animation");
        SpineWindowsUpdate.animationEnd = SpineWindowsUpdate.skeletonData.findAnimation("end");
        SpineWindowsUpdate.animationStand = SpineWindowsUpdate.skeletonData.findAnimation("stand");
        SpineWindowsUpdate.animationStart = SpineWindowsUpdate.skeletonData.findAnimation("start");
        SpineWindowsUpdate.skinDefault = SpineWindowsUpdate.skeletonData.findSkin("default");
        textures_output = assetManager.get("textures/output.atlas");
        sfx_add10 = assetManager.get("sfx/add 10.mp3");
        sfx_add15 = assetManager.get("sfx/add15.mp3");
        sfx_backspace = assetManager.get("sfx/backspace.mp3");
        sfx_bang = assetManager.get("sfx/bang.mp3");
        sfx_boom = assetManager.get("sfx/boom.mp3");
        sfx_chomp = assetManager.get("sfx/chomp.mp3");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_concatenate0 = assetManager.get("sfx/concatenate0.mp3");
        sfx_divideby3 = assetManager.get("sfx/divideby3.mp3");
        sfx_explosion = assetManager.get("sfx/explosion.mp3");
        sfx_hah = assetManager.get("sfx/hah.mp3");
        sfx_mmmmm = assetManager.get("sfx/mmmmm.mp3");
        sfx_multiplyby2 = assetManager.get("sfx/multiplyby2.mp3");
        sfx_ohMyHead = assetManager.get("sfx/oh my head.mp3");
        sfx_ouch = assetManager.get("sfx/ouch.mp3");
        sfx_plus1 = assetManager.get("sfx/plus1.mp3");
        sfx_reset = assetManager.get("sfx/reset.mp3");
        sfx_subtract1 = assetManager.get("sfx/subtract1.mp3");
        sfx_subtract5 = assetManager.get("sfx/subtract5.mp3");
        sfx_uhHuh = assetManager.get("sfx/uh huh.mp3");
        sfx_uhuh = assetManager.get("sfx/uhuh.mp3");
        sfx_woof = assetManager.get("sfx/woof.mp3");
        bgm_minigame1 = assetManager.get("bgm/minigame1.mp3");
        bgm_minigame2 = assetManager.get("bgm/minigame2.mp3");
        bgm_minigame3 = assetManager.get("bgm/minigame3.mp3");
        bgm_minigame4 = assetManager.get("bgm/minigame4.mp3");
        bgm_minigame5 = assetManager.get("bgm/minigame5.mp3");
        bgm_war = assetManager.get("bgm/war.mp3");
    }

    public static class SpineAsteroidsAsteroid {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationLarge;

        public static Animation animationMed;

        public static Animation animationSmall;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineAsteroidsBullet {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineAsteroidsPlayer {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDonutBg {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDonutDonut {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnger0;

        public static Animation animationAnger1;

        public static Animation animationAnger2;

        public static Animation animationAnger3;

        public static Animation animationAnger4;

        public static Animation animationAnger5;

        public static Animation animationAnger6;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDonutPackage {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDragon {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationBlaze;

        public static Animation animationStand;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDrugDrug {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDrugMap {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDrugPlayer {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineMenuLoading {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHide;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineNvidia0 {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animation3080;

        public static Animation animationFwho;

        public static Animation animationInfinty;

        public static Animation animationStanding;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineNvidia1 {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStanding;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineNvidia2 {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStanding;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineNvidia3 {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStanding;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineSandBackground {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAsteroids;

        public static Animation animationInvaders;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineTree {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineWindowsHi {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineWindowsUpdate {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationEnd;

        public static Animation animationStand;

        public static Animation animationStart;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class Values {
        public static float jumpVelocity = 10.0f;

        public static String name = "Raeleus";

        public static boolean godMode = true;

        public static int id = 10;

        public static Range speedLimitRange = new Range(0.0f, 10.0f);

        public static float speedLimit = 5.0f;
    }

    public static class Range {
        public float min;

        public float max;

        Range(float min, float max) {
            this.min = min;
            this.max = max;
        }
    }
}
