package com.atkinson.game.desktop;

import com.atkinson.game.content.Difficulty;
import com.atkinson.game.content.Options;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.atkinson.game.content.PlaneDodger;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {
    public static void main (String[] args)
    {
        Game myGame = new PlaneDodger();
        
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

    cfg.title = "Plane Dodger";
    cfg.width = Difficulty.worldWidth;
    cfg.height = Difficulty.worldHeight;
    //cfg.fullscreen = true;
    //cfg.vSyncEnabled = true;
    
    //Options.desktopWidth = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
    //Options.desktopheight = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
        
        LwjglApplication launcher = new LwjglApplication( myGame, cfg);
    }
}
