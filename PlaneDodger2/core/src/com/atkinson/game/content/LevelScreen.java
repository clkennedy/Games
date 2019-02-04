package com.atkinson.game.content;

import com.atkinson.game.engine.BaseScreen;
import com.atkinson.game.engine.BaseActor;
import com.atkinson.game.engine.BaseGame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LevelScreen extends BaseScreen {
    
    // Plane
    private Plane plane;
    
    private float starTimer;
    private float starSpawnInterval;
    private float starSpawnChance;
    public static int score;
    static Label scoreLabel;
    
    //enemy stuffs
    private float enemyTimer;
    public float enemySpawnInterval;
    public float enemySpeed;
    public float doubleEnemyChance;
    
    //chicken stuffs
    private float chickenTimer;
    public float chickenSpawnInterval;
    public float chickenSpeed;
    public float chickenSpawnChance;
    
     //powerup stuffs
    private float powerupSpawnInterval;
    private float powerupTimer;
    private float powerupSpeed;
    private float powerupChance;
    private boolean powerupSpawned;
    
    //points for living
    private float addPointsInterval = 15;
    private float addPointsTimer = 0;
    private int PointsToAdd = 3;
    
    private boolean gameOver;
    BaseActor gameOverMessage;
    
    Music backgroundMusic;
    Sound explosionSound;
    Sound sparkleSound;
    
    private boolean returnToMainScreen = false;
    
    

    public void initialize() {
        
        BaseActor.setMainStage(mainStage);
        Difficulty.passInLevelScreen(this);
        float w = 0;
        float gw = 0;
        if(MathUtils.randomBoolean(.7f)){
            Sky s1;
            Ground g1;
            do{
            s1 = new Sky(w, 0, mainStage);
            g1 = new Ground(gw, 0, mainStage);
            w+=s1.getWidth();
            gw+=g1.getWidth();
            }while(w - s1.getWidth() < Gdx.graphics.getWidth()  ||gw - g1.getWidth() < Gdx.graphics.getWidth() );
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Prelude-and-Action.mp3"));
        }
        else if (MathUtils.randomBoolean(.7f)){
            Sky s1;
            Ground g1;
            do{
            s1 = new Sky(w, 0, mainStage, "Starbasesnow.png");
            g1 = new Ground(gw, 0, mainStage, "groundnight.png");
            w+=s1.getWidth();
            gw+=g1.getWidth();
            }while(w - s1.getWidth() < Gdx.graphics.getWidth() ||gw - g1.getWidth() < Gdx.graphics.getWidth() );
            
            new Moon(0, 0, mainStage);
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("NightMusic.mp3"));
        }
        else{
            Sky s1;
            Ground g1;
            do{
            s1 = new Sky(w, 0, mainStage, "skyice.png");
            g1 = new Ground(gw, 0, mainStage, "groundice.png");
            w+=s1.getWidth();
            gw+=g1.getWidth();
            }while(w - s1.getWidth() < Gdx.graphics.getWidth() ||gw - g1.getWidth() < Gdx.graphics.getWidth() );
            
            new SnowStorm(0, 0, uiStage);
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Motivated.mp3"));
        }
        
        
        
        // Instantiate Plane and set world bounds
        
        BaseActor.setWorldBounds(800, 600);
         
        starSpawnInterval = 4;
        starTimer = MathUtils.random(starSpawnInterval);
        starSpawnChance = .9f;
        
        score = 0;
        scoreLabel = new Label(Integer.toString(score), BaseGame.labelStyle);
        uiTable.pad(10);
        uiTable.add(scoreLabel);
        uiTable.row();
        uiTable.add().expandY();
        
        
        //enemy stuffs
        enemySpawnInterval = Difficulty.ENEMY_INTERVAL * Options.aspectRatio;
        enemyTimer = MathUtils.random(enemySpawnInterval);
        enemySpeed = Difficulty.ENEMY_SPEED * Options.aspectRatio;
        doubleEnemyChance =.1f;
        
        //chicken stuffs
        chickenSpawnInterval = Difficulty.CHICKEN_INTERVAL;
        chickenTimer = MathUtils.random(chickenSpawnInterval);
        chickenSpeed = 50;
        chickenSpawnChance = .5f;
        
        
        //powerup stuffs
        powerupSpawnInterval = 10;
        powerupTimer = MathUtils.random(powerupSpawnInterval);
        powerupSpeed = 200;
        powerupChance = .6f;
        powerupSpawned = false;
        
        gameOver = false;
        
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(Options.musicVolume);
        backgroundMusic.play();
        sparkleSound = Gdx.audio.newSound(Gdx.files.internal("sparkle.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
        
        plane = new Plane(100, 500, mainStage);
        
        Difficulty.newGame();
    }
	
    public void update(float dt) {
        
       
        
        if(plane.getPlayerControls().isJustPressed("menu")){
            Progress.Save();
            backgroundMusic.stop();
            BaseGame.setActiveScreen(new MenuScreen());
        }
        
        
        if(gameOver){
            return;
        }
        
        if(!powerupSpawned){
            powerupTimer += dt;
            if(powerupTimer > powerupSpawnInterval){
                powerupTimer = 0;
                if(MathUtils.randomBoolean(powerupChance)){
                    switch(MathUtils.random(5)){
                        case 0:
                            new DoubleFire(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                        case 1:
                            new Sheild(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                        case 2:
                            new WallBlast(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                        case 3:
                            new Reset(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                        case 4:
                            new TripleShot(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                        case 5:
                            new OneUp(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                        default:
                            new OneUp(Gdx.graphics.getWidth(), 
                                    MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - 50) + Ground.gHeight, mainStage);
                        break;
                    }
                    
                }
            }
        }
        
        //star spawning
        starTimer += dt;
        if(starTimer > starSpawnInterval){
            starTimer = 0;
            if(MathUtils.randomBoolean(starSpawnChance)){
                Star s = new Star(Gdx.graphics.getWidth(), MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - Star.sHeight) + Ground.gHeight, mainStage);
                if(MathUtils.randomBoolean(.3f)){
                    s.setSpeed(s.getSpeed() * 2f);
                }
            }
            if(MathUtils.randomBoolean(.3f)){
                starTimer += starSpawnInterval /2;
            }
            
        }
        
        chickenTimer += dt;
        if(chickenTimer > chickenSpawnInterval){
            chickenTimer = 0;
            if(MathUtils.randomBoolean(chickenSpawnChance)){
                Chicken chick = new Chicken(Gdx.graphics.getWidth(), MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - Chicken.cHeight) + Ground.gHeight, mainStage);
                if(MathUtils.randomBoolean(.2f)){
                    chick.setSpeed(chick.getSpeed() * 1.5f);
                }
            }
            if(MathUtils.randomBoolean(.3f)){
                chickenTimer += chickenSpawnInterval / (MathUtils.random(2) +2);
            }
        }
        
        addPointsTimer += dt;
        if(addPointsTimer > addPointsInterval){
            addPointsTimer = 0;
            score += PointsToAdd;
            updateScore();
        }
        
        //enemy stuff
        enemyTimer += dt;
        if(enemyTimer > enemySpawnInterval){
            enemyTimer = 0;
            enemySpawnInterval -= .1f;
            enemySpeed += 10;
            new Enemy(Gdx.graphics.getWidth(), MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - Enemy.eHeight) + Ground.gHeight, mainStage)
                    .setSpeed(enemySpeed  * Options.aspectRatio);
            if(MathUtils.randomBoolean(doubleEnemyChance)){
                if(MathUtils.randomBoolean(.5f)){
                    new Enemy(Gdx.graphics.getWidth(), MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - Enemy.eHeight) + Ground.gHeight, mainStage)
                    .setSpeed(enemySpeed  * Options.aspectRatio);
                }
                else{
                    new Enemy(Gdx.graphics.getWidth(), MathUtils.random(Gdx.graphics.getHeight() - Ground.gHeight - Enemy.eHeight) + Ground.gHeight, mainStage)
                    .setSpeed(enemySpeed  * Options.aspectRatio /1.5f);
                }
            }
            if(MathUtils.randomBoolean(.05f)){
                doubleEnemyChance += .02f;
                if(doubleEnemyChance > .5f){
                    doubleEnemyChance =.5f;
                }
            }
            if(enemySpawnInterval < .6f){
                enemySpawnInterval = .6f;
            }
            if(enemySpeed > 375){
                enemySpeed = 375;
            }
        }
        
        for(BaseActor enemy: BaseActor.getList(mainStage, "com.atkinson.game.content.Enemy")){
            if(plane.overlaps(enemy)){
                plane.loseLife();
                if(plane.dead()){
                    gameOver = true;
                    gameOverMessage = new BaseActor(0, 0, uiStage);
                    gameOverMessage.loadTexture("game-over.png");
                    gameOverMessage.centerAtPosition(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/2);
                    backgroundMusic.stop();
                    plane.remove();
                    
                    BaseActor mainmenu = new BaseActor(0, 0, mainStage);
                    mainmenu.loadTexture("escMainMenu.png");
                    mainmenu.setSize(mainmenu.getWidth(), mainmenu.getHeight());
                    mainmenu.setOriginX(mainmenu.getWidth() / 2);
                    mainmenu.setOriginY(mainmenu.getHeight()/ 2);
                    mainmenu.setBoundaryRectangle();
                    mainmenu.getBoundaryPolygon();
                    mainmenu.setOpacity(0);
                    mainmenu.centerAtPosition(Gdx.graphics.getWidth() / 2,50);
                    mainmenu.addAction(Actions.delay(3));
                    mainmenu.addAction(Actions.after(Actions.fadeIn(1)));
                    
                    java.util.ArrayList<BaseActor> unlocked = Unlocks.Unlock(score);
                    Progress.Save();
                    if(unlocked != null && !unlocked.isEmpty()){
                        if(gameOverMessage != null){
                            gameOverMessage.addAction(Actions.delay(1));
                            gameOverMessage.addAction(Actions.after(Actions.fadeOut(1)));
                        }
                        
                        BaseActor unlockText = new BaseActor();
                        unlockText.loadTexture("unlocked.png");
                        unlockText.centerAtPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() -100);
                        unlockText.setOpacity(0);
                        unlockText.addAction(Actions.delay(2));
                        unlockText.addAction(Actions.after(Actions.fadeIn(1)));
                        float w = 0;
                        for(BaseActor bActor : unlocked){
                            w += bActor.getWidth();
                        }
                        float halfW = w / 2;
                        for(BaseActor bActor : unlocked){
                            bActor.setSize(50, 50);
                            bActor.centerAtPosition((Gdx.graphics.getWidth() /2) - halfW + (bActor.getWidth() /2), Gdx.graphics.getHeight() - 200);
                            bActor.setOpacity(0);
                            bActor.addAction(Actions.delay(2));
                            bActor.addAction(Actions.after(Actions.fadeIn(1)));
                            halfW -= bActor.getWidth();
                        }
                        
                    }
                }
                else{
                    Explosion planeEx = new Explosion(0, 0, mainStage);
                    planeEx.centerAtActor(plane);
                    planeEx.setScale(3);
                    planeEx.setZIndex(850);
                    planeEx.play();
                }
                ((Enemy)enemy).Destroy();
            }
            
            if(enemy.getX() + enemy.getWidth() < 0){
                score += Enemy.points;
                updateScore();
                enemy.remove();
            }
        }
        for(BaseActor chicken: BaseActor.getList(mainStage, "com.atkinson.game.content.Chicken")){
            if(plane.overlaps(chicken)){
                score+= Chicken.points;
                ((Chicken)chicken).Destroy();
            }    
        }
        
    }
    
    public static void updateScore(){
        scoreLabel.setText(Integer.toString(score));
    }
    
    public boolean keyDown(int keyCode) {
//        if(keyCode == Keys.SPACE)
//            plane.boost();
        return true;
    }
    
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /*if(button == Input.Buttons.LEFT)
            plane.boost();*/
        return true;
    }
}
