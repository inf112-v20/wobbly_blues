package run;

import classes.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import testrunner.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TestMapInit {

    @Test
    public void testMapInit(){
        Map map = new Map();
    }

    @Test
    public void testMapObject(){
        Map map = new Map();
        TiledMap mapt = map.getMap();
        MapLayer lsr = mapt.getLayers().get("Laser");
        TiledMapTileLayer lyr = (TiledMapTileLayer) mapt.getLayers().get("Belt");
        TiledMapTileMapObject ts = (TiledMapTileMapObject) lsr.getObjects().get(0);
        System.out.println(lyr.getCell(5,5).getRotation());
    }
}
