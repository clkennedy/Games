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
 * @author ARustedKnight
 */
public class Chicken extends BaseActor{
    
    final public static int points = 8;
    public static float cHeight = 100;
    
    public Chicken(float x, float y, Stage s){
        super(x,y,s);
        
        setAnimation(Avatars.chickenAvatar.getAnim());
        
        setSize(Avatars.chickenAvatar.getWidth()* Options.aspectRatio, Avatars.chickenAvatar.getHeight()* Options.aspectRatio);
        setBoundaryPolygon(8);
        setSpeed(Difficulty.CHICKEN_SPEED * Options.aspectRatio);
        setMotionAngle(180);
        cHeight = getHeight();
    }
    
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        if(this.getX() + getWidth() < 0){
            this.remove();
        }
    }
    
    public void Destroy(){
        new Splatter(0, 0, this.getStage()).play().centerAtActor(this);
        this.remove();
    }
}
