package classes;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class StartGame extends Game {

    public Skin defaultSkin;
    @Override
    public void create() {
        this.defaultSkin = new Skin(Gdx.files.internal("uiskin.json"));
        //setting the screen
        this.setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}