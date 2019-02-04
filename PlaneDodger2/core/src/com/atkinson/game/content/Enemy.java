/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.atkinson.game.engine.PlayerControls;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author cameron.kennedy
 */
public class Enemy extends BaseActor{
    
    public static final int points = 1;
    
    public static float eHeight = 100;
    
    private boolean toDestroy = false;
    private Explosion e;
    
    public Enemy(float x, float y, Stage s) {
        super(x, y, s);
        
        setAnimation(Unlocks.currentEnemyAvatar.getAnim());
        setSize(Unlocks.currentEnemyAvatar.getWidth()* Options.aspectRatio, Unlocks.currentEnemyAvatar.getHeight()* Options.aspectRatio);
        setSpeed(100 * Options.aspectRatio);
        setMotionAngle(180);
        eHeight = getHeight();
        setBoundaryPolygon(8);
        setOrigin(getWidth() /2, getHeight() / 2);
        
        e = new Explosion(x, y, s);
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        if(toDestroy){
            this.Destroy();
        }
    }
    
    public void setToDestroy(){
        toDestroy = true;
    }
    
    public void Destroy(){
        
        e.setZIndex(851);
        e.play().centerAtActor(this);
        this.remove();
    }
    
}
