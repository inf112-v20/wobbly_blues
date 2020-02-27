package run;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * This is a class to see the difference between what we can do
 * with and without a headless application
 */
public class TestWithoutRunner {

    @Test
    public void testWithoutRunner() {
        //This should be a null pointer exception
        try {
            TiledMap tileMap = new TmxMapLoader().load("fullboard.tmx");
        } catch (NullPointerException exp){
            return;
        }
        fail("no reason to use TestRunner");
    }
}
