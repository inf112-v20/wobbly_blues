package run;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestWithRunner {


    @Test
    public void testTileMapInitiate() {
        TiledMap tileMap = new TmxMapLoader().load("fullboard.tmx");
    }
}
