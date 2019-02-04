/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
/**
 *
 * @author cameron.kennedy
 */
public class Ground extends BaseActor{
    
    public static float gHeight = 0;
    public static float count= 0;
    
    public Ground(float x, float y, Stage s){
        super(x,y,s);
        
        loadTexture("ground.png");
        setSize(getWidth() * Options.aspectRatio, getHeight() * Options.aspectRatio);
        setSpeed(Difficulty.LEVEL_SPEED*2  * Options.aspectRatio);
        setMotionAngle(180);
        setBoundaryRectangle();
        gHeight = getHeight();
        
        count = BaseActor.count(s, "com.atkinson.game.content.Ground");
        
    }
    public Ground(float x, float y, Stage s, String texture){
        super(x,y,s);
        
        loadTexture(texture);
        setSize(getWidth() * Options.aspectRatio, getHeight() * Options.aspectRatio);
        setSpeed(Difficulty.LEVEL_SPEED*2);
        setMotionAngle(180);
        setBoundaryRectangle();
        gHeight = getHeight();
        
        count = BaseActor.count(s, "com.atkinson.game.content.Ground");
        
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        setZIndex(10);
        if(getX() + getWidth() < 0){
            moveBy(Ground.count * getWidth(), 0);
        }
    }
    
}
