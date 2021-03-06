package classes;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StartScreen implements Screen {

    //the main game
    private final StartGame game;
    //the stage where we put the buttons
    private Stage stage;
    //the buttons in the menu
    private final Button startBtn, exitBtn, logo;
    private int cooldown, cooldownTimer;
    private float r=.66f, g=.66f, b=1f;


    /**
     * This contains the main game and gives you the option to either quit or start the game.
     * @param game
     */
    public StartScreen(StartGame game) {
        this.game = game;
        stage = new Stage();

        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();

        //creating buttons, and setting positons
        startBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/startbtn.png"))));
        exitBtn = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/endbtn.png"))));
        logo = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/logo.png"))));

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
            //if pressed send you to the screen where you choose number of players and AI
           game.setScreen(new ChoosePlayerScreen(game));
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
