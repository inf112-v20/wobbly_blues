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
    protected final TiledMapTileLayer conveyorLayer;
    protected final TiledMapTileLayer toolLayer;
    protected final TiledMapTileLayer rotorLayer;
    protected final TiledMapTileLayer pusherLayer;

    protected final int width, height;

    protected final  TiledMapTileSet tileSet;

    protected BoardScreen board;


    /**
     * creates the map with the layers on the board.
     * @param boardName
     */
    public MapLayers(String boardName){
        map = new TmxMapLoader().load(boardName);

        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        wallLayer = (TiledMapTileLayer) map.getLayers().get("Walls");
        startPos = (TiledMapTileLayer) map.getLayers().get("startPos");
        laserLineLayer = (TiledMapTileLayer) map.getLayers().get("LaserLines");
        boardLayer = (TiledMapTileLayer) map.getLayers().get("board");
        conveyorLayer = (TiledMapTileLayer) map.getLayers().get("Belts");
        toolLayer = (TiledMapTileLayer) map.getLayers().get("Tools");
        rotorLayer = (TiledMapTileLayer) map.getLayers().get("Rotors");
        pusherLayer = (TiledMapTileLayer) map.getLayers().get("Pusher");

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

    /**
     * finds all the flags in the map
     * @return a list of the flag positions.
     */
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

    /**
     * finds all the lasers in the map
     * @return a list of the lasers.
     */
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
    /**
     * finds all the belts in the map
     * @return a list of the belts.
     */
    protected List<Belt> findBelts(){
        List<Belt> list = new ArrayList<>();
        for (int x = 0; x < conveyorLayer.getWidth(); x++) {
            for (int y = 0; y < conveyorLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = conveyorLayer.getCell(x, y);
                if (cell != null) {
                    list.add(new Belt(new Vector2(x,y), cell.getTile().getId()));
                }
            }
        }
        return list;
    }
    /**
     * finds all the rotor pads in the map
     * @return a list of the rotor pads.
     */
    protected List<RotorPad> findRotors(){
        List<RotorPad> list = new ArrayList<>();
        for (int x = 0; x < rotorLayer.getWidth(); x++) {
            for (int y = 0; y < rotorLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = rotorLayer.getCell(x, y);
                if (cell != null) {
                    list.add(new RotorPad(new Vector2(x,y),cell.getTile().getId()));
                }
            }
        }
        return list;
    }
    /**
     * finds all the tools in the map
     * @return a list of the tools.
     */
    protected List<Tool> findTools(){
        List<Tool> list = new ArrayList<>();
        for (int x = 0; x < toolLayer.getWidth(); x++) {
            for (int y = 0; y < toolLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = toolLayer.getCell(x, y);
                if (cell != null) {
                    list.add(new Tool(new Vector2(x,y), cell.getTile().getId()));
                }
            }
        }
        return list;
    }
    /**
     * finds all the tools in the map
     * @return a list of the tools.
     */
    protected List<Tool> findPusher(){
        List<Tool> list = new ArrayList<>();
        for (int x = 0; x < pusherLayer.getWidth(); x++) {
            for (int y = 0; y < pusherLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = pusherLayer.getCell(x, y);
                if (cell != null) {
                    list.add(new Tool(new Vector2(x,y), cell.getTile().getId()));
                }
            }
        }
        return list;
    }

    /**
     * checkes if the position is outside the map.
     * @param pos
     * @return true or false.
     */
    public boolean isOutside(Vector2 pos){
        if(pos.x > boardLayer.getWidth() || pos.y > boardLayer.getHeight() || pos.x < 0 || pos.y < 0) return false;
        return true;
    }


}
