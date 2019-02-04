/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author cameron.kennedy
 */
public class Explosion extends BaseActor{
    
    private Sound exSound;
    private boolean loaded = false;
    
    public Explosion(float x, float y, Stage s) {
        super(x, y, s);
        
        //loadAnimationFromSheet("explosion.png", 6, 6, .02f, false);
        setSpeed(100);
        setMotionAngle(180);
        setBoundaryPolygon(8);
        
        exSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
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
    
    public Explosion play(){
        loaded = true;
        setAnimation(Avatars.explosionAnim.getAnim());
        setSize(Avatars.explosionAnim.getWidth()* Options.aspectRatio, Avatars.explosionAnim.getHeight()* Options.aspectRatio);
        setOrigin(this.getWidth() / 2, this.getHeight() / 2);
        exSound.play(Options.soundVolume);
        return this;
    }
    
    
}
