package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import run.*;

public class StartScreen implements Screen {

    private final StartGame game;

    private Stage stage;

    private OrthographicCamera camera;
    private Button start;
    private TextButton end;
    private Skin skin;

    public StartScreen(StartGame game) {

        this.game = game;
        stage = new Stage();
        skin = new Skin();
        start = new TextButton("Start Game",skin);
        end = new TextButton("End Game",skin);
        start.setPosition(0,0);
        end.setPosition(200,200);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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

    }
}
