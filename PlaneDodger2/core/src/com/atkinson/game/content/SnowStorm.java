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
public class SnowStorm extends BaseActor{
    
    private int numOfSnow = 200;
    public static int curSnow = 0;
    
    public SnowStorm(float x, float y, Stage s){
        super(x,y,s);
        curSnow = 0;
        for(int i = 0; i < numOfSnow; i ++){
            int sy = (int)(MathUtils.random((int)(Difficulty.worldHeight)) * Options.aspectRatio);
            int sx = (int)(MathUtils.random((int)(Difficulty.worldWidth)) * Options.aspectRatio);
            int mAngle = MathUtils.random(180, 270);

            new Snow(sx, sy, this.getStage()).setMotionAngle(mAngle);
            curSnow ++;
        }
        
    }
    
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        this.setZIndex(900);
        if(curSnow < numOfSnow){
            if(MathUtils.randomBoolean(.2f)){
                for(int i = curSnow; i < numOfSnow; i++){
                    if(MathUtils.randomBoolean(.5f)){
                        int y = Gdx.graphics.getHeight();
                        int x = MathUtils.random(Gdx.graphics.getWidth());
                        int mAngle = MathUtils.random(180+45, 270);

                        new Snow(x, y, this.getStage()).setMotionAngle(mAngle);
                        curSnow++;
                    }
                    else{
                        int y = MathUtils.random(Gdx.graphics.getHeight());
                        int x = Gdx.graphics.getWidth();
                        int mAngle = MathUtils.random(180, 180+45);

                        new Snow(x, y, this.getStage()).setMotionAngle(mAngle);
                        curSnow++;
                    }
                }
            }
            
        }
        
    }
}
