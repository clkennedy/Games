/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author ARustedKnight
 */
public class Splatter extends BaseActor{
    
    private Sound exSound;
    private boolean loaded = false;
    
    public Splatter(float x, float y, Stage s) {
        super(x, y, s);
        
        //loadAnimationFromSheet("bloodSpatter.png", 3, 3, .1f, false);
        setSize(100, 100);
        setSpeed(50);
        setMotionAngle(180);
        
        exSound = Gdx.audio.newSound(Gdx.files.internal("chickensquawk.wav"));
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        if(loaded && isAnimationFinished()){
            remove();
        }
    }
    
    public Splatter play(){
        loaded = true;
        setAnimation(Avatars.splatterAnim.getAnim());
        setSize(Avatars.splatterAnim.getWidth()* Options.aspectRatio, Avatars.splatterAnim.getHeight()* Options.aspectRatio);
        exSound.play(Options.soundVolume);
        return this;
    }
    
}
