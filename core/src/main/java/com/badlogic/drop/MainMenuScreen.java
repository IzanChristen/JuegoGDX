package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MainMenuScreen implements Screen {
    final Drop game;
    Texture background;
    BitmapFont titleFont;
    BitmapFont instructionFont;

    public MainMenuScreen(final Drop game) {
        this.game = game;
        background = new Texture("background.png");

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.size = 175;
        param.color = Color.WHITE;
        titleFont = generator.generateFont(param);

        param.size = 125;
        instructionFont = generator.generateFont(param);

        generator.dispose();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 8, 5);
        game.batch.end();

        game.guiViewport.apply();
        game.batch.setProjectionMatrix(game.guiViewport.getCamera().combined);
        game.batch.begin();

        float screenHeight = game.guiViewport.getWorldHeight();
        titleFont.draw(game.batch, "Welcome to Drop Bomb!", 550, screenHeight - 350);
        instructionFont.draw(game.batch, "Tap anywhere to begin!", 600, screenHeight - 650);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
        game.guiViewport.update(width, height, true);
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        titleFont.dispose();
        instructionFont.dispose();
    }
}
