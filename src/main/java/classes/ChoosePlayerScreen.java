package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class ChoosePlayerScreen implements Screen{

    //the main game
    private final StartGame game;
    //the stage where we put the buttons
    private Stage stage;
    //the buttons in the menu
    private final Button logo;
    private int cooldown, cooldownTimer;
    private float r=.66f, g=.66f, b=1f;
    public SelectBox<String> selectMap;

    public final float BUTTON_WIDTH;
    public final float BUTTON_HEIGHT;
    public final float BUTTON_X;

    public ChoosePlayerScreen(StartGame game) {
        this.game = game;
        stage = new Stage();
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();


        BUTTON_WIDTH = (float) (Gdx.graphics.getWidth() * 0.25);
        BUTTON_HEIGHT = (float) (Gdx.graphics.getHeight() * 0.25);
        BUTTON_X = (float) (Gdx.graphics.getWidth() * 0.5 - BUTTON_WIDTH * 0.5);

        //creating buttons, and setting positons
        logo = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/logo.png"))));

        logo.setPosition(width /2f - logo.getWidth()/2, height*4 /6f);

        selectMap = new SelectBox<>(game.defaultSkin);

        selectMap.setItems("1","2","3","4");

        selectMap.setSelected("1");

        selectMap.setWidth(BUTTON_WIDTH * .87f);

        selectMap.setPosition(BUTTON_X - selectMap.getWidth(),  BUTTON_HEIGHT / 2);

        stage.addActor(selectMap);
        stage.addActor(logo);

        Gdx.input.setInputProcessor(stage);
        cooldown = 10;
        cooldownTimer = 0;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();

        if (cooldownTimer==0) {
            if (logo.isPressed()) {

                float temp = r;
                r = g;
                g = b;
                b = temp;
                cooldownTimer = cooldown;
            }
        }
        else
            cooldownTimer--;
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}

