package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class SplashScreen implements Screen {

    final Drop game;
    Texture logo;
    BitmapFont font;
    float elapsedTime = 0;
    float splashDuration = 3;
    float alpha = 0;

    public SplashScreen(final Drop game) {
        this.game = game;

        font = new BitmapFont();

        try {
            logo = new Texture("bucket.png");
        } catch (Exception e) {
            logo = null;
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        elapsedTime += delta;

        if (elapsedTime < 1) {
            alpha = elapsedTime;
        } else if (elapsedTime > splashDuration - 1) {
            alpha = 1 - (elapsedTime - (splashDuration - 1));
        } else {
            alpha = 1;
        }

        alpha = Math.max(0, Math.min(1, alpha));

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();

        if (logo != null) {
            float logoWidth = 2;
            float logoHeight = 3;
            float logoX = (8 - logoWidth) / 2;
            float logoY = (5 - logoHeight) / 2;

            game.batch.setColor(1, 1, 1, alpha);
            game.batch.draw(logo, logoX, logoY, logoWidth, logoHeight);
            game.batch.setColor(1, 1, 1, 1);
        }
        game.batch.end();

        game.guiViewport.apply();
        game.batch.setProjectionMatrix(game.guiViewport.getCamera().combined);
        game.batch.begin();

        if (logo == null) {
            GlyphLayout layout = new GlyphLayout();
            layout.setText(font, "DROP GAME");

            float screenWidth = game.guiViewport.getWorldWidth();
            float screenHeight = game.guiViewport.getWorldHeight();

            float textX = (screenWidth - layout.width) / 2;
            float textY = (screenHeight + layout.height) / 2;

            game.batch.setColor(1, 1, 1, alpha);
            font.draw(game.batch, layout, textX, textY);
            game.batch.setColor(1, 1, 1, 1);
        }
        game.batch.end();

        if (elapsedTime >= splashDuration) {
            game.setScreen(new MainMenuScreen(game));
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
        if (logo != null) logo.dispose();
        font.dispose();
    }
}
