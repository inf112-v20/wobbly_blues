package run;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Test;

/**
 * This is a class to see the difference between what we can do
 * with and without a headless application
 */
public class TestWithoutRunner {

    @Test
    public void bestTestInHistory() {
        TiledMap tileMapp = new TmxMapLoader().load("fullboard.tmx");
    }
}
