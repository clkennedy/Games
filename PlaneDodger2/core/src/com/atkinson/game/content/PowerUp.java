/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.audio.Sound;


/**
 *
 * @author ARustedKnight
 */
public abstract class PowerUp extends BaseActor{
    
    private float lengthOfPowerUp = 5;
    private float timer = 0;
    protected boolean applied = false;
    protected Sound pop;
    public boolean popped = false;
    public static PowerUp powerUp= null;

    public PowerUp(float x, float y, Stage s) {
        super(x, y, s);
        
        
        //powerUp = this;
    }
    public PowerUp(float x, float y, Stage s, float length) {
        this(x, y, s);
        pop = Gdx.audio.newSound(Gdx.files.internal("bubblepop.ogg"));
        this.lengthOfPowerUp = length;
        
        //powerUp = this;
    }
    
    public void pop(){
        if(popped)return;
        this.setAnimation(Avatars.bubblePopAnim.getAnim());
        this.setSize(Avatars.bubblePopAnim.getWidth(), Avatars.bubblePopAnim.getHeight());
        this.pop.play(Options.soundVolume);
        popped = !popped;
    }
    public static void setPowerUp(PowerUp pu){
        if(PowerUp.powerUp != null){
            PowerUp.powerUp.end();
        }
        
        //PowerUp.powerUp = null;
        PowerUp.powerUp = pu;
        PowerUp.powerUp.apply();
    }
    
    public abstract void apply();
    public abstract void end();
    public abstract PowerUp create(float x, float y, Stage s);
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        if(powerUp != null){
            powerUp.setSpeed(0);
            powerUp.timer += dt;
            if(powerUp.timer > powerUp.lengthOfPowerUp){
               powerUp.end();
               if(powerUp != null){
                   powerUp = null;
               }
            }
        }
        
        if(this.isAnimationFinished()){
            this.setPosition(0, 0);
            this.setSpeed(0);
            this.setVisible(false);
        }
        
        if(this.getX() + this.getWidth() < 0){
            this.remove();
        }
    }
    
    
}
