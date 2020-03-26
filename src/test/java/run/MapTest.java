package run;

import classes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class MapTest {

    Map map;

    @Before
    public void resetEnv(){
        map = new Map("testboard.tmx");
    }

}
