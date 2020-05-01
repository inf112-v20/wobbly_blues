package run;

import classes.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        cfg.title = "Robo-Rally";
        cfg.width = 800;
        cfg.height = 1000;
        Options.screenHeight = cfg.height;
        Options.screenWidth = cfg.width;

        new LwjglApplication(new StartGame(), cfg);
    }
}