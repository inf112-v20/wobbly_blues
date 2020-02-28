package classes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import enums.Direction;

public class Map {

    private TiledMap map;
    private TiledMapTileLayer board;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer playerLayer;
    int width, height;

    public Map(){
        map = new TmxMapLoader().load("fullboard.tmx");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        MapProperties prop = map.getProperties();
        width = prop.get("width",Integer.class);
        height = prop.get("height",Integer.class);

    }

    public boolean isHole(int x, int y){
        return holeLayer.getCell(x,y) != null;
    }

    public boolean isFlag(int x, int y){
        return flagLayer.getCell(x,y) != null;
    }

    public void setPlayer(Robot robot){
        playerLayer.setCell(robot.getPosX(),robot.getPosY(),robot.getState());
    }

    public boolean moveRobot(Robot robot, Direction dir){
        switch (dir){
            case LEFT:
                if (robot.getPosX() > 0){
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX() - 1, robot.getPosY());
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            case RIGHT:
                if (robot.getPosX() < width - 1){
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX() + 1, robot.getPosY());
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            case UP:
                if (robot.getPosY() < height - 1){
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX(), robot.getPosY()+1);
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            case DOWN:
                if (robot.getPosY() > 0){
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX(), robot.getPosY()-1);
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public TiledMap getMap(){
        return map;
    }
}