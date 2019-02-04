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
 * @author cameron.kennedy
 */
public class Sparkle extends BaseActor{
    
    private Sound spSound;
    private boolean loaded = false;
    
    public Sparkle(float x, float y, Stage s) {
        super(x, y, s);
        
        //loadAnimationFromSheet("sparkle.png", 8, 8, .02f, false);
        setSpeed(100);
        setMotionAngle(180);
        setBoundaryRectangle();
        
        spSound = Gdx.audio.newSound(Gdx.files.internal("sparkle.mp3"));
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        this.setZIndex(852);
        if(loaded && isAnimationFinished()){
            remove();
        }
        
    }
    
    public Sparkle play(){
        loaded = true;
        setAnimation(Avatars.sparkleAnim.getAnim());
        setSize(Avatars.sparkleAnim.getWidth()* Options.aspectRatio, Avatars.sparkleAnim.getHeight()* Options.aspectRatio);
        spSound.play(Options.soundVolume);
        return this;
    }
    
}
