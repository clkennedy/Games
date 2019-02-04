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
public class OneUp extends PowerUp{
    private Plane _player;
    private float modifier = 3;
    private float defaultSpeed;
    BaseActor sheild = null;
    
    public OneUp(float x, float y, Stage s) {
        super(x, y, s, 1);
        
        for(BaseActor plane : BaseActor.getList(this.getStage(), "com.atkinson.game.content.Plane")){
            this._player = (Plane)plane;
        }
        loadTexture("oneuppowerup.png");
        setSize(50* Options.aspectRatio , 50* Options.aspectRatio);
        //centerAtActor(_player);
        setSpeed(Difficulty.POWERUP_SPEED * Options.aspectRatio);
        setMotionAngle(180);
        setBoundaryPolygon(8);
        Action pulse = Actions.sequence(Actions.scaleTo(1.2f, 1.2f, .5f), Actions.scaleTo(1, 1, .5f));
        addAction(Actions.forever(pulse));
    }

    @Override
    public void apply() {
        this.applied = true;
        
        _player.addLife();
    }

    @Override
    public void end() {
        this.applied = false;
        this.remove();
        PowerUp.powerUp = null;
    }
    @Override
    public void act(float dt){
        super.act(dt);
        
        if(this.overlaps(_player) && !this.applied){
            PowerUp.setPowerUp(this);
            this.pop();
        }
    }
    @Override
    public PowerUp create(float x, float y, Stage s) {
        return new OneUp(x, y, s);
    }
}
