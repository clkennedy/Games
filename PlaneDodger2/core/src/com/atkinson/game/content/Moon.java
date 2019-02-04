/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author ARustedKnight
 */
public class Moon extends BaseActor{
    public Moon(float x, float y, Stage s){
        super(x,y,s);
        
        if(MathUtils.randomBoolean(.7f)){
            loadTexture("crescentmoon.png");
        }
        else{
            loadTexture("fullmoon.png");
        }
        setBoundaryPolygon(8);
        setSize(50* Options.aspectRatio,50* Options.aspectRatio);
        setSpeed(1 * Options.aspectRatio);
        setPosition(Gdx.graphics.getWidth() - this.getWidth(), Gdx.graphics.getHeight() - this.getHeight() - MathUtils.random(Gdx.graphics.getHeight() /4 ));
        setMotionAngle(180);
    }
    
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        if(this.getX() + getWidth() < 0){
            this.remove();
        }
    }
    
}
