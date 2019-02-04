/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author ARustedKnight
 */
public class Rainbow extends BaseActor{
    
    public Rainbow(float x, float y, Stage s){
        super(x, y, s);
        
        loadTexture("NyanCat\\rainbow.png");
        setSize(getWidth()* Options.aspectRatio, getHeight()* Options.aspectRatio);
        setSpeed(500 * Options.aspectRatio);
        setMotionAngle(180);
        
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        if(getX() + getWidth() < 0){
            remove();
        }
    }
    
}
