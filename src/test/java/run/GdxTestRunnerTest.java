package run;

import classes.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import static org.junit.Assert.*;

//Simple test for checking the capability of the GdxTestRunner
@RunWith(GdxTestRunner.class)
public class GdxTestRunnerTest {


    @Test
    public void testTileMapInit() {
        TiledMap tileMap = new TmxMapLoader().load("testboard.tmx");
        TiledMapTileLayer board = (TiledMapTileLayer) tileMap.getLayers().get("board");
        TiledMapTileLayer holeLayer = (TiledMapTileLayer) tileMap.getLayers().get("Hole");
        assertEquals(5,  board.getCell(5,5).getTile().getId());
        assertEquals(91,  holeLayer.getCell(0,6).getTile().getId());
    }

    @Test
    public void testMapInit(){
        Map map = new Map();
        assertNotNull(map);
    }
}
