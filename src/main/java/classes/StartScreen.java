package classes;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import run.*;

public class StartScreen implements Screen {

    private final StartGame game;

    private Stage stage;

    private SpriteBatch paint;
    private SpriteBatch batch;

    private final Button gameButton;
    private final Button exitButton;

    private Texture startbtn = new Texture(Gdx.files.internal("startbtn.png"));

    private Texture endbtn = new Texture(Gdx.files.internal("endbtn.png"));

    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();

    public StartScreen(StartGame game) {

        this.game = game;
        this.paint = game.paint;
        stage = new Stage();
        batch = new SpriteBatch();

        gameButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("startbtn.png"))));
        gameButton.setPosition(width/2f - gameButton.getWidth()/2, height/2f);

        exitButton = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("endbtn.png"))));
        exitButton.setPosition(width/2f - exitButton.getWidth()/2, height/2f - exitButton.getWidth()/2);

        stage.addActor(gameButton);
        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(.3f, .3f, .3f, 1); //background color DARK GREY

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act(v);
        stage.draw();

        if(gameButton.isPressed()){
            System.out.println("den er trykket på");
        }

        if(exitButton.isPressed()) {
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
