/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author ARustedKnight
 */
public class WallBlast extends PowerUp{

    private Plane _player;
    private float modifier = 3;
    private int collectedPoints;
    BaseActor sheildWall = null;
    
    public WallBlast(float x, float y, Stage s) {
        super(x, y, s, 5);
        
        for(BaseActor plane : BaseActor.getList(this.getStage(), "com.atkinson.game.content.Plane")){
            this._player = (Plane)plane;
        }
        loadTexture("wallblastpowerup.png");
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
        
        sheildWall = new BaseActor();
        sheildWall.loadTexture("wallblast.png");
        sheildWall.setSpeed(200);
        sheildWall.setMotionAngle(0);
        sheildWall.setOpacity(1);
        sheildWall.setSize(150* Options.aspectRatio, 450* Options.aspectRatio);
        sheildWall.setBoundaryPolygon(8);
        sheildWall.centerAtPosition(_player.getX(), Gdx.graphics.getHeight() /2);
        
        this.applied = true;
    }

    @Override
    public void end() {
        sheildWall.setVisible(false);
        LevelScreen.score += (int)(collectedPoints / 2);
        sheildWall.remove();
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
            sheildWall.applyPhysics(dt);
            //System.err.println(sheild.getWidth());
            if(sheildWall != null){
                for(BaseActor enemy: BaseActor.getList(this.getStage(), "com.atkinson.game.content.Enemy")){
                    if(sheildWall.overlaps(enemy)){
                        collectedPoints += Enemy.points;
                        ((Enemy)enemy).setToDestroy();
                     }
                 }
                if(sheildWall.getX() + (sheildWall.getWidth() / 2) > Gdx.graphics.getWidth()){
                    this.end();
                    sheildWall.remove();
                }
            }
        }
    }
    @Override
    public PowerUp create(float x, float y, Stage s) {
        return new WallBlast(x, y, s);
    }
}
