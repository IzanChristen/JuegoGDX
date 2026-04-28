package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Drop extends Game {
    public SpriteBatch batch;
    public FitViewport viewport;
    public ScreenViewport guiViewport;
    public Integer score;

    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        guiViewport = new ScreenViewport();
        score = 0;
        this.setScreen(new SplashScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
    }
}
