package classes;

import classes.*;
import com.badlogic.gdx.Game;

public class StartGame extends Game {

    @Override
    public void create() {
        //setting the screen
        this.setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}