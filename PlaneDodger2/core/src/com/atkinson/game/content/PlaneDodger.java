package com.atkinson.game.content;

import com.atkinson.game.engine.BaseActor;
import com.atkinson.game.engine.BaseGame;

public class PlaneDodger extends BaseGame {

    public void create()
    {
        super.create();
        //Progress.Load();
        setActiveScreen( new LoadScreen() );
    }
}
