package inf112.skeleton.app;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private Sprite background;
    private Sprite player;
    private float w;
    private float h;

    @Override
    public void create() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        font = new BitmapFont();
        background = new Sprite( new Texture(Gdx.files.internal("Chasm.bmp")));
        player = new Sprite(( new Texture((Gdx.files.internal(("testRobot.bmp"))))));
        background.setSize(900,900);
        player.setSize(70,70);
        player.setPosition(0,0);
        font.setColor(Color.RED);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT )&& player.getX() > 0){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                player.translateX(-1f);
            else
                player.translateX(-10.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getX() < 830){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                player.translateX(1f);
            else
                player.translateX(10.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP )&& player.getY() < 830){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                player.translateY(1f);
            else
                player.translateY(10.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getY() > 0){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                player.translateY(-1f);
            else
                player.translateY(-10.0f);
        }

        batch.begin();
        background.draw(batch);
        player.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}