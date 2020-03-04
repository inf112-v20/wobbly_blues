package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import run.*;

public class StartScreen implements Screen {

    //the main game
    private final StartGame game;
    //the stage where we put the buttons
    private Stage stage;
    //the buttons in the menu
    private final Button startBtn, exitBtn;
    private Button logo;
    private int cooldown, cooldownTimer;
    private float r=.66f,g=.66f,b=1f;

    public StartScreen(StartGame game) {
        this.game = game;
        stage = new Stage();

        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();

        //creating buttons, and setting positons
        startBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("startbtn.png"))));
        exitBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("endbtn.png"))));
        logo = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("logo.png"))));

        startBtn.setPosition(width /2f - startBtn.getWidth()/2, height*2 /6f);
        exitBtn.setPosition(width /2f - exitBtn.getWidth()/2, height/6f);
        logo.setPosition(width /2f - logo.getWidth()/2, height*4 /6f);

        stage.addActor(startBtn);
        stage.addActor(exitBtn);
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

        //actions for the buttons
        if(startBtn.isPressed()){
            //if pressed sett a new screen
           game.setScreen(new BoardScreen(game));
        }
        if(exitBtn.isPressed()) {
            //if pressed close the app
            Gdx.app.exit();
        }
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
