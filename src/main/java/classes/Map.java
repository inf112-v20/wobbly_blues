package Class;

import com.badlogic.gdx.maps.tiled.*;

public class Map {
    private static TiledMap map;
    public Map(){
        map = new TmxMapLoader().load("example.xml");
    }

    public static TiledMap getMap(){
        return map;
    }
}
