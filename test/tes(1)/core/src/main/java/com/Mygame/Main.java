package com.Mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture playerTexture, groundTexture1, groundTexture2, backgroundTexture;
    private float groundSpeed = 200;
    private float ground1X;
    private float ground2X;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        playerTexture = new Texture(Gdx.files.internal("player.png"));
        groundTexture1 = new Texture(Gdx.files.internal("ground1.png"));
        groundTexture2 = new Texture(Gdx.files.internal("ground2.png"));
        groundTexture2.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);


        ground1X = 0;
        ground2X = groundTexture1.getWidth();

    }

    @Override
    public void render() {
//        clear scene
        ScreenUtils.clear(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();
        ground1X -= groundSpeed * deltaTime;
        ground2X -= groundSpeed * deltaTime;

        if (ground2X + groundTexture2.getWidth() < 0) {
            ground2X = ground1X + groundTexture1.getWidth();
        }




        batch.begin();
//        background
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        ground
//        batch.draw(groundTexture1, ground1X, 0);
        for (float x = ground1X; x < Gdx.graphics.getWidth(); x += groundTexture1.getWidth()) {
            batch.draw(groundTexture1, x, 0);
        }
//        batch.draw(groundTexture2, ground2X, 0);
//        player
        batch.draw(playerTexture, 50, groundTexture1.getHeight() );
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
        playerTexture.dispose();
        groundTexture1.dispose();
        groundTexture2.dispose();
    }
}
