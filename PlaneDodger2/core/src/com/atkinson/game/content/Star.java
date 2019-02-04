/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import static com.atkinson.game.content.LevelScreen.score;
import static com.atkinson.game.content.LevelScreen.updateScore;
import com.atkinson.game.engine.BaseActor;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author cameron.kennedy
 */
public class Star extends BaseActor{
    
    private Plane _player;
    private Sparkle sp;
    public final static int points = 2;
    public static float sHeight = 100;
    
    public Star(float x, float y, Stage s) {
        super(x, y, s);
        
        setAnimation(Unlocks.currentCollectableAvatar.getAnim());
        setSize(Unlocks.currentCollectableAvatar.getWidth()* Options.aspectRatio, Unlocks.currentCollectableAvatar.getHeight()* Options.aspectRatio);
        setOrigin(getWidth() /2, getHeight() /2);
        Action pulse = Actions.sequence(Actions.scaleTo(1.2f, 1.2f, .5f), Actions.scaleTo(1, 1, .5f));
        
        sHeight = getHeight();
        
        addAction(Actions.forever(pulse));
        setSpeed(Difficulty.LEVEL_SPEED * Options.aspectRatio);
        setMotionAngle(180);
        
        setBoundaryPolygon(12);
        
        for(BaseActor plane : BaseActor.getList(this.getStage(), "com.atkinson.game.content.Plane")){
            this._player = (Plane)plane;
        }
        
        sp = new Sparkle(x,y, this.getStage());
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        applyPhysics(dt);
        
        if(_player.overlaps(this)){
               // Sparkle sp = new Sparkle(180, 180, this.getStage());
                sp.play().centerAtActor(this);
                
                LevelScreen.score += Star.points;
                this.remove();
                //this.addAction(Actions.removeActor(this));
                
                LevelScreen.updateScore();
            }
        
        //remove after moving past the screen
        if(getX() + getWidth() < 0){
            this.remove();
        }
    }
    
}
