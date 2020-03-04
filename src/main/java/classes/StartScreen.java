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
    private final Button startBtn;
    private final Button exitBtn;

    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();

    public StartScreen(StartGame game) {
        this.game = game;
        stage = new Stage();

        //creating buttons, and setting positons
        startBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("startbtn.png"))));
        startBtn.setPosition(width/2f - startBtn.getWidth()/2, height/2f);
        exitBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("endbtn.png"))));
        exitBtn.setPosition(width/2f - exitBtn.getWidth()/2, height/2f - exitBtn.getWidth()/2);

        stage.addActor(startBtn);
        stage.addActor(exitBtn);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
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
