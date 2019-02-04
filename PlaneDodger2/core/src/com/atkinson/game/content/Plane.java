/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import com.atkinson.game.engine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;

/**
 *
 * @author cameron.kennedy
 */
public class Plane extends BaseActor {
    
    private float rotationSpeed = 15f;
    private PlayerControls _playerControls;
    
    private boolean canShoot = true;
    private float reloadInterval = .5f;
    private float reloadTime = 0;
    
    private boolean _grounded = false;
    private boolean canTripleShot = false;
    
    private float boostHeight = 400;
    
    private Avatar curAvatar;
    
    private int lives = 3;
    private ArrayList<BaseActor> livesIcons;
    
    private Vector2 resetCoords;
    
    public Plane(float x, float y, Stage s){
        super(x,y,s);
        
        this._playerControls = new PlayerControls();
        
        _playerControls.setDefaultControls();
        _playerControls.removeMapping("Up");
        
        _playerControls.get("jump").addKeyboardMapping(Keys.W);
        _playerControls.get("jump").addKeyboardMapping(Keys.UP);
        
        _playerControls.get("fire").addKeyboardMapping(Keys.F);
        _playerControls.get("fire").addKeyboardMapping(Keys.SHIFT_RIGHT);
        
        resetCoords = new Vector2(x, y);
        
        setAnimation(Unlocks.currentAvatar.getAnim());
        setMaxSpeed(800);
        
        this.setSize(Unlocks.currentAvatar.getWidth() * Options.aspectRatio, Unlocks.currentAvatar.getHeight() * Options.aspectRatio);
        setBoundaryPolygon(8);
        setOrigin(getWidth() /2, getHeight() / 2);
        if(Unlocks.currentAvatar == Avatars.dragonAvatar){
            this.setSize(Unlocks.currentAvatar.getWidth() * Options.aspectRatio, (Unlocks.currentAvatar.getHeight() / 2)  * Options.aspectRatio);
            setBoundaryPolygon(8);
            for(int i = 0; i < 8; i ++){
                getBoundaryPolygon().getVertices()[2*i + 1] += Unlocks.currentAvatar.getHeight() / 4;
            }
            this.setSize(Unlocks.currentAvatar.getWidth() * Options.aspectRatio, Unlocks.currentAvatar.getHeight() * Options.aspectRatio);
        }
        
        this.setOrigin(this.getWidth() / 2, this.getHeight() /2);
        
        livesIcons = new ArrayList();
        BaseActor l;
        for(int i = 0; i <lives; i++){
            l = new BaseActor(0,0, this.getStage());
            //livesIcons[i].loadTexture("smallGreenPlane.png");
            Array<TextureRegion> textureArray = new Array<TextureRegion>();
            textureArray.add(Unlocks.currentAvatar.getAnim().getKeyFrame(0));
            Animation<TextureRegion> anim = new Animation<TextureRegion>(1, textureArray);
            l.setAnimation(anim);
            l.setZIndex(853);
            l.setSize(50 * Options.aspectRatio, 40 * Options.aspectRatio);
            l.centerAtPosition(((i + 1) * l.getWidth()) -(l.getWidth() / 2), Gdx.graphics.getHeight() - l.getHeight());
            livesIcons.add(l);
        }        
        
    }
    
    public void loseLife(){
        this.lives --;
        livesIcons.get(lives).remove();
        livesIcons.remove(lives);
        //reset();
        
    }
    
    public void addLife(){
        
            BaseActor l = new BaseActor(0,0, this.getStage());
            //livesIcons[i].loadTexture("smallGreenPlane.png");
            Array<TextureRegion> textureArray = new Array<TextureRegion>();
            textureArray.add(Unlocks.currentAvatar.getAnim().getKeyFrame(0));
            Animation<TextureRegion> anim = new Animation<TextureRegion>(1, textureArray);
            l.setAnimation(anim);
            l.setSize(50  * Options.aspectRatio, 40  * Options.aspectRatio);
            l.setZIndex(853);
            l.centerAtPosition(((livesIcons.size() + 1) * l.getWidth()) -(l.getWidth() / 2), Gdx.graphics.getHeight() - l.getHeight());
            livesIcons.add(l);
        
        this.lives ++;
        
    }
    
    public boolean dead(){
        return this.lives == 0;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha){
        
        super.draw(batch, parentAlpha);
        
        
    }
    
    @Override
    public void act(float dt){
        super.act(dt);
        
        
       setAcceleration(800 * Options.aspectRatio);
       accelerateAtAngle(270);
       int deadzone = 0; 
       
       if(this._grounded){
            setRotation(MathUtils.lerpAngleDeg(getRotation(), 0, dt *rotationSpeed));
        }
       else if(getMotionAngle() != 90 && getSpeed() - deadzone > 0){
            setRotation(MathUtils.lerpAngleDeg(getRotation(), -45, dt*rotationSpeed));
        }else if(getMotionAngle() == 90 && getSpeed() - deadzone > 0){
            setRotation(MathUtils.lerpAngleDeg(getRotation(), 45, dt *rotationSpeed));
        }
        
        //System.err.println();
        applyPhysics(dt);
        
        
        for(BaseActor ground: BaseActor.getList(this.getStage(), "com.atkinson.game.content.Ground")){
            if(this.overlaps(ground)){
                setSpeed(0);
                this.Grounded();
                preventOverlap(ground);
            }
        }
        
        
        
        if(getY() + getHeight() > getWorldBounds().height * Options.aspectRatio){
            setSpeed(0);
            boundToWorld();
        }
        
        if(_playerControls.isJustPressed("Jump")){
            boost();
        }
        shoot(dt);
        
        if(Unlocks.currentAvatar == Avatars.nyancatAvatar){
            new Rainbow(getX(), getY(), getStage());
            this.setZIndex(800);
        }
        for(BaseActor lifeDis: livesIcons){
            lifeDis.setZIndex(853);
        }
        
        if(this.getY() + this.getHeight() < 0){
            reset();
        }
        
        //System.out.println(getSpeed());
        
    }
    
    public void reset(){
        this.setX(resetCoords.x);
        this.setY(resetCoords.y);
        setSpeed(0);
    }
    
    public void setReloadSpeed(float interval){
        this.reloadInterval = interval;
    }
    public float getReloadSpeed(){
        return this.reloadInterval;
    }
    
    public void Grounded(){
        this._grounded = true;
    }
    
    public PlayerControls getPlayerControls(){
        return this._playerControls;
    }
    
    public void boost(){
        setSpeed(boostHeight * Options.aspectRatio);
        setMotionAngle(90);
        this._grounded = false;
        
    }
    
    public boolean canShoot(){
        return this.canShoot;
    }
    
    public boolean canTripleShot(boolean tshot){
        return this.canTripleShot = tshot;
    }
    
    public void shoot(float dt){
        if(!canShoot){
            reloadTime += dt;
        }
        
        if(_playerControls.isPressed("fire") && canShoot){
            canShoot = false;
            new Bullet(this.getX() +(this.getWidth()), this.getY() + (this.getHeight() / 2), this.getStage());
            if(canTripleShot){
                Bullet b1 = new Bullet(this.getX() +(this.getWidth()), this.getY() + (this.getHeight() / 2), this.getStage());
                Bullet b2 = new Bullet(this.getX() +(this.getWidth()), this.getY() + (this.getHeight() / 2), this.getStage());
                
                b1.setRotation(30);
                b2.setRotation(-30);
                b1.setMotionAngle(30);
                b2.setMotionAngle(-30);
            }
        }
        if(reloadTime > reloadInterval){
            reloadTime = 0;
            canShoot = true;
        }
        
    }
    
}
