package classes;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;
import enums.*;

import java.util.*;

public class MapLayers {
    protected final TiledMap map;
    protected final TiledMapTileLayer flagLayer;
    protected final TiledMapTileLayer holeLayer;
    protected TiledMapTileLayer playerLayer;
    protected final TiledMapTileLayer wallLayer;
    protected final TiledMapTileLayer startPos;
    protected TiledMapTileLayer laserLineLayer;
    protected final TiledMapTileLayer boardLayer;

    protected final int width, height;

    protected final  TiledMapTileSet tileSet;

    protected BoardScreen board;


    public MapLayers(String boardName){
        map = new TmxMapLoader().load(boardName);

        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        wallLayer = (TiledMapTileLayer) map.getLayers().get("Walls");
        startPos = (TiledMapTileLayer) map.getLayers().get("startPos");
        laserLineLayer = (TiledMapTileLayer) map.getLayers().get("LaserLines");
        boardLayer = (TiledMapTileLayer) map.getLayers().get("board");

        tileSet = map.getTileSets().getTileSet("tiles");

        MapProperties prop = map.getProperties();

        width = prop.get("width", Integer.class);
        height = prop.get("height", Integer.class);
    }

    public TiledMap getMap() {
        return map;
    }

    /**
     * retrives the board to the map
     * @param board
     */
    public void getBoard(BoardScreen board){
        this.board = board;
    }

    /**
     * Finds all the starting positions on the map
     * @return list of starting positions
     */
    protected List<Vector2> findStart(){
        List<Vector2> list = new ArrayList<>();
        for(int x= 0; x<startPos.getWidth(); x++){
            for(int y = 0; y<startPos.getHeight();y++){
                if(startPos.getCell(x,y) != null)
                {
                    list.add(new Vector2(x,y));
                }
            }
        }
        return list;
    }

    protected List<Vector2> findFlags(){
        List<Vector2> list = new ArrayList<>();
        for(int x= 0; x<flagLayer.getWidth(); x++){
            for(int y = 0; y<flagLayer.getHeight();y++){
                if(flagLayer.getCell(x,y) != null)
                {
                    list.add(new Vector2(x,y));
                }
            }
        }
        return list;
    }

    protected List<Laser> findLasers() {
        List<Laser> list = new ArrayList<>();
        for (int x = 0; x < wallLayer.getWidth(); x++) {
            for (int y = 0; y < wallLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = wallLayer.getCell(x, y);
                if (cell != null) {
                    int ID = cell.getTile().getId();
                    if (ID == TileID.EAST_LASER_WALL.getId()) {
                        list.add(new Laser(new Vector2(x, y), Direction.LEFT));
                    } else if (ID == TileID.WEST_LASER_WALL.getId()) {
                        list.add(new Laser(new Vector2(x, y), Direction.RIGHT));
                    } else if (ID == TileID.NORTH_LASER_WALL.getId()) {
                        list.add(new Laser(new Vector2(x, y), Direction.DOWN));
                    } else if (ID == TileID.SOUTH_LASER_WALL.getId()) {
                        list.add(new Laser(new Vector2(x, y), Direction.UP));
                    }
                }
            }
        }
        return list;
    }

    public boolean isOutside(Vector2 pos){
        if(pos.x > boardLayer.getWidth() || pos.y > boardLayer.getHeight() || pos.x < 0 || pos.y < 0) return false;
        return true;
    }


}
