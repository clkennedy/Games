/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
/**
 *
 * @author cameron.kennedy
 */
public class Sky extends BaseActor{
    
    public static float count = 0;
    
    public Sky(float x, float y, Stage s){
        super(x,y,s);
        
        
        loadTexture("sky.png");
        
        
        setSize(getWidth() * Options.aspectRatio, getHeight() * Options.aspectRatio);
        //setScale(Options.aspectRatio);
        setSpeed(Difficulty.LEVEL_SPEED /4);
        setMotionAngle(180);
        
        count = BaseActor.count(s, "com.atkinson.game.content.Sky");
        
    }
    public Sky(float x, float y, Stage s, String texture){
        super(x,y,s);
        
        
        loadTexture(texture);
        
        //float aspectRatio = Gdx.graphics.getHeight() - getHeight();
        
        setSize(getWidth() * Options.aspectRatio, getHeight() * Options.aspectRatio);
        //setScale(Options.aspectRatio);
        setSpeed(Difficulty.LEVEL_SPEED /4);
        setMotionAngle(180);
        
        count = BaseActor.count(s, "com.atkinson.game.content.Sky");
    }
    
    public Sky clearTexture(){
        this.setAnimation(null);
        return this;
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        if(getX() + getWidth() < 0){
            moveBy(Sky.count * getWidth(), 0);
        }
    }
}
