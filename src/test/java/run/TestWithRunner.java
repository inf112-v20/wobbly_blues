package run;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class TestWithRunner {


    @Test
    public void testTileMapInitiate() {
        TiledMap tileMap = new TmxMapLoader().load("assets/example.xml");
        TiledMapTileLayer board = (TiledMapTileLayer) tileMap.getLayers().get("Board");
        TiledMapTileLayer flagLayer = (TiledMapTileLayer) tileMap.getLayers().get("Flag");
        TiledMapTileLayer holeLayer = (TiledMapTileLayer) tileMap.getLayers().get("Hole");
        TiledMapTileLayer playerLayer = (TiledMapTileLayer) tileMap.getLayers().get("Player");
        assertEquals("checking that id corresponds", 5,  board.getCell(0,0).getTile().getId());
        assertEquals("checking that id corresponds", 6,  holeLayer.getCell(2,2).getTile().getId());
    }
}
