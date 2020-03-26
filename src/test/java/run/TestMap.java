package run;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestMap {

    @Test
    public void testMapInit(){
        Map map = new Map();
    }

    @Test
    public void testMapObject(){
        Map map = new Map();
        TiledMap mapt = map.getMap();

    }
}
