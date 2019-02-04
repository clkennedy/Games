/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atkinson.game.content;

import static com.atkinson.game.content.MenuScreen.currentMenuIndex;
import com.atkinson.game.engine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
/**
 *
 * @author ARustedKnight
 */
public class InstructionScreen extends BaseScreen {
   
    static int currentMenuIndex = -1;
    BaseActor mainmenu;
    public void initialize()
    {
        float bw = 0;
        
        while(bw < Gdx.graphics.getWidth()){
            BaseActor bGround =new Sky(bw, 0, mainStage, "sky.png");
            bGround.setSpeed(0);
            bw+=bGround.getWidth();
        }
        
        
        Label fly = new Label( "Fly Upwards - Space Bar / W / Up Arrow", BaseGame.labelStyle);
        fly.setFontScale(.7f* Options.aspectRatio);
        fly.setColor(Color.FOREST);
        fly.setSize(fly.getWidth() / 2, fly.getHeight() /2);
        fly.setPosition(15* Options.aspectRatio, 550* Options.aspectRatio);
        mainStage.addActor(fly);
        
        Label fire = new Label( "Fire - Left Mouse Button / F / Right Shft", BaseGame.labelStyle);
        fire.setFontScale(.7f* Options.aspectRatio);
        fire.setColor(Color.FOREST);
        fire.setSize(fire.getWidth() / 2, fire.getHeight() /2);
        fire.setPosition(15* Options.aspectRatio, 500* Options.aspectRatio);
        mainStage.addActor(fire);
        
        Label ins = new Label( "Dodge Enemies - Collect Stars and Chickens", BaseGame.labelStyle);
        ins.setFontScale(.7f* Options.aspectRatio);
        ins.setColor(Color.FOREST);
        ins.setSize(ins.getWidth() / 2, ins.getHeight() /2);
        ins.setPosition(15* Options.aspectRatio, 450* Options.aspectRatio);
        mainStage.addActor(ins);
        
        Label pUps = new Label( "Power Ups", BaseGame.labelStyle);
        pUps.setFontScale(.7f* Options.aspectRatio);
        pUps.setColor(Color.FOREST);
        pUps.setSize(pUps.getWidth() / 2, pUps.getHeight() /2);
        pUps.setPosition(30* Options.aspectRatio, 400* Options.aspectRatio);
        mainStage.addActor(pUps);
        
        BaseActor powerup = new BaseActor(0, 0, mainStage);
        powerup.loadTexture("tripleshotpowerup.png");
        powerup.setSpeed(0);
        powerup.setSize(50* Options.aspectRatio,50* Options.aspectRatio);
        powerup.setOrigin(powerup.getWidth() / 2, powerup.getHeight() /2);
        powerup.setPosition(30* Options.aspectRatio, 345* Options.aspectRatio);
        
        Label pUpsDesc = new Label( " - Triple Shot", BaseGame.labelStyle);
        pUpsDesc.setFontScale(.5f* Options.aspectRatio);
        pUpsDesc.setColor(Color.FOREST);
        pUpsDesc.setSize(pUpsDesc.getWidth() / 2, pUpsDesc.getHeight() /2);
        pUpsDesc.setPosition(80* Options.aspectRatio, 350* Options.aspectRatio);
        mainStage.addActor(pUpsDesc);
        
        powerup = new BaseActor(0, 0, mainStage);
        powerup.loadTexture("resetpowerup.png");
        powerup.setSpeed(0);
        powerup.setSize(50* Options.aspectRatio,50* Options.aspectRatio);
        powerup.setOrigin(powerup.getWidth() / 2, powerup.getHeight() /2);
        powerup.setPosition(30* Options.aspectRatio, 295* Options.aspectRatio);
        
        pUpsDesc = new Label( " - Resets Enemies", BaseGame.labelStyle);
        pUpsDesc.setFontScale(.5f* Options.aspectRatio);
        pUpsDesc.setColor(Color.FOREST);
        pUpsDesc.setSize(pUpsDesc.getWidth() / 2, pUpsDesc.getHeight() /2);
        pUpsDesc.setPosition(80* Options.aspectRatio, 300* Options.aspectRatio);
        mainStage.addActor(pUpsDesc);
        
        powerup = new BaseActor(0, 0, mainStage);
        powerup.loadTexture("doublefirepowerup.png");
        powerup.setSpeed(0);
        powerup.setSize(50* Options.aspectRatio,50* Options.aspectRatio);
        powerup.setOrigin(powerup.getWidth() / 2, powerup.getHeight() /2);
        powerup.setPosition(30* Options.aspectRatio, 245* Options.aspectRatio);
        
        pUpsDesc = new Label( " - Increases Attack Speed", BaseGame.labelStyle);
        pUpsDesc.setFontScale(.5f* Options.aspectRatio);
        pUpsDesc.setColor(Color.FOREST);
        pUpsDesc.setSize(pUpsDesc.getWidth() / 2, pUpsDesc.getHeight() /2);
        pUpsDesc.setPosition(80* Options.aspectRatio, 250* Options.aspectRatio);
        mainStage.addActor(pUpsDesc);
        
        powerup = new BaseActor(0, 0, mainStage);
        powerup.loadTexture("sheildpowerup.png");
        powerup.setSpeed(0);
        powerup.setSize(50* Options.aspectRatio,50* Options.aspectRatio);
        powerup.setOrigin(powerup.getWidth() / 2, powerup.getHeight() /2);
        powerup.setPosition(30* Options.aspectRatio, 195* Options.aspectRatio);
        
        pUpsDesc = new Label( " - Summons a Protective Sheild", BaseGame.labelStyle);
        pUpsDesc.setFontScale(.5f* Options.aspectRatio);
        pUpsDesc.setColor(Color.FOREST);
        pUpsDesc.setSize(pUpsDesc.getWidth() / 2, pUpsDesc.getHeight() /2);
        pUpsDesc.setPosition(80* Options.aspectRatio, 200* Options.aspectRatio);
        mainStage.addActor(pUpsDesc);
        
        powerup = new BaseActor(0, 0, mainStage);
        powerup.loadTexture("wallblastpowerup.png");
        powerup.setSpeed(0);
        powerup.setSize(50* Options.aspectRatio,50* Options.aspectRatio);
        powerup.setOrigin(powerup.getWidth() / 2, powerup.getHeight() /2);
        powerup.setPosition(30* Options.aspectRatio, 145* Options.aspectRatio);
        
        pUpsDesc = new Label( " - Blast of Energy / Collects Half the Points", BaseGame.labelStyle);
        pUpsDesc.setFontScale(.5f* Options.aspectRatio);
        pUpsDesc.setColor(Color.FOREST);
        pUpsDesc.setSize(pUpsDesc.getWidth() / 2, pUpsDesc.getHeight() /2);
        pUpsDesc.setPosition(80* Options.aspectRatio, 150* Options.aspectRatio);
        mainStage.addActor(pUpsDesc);
        
        mainmenu = new BaseActor(0, 0, mainStage);
        mainmenu.loadTexture("mainmenu.png");
        mainmenu.setSize((mainmenu.getWidth() / 3)* Options.aspectRatio, (mainmenu.getHeight() /3)* Options.aspectRatio);
        mainmenu.setOriginX(mainmenu.getWidth() / 2);
        mainmenu.setOriginY(mainmenu.getHeight()/ 2);
        mainmenu.setBoundaryRectangle();
        mainmenu.getBoundaryPolygon();
        mainmenu.centerAtPosition(Gdx.graphics.getWidth() / 2,50* Options.aspectRatio);
        mainmenu.addListener(new Hover(){
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                event.getListenerActor().remove();
                BaseGame.setActiveScreen( new MenuScreen());
            }
        
        });
        
        
        //BaseActor start = new BaseActor(0,0, mainStage);
       // start.loadTexture( "message-start.png" );
        //start.centerAtPosition(400,300);
       // start.moveBy(0,-100);
    }
    
    public void update(float dt) {
    }
    
    @Override
    public boolean keyDown(int keyCode) {
      if(keyCode == Keys.ESCAPE){
          BaseGame.setActiveScreen(new MenuScreen());
      }
      if(keyCode == Keys.UP){
          descaleOption();
          currentMenuIndex--;
          currentMenuIndex = MathUtils.clamp(currentMenuIndex, -1, 0);
          scaleOption();
      }
      if(keyCode == Keys.DOWN){
          descaleOption();
          currentMenuIndex++;
          currentMenuIndex = MathUtils.clamp(currentMenuIndex, -1, 0);
          scaleOption();
      }
      if(keyCode == Keys.ENTER){
          if(currentMenuIndex == 0){
              BaseGame.setActiveScreen(new MenuScreen());
          }
      }
        return true;
    }
    private void descaleOption(){
        
        mainmenu.setScale(1f);
    }
    private void scaleOption(){
        if(currentMenuIndex == 0)
            mainmenu.setScale(1.2f);
    }
}
