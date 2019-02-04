/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author ARustedKnight
 */
public class Sheild extends PowerUp{

    private Plane _player;
    private float modifier = 3;
    private float defaultSpeed;
    BaseActor sheild = null;
    
    public Sheild(float x, float y, Stage s) {
        super(x, y, s, 10);
        
        for(BaseActor plane : BaseActor.getList(this.getStage(), "com.atkinson.game.content.Plane")){
            this._player = (Plane)plane;
        }
        loadTexture("sheildpowerup.png");
        setSize(50* Options.aspectRatio, 50* Options.aspectRatio);
        //centerAtActor(_player);
        setSpeed(Difficulty.POWERUP_SPEED * Options.aspectRatio);
        setMotionAngle(180);
        setBoundaryPolygon(8);
        Action pulse = Actions.sequence(Actions.scaleTo(1.2f, 1.2f, .5f), Actions.scaleTo(1, 1, .5f));
        addAction(Actions.forever(pulse));
    }

    @Override
    public void apply() {
        if(this.applied)return;
        
        sheild = new BaseActor();
        sheild.loadTexture("sheild.png");
        sheild.setOpacity(1);
        sheild.setSize(150* Options.aspectRatio, 150* Options.aspectRatio);
        
        sheild.setBoundaryPolygon(8);
        sheild.centerAtActor(_player);
        
        this.applied = true;
    }

    @Override
    public void end() {
        sheild.setVisible(false);
        sheild.remove();
        this.remove();
        this.applied = false;
        PowerUp.powerUp = null;
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        
        if(this.overlaps(_player) && !this.applied){
            PowerUp.setPowerUp(this);
            this.pop();
        }
        if(this.applied){
            sheild.centerAtActor(_player);
            //System.err.println(sheild.getWidth());
            if(sheild != null){
                sheild.setZIndex(50);
                for(BaseActor enemy: BaseActor.getList(this.getStage(), "com.atkinson.game.content.Enemy")){
                    if(sheild.overlaps(enemy)){
                        ((Enemy)enemy).setToDestroy();
                     }
                 }
            }
        }
    }

    @Override
    public PowerUp create(float x, float y, Stage s) {
        return new Sheild(x, y, s);
    }
    
}
