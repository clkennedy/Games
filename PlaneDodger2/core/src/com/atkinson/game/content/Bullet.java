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
public class Bullet extends BaseActor{
    
    private static final int bulletSpeed = 300;
    
    public Bullet(float x, float y, Stage s) {
        super(x, y, s);
        
        setAnimation(Unlocks.currentBulletAvatar.getAnim());
        setSize(Unlocks.currentBulletAvatar.getWidth() * Options.aspectRatio, Unlocks.currentBulletAvatar.getHeight()* Options.aspectRatio);
        setOrigin(this.getWidth() / 2, this.getHeight() /2);
        setBoundaryPolygon(6);
        setSpeed(bulletSpeed  * Options.aspectRatio);
        setMotionAngle(0);
        
        Gdx.audio.newSound(Gdx.files.internal("gunshot.ogg")).play(Options.soundVolume);
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        for(BaseActor enemy : BaseActor.getList(this.getStage(), "com.atkinson.game.content.Enemy")){
            if(this.overlaps(enemy)){
                ((Enemy)enemy).Destroy();
                this.remove();
            }
        }
        for(BaseActor enemy : BaseActor.getList(this.getStage(), "com.atkinson.game.content.Chicken")){
            if(this.overlaps(enemy)){
                ((Chicken)enemy).Destroy();
                this.remove();
            }
        }
        if(this.getX() + this.getWidth() > Gdx.graphics.getWidth()){
            this.remove();
        }
    }
}
