/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import static com.atkinson.game.content.SnowStorm.curSnow;
import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author ARustedKnight
 */
public class Snow extends BaseActor{
    
    public Snow(float x, float y, Stage s){
        super(x,y,s);
        
        
        loadTexture("snow.png");
        
        setBoundaryPolygon(8);
        setSize(8* Options.aspectRatio,8* Options.aspectRatio);
        setZIndex(1000);
        setSpeed(MathUtils.random(100, 300));
    }
    
    public void reset(){
        if(MathUtils.randomBoolean(.5f)){
            int y = Gdx.graphics.getHeight();
            int x = MathUtils.random(Gdx.graphics.getWidth());
            int mAngle = MathUtils.random(180+45, 270);
            this.setPosition(x, y);
            this.setMotionAngle(mAngle);
        }
        else{
            int y = MathUtils.random(Gdx.graphics.getHeight());
            int x = Gdx.graphics.getWidth();
            int mAngle = MathUtils.random(180, 180+45);
            this.setPosition(x, y);
            this.setMotionAngle(mAngle);
        }
    }
  
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        this.setZIndex(900);
        if(this.getX() + getWidth() < 0){
//            SnowStorm.curSnow--;
//            this.remove();
             reset();
        }
        if(this.getY() + getHeight()< 0){
//            SnowStorm.curSnow--;
//            this.remove();
            reset();
        }
    }
}
