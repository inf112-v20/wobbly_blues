package run;

import classes.*;
import com.badlogic.gdx.*;

public class StartGame extends Game {

    @Override
    public void create() {
        //setting the screen
        this.setScreen(new StartScreen(this));

    }

    @Override
    public void dispose(){
    }

    @Override
    public void render() {
        super.render();
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