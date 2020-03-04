package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.*;
import enums.*;

public class Map {

    private TiledMap map;
    private TiledMapTileLayer board;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer wallLayer;
    int width, height;

    public Map() {

        map = new TmxMapLoader().load("fullboard.tmx");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        wallLayer = (TiledMapTileLayer) map.getLayers().get("Walls");

        MapProperties prop = map.getProperties();
        width = prop.get("width", Integer.class);
        height = prop.get("height", Integer.class);

    }

    public boolean isHole(int x, int y, Robot robot) {
        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
        return holeLayer.getCell(x, y) != null;
    }

    public boolean isFlag(int x, int y, Robot robot) {
        if (flagLayer.getCell(x,y) != null){
            if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG1.getId()){
                if (!robot.hasFlag(tileID.FLAG1.getId()) && !robot.hasFlag(tileID.FLAG2.getId()) && !robot.hasFlag(tileID.FLAG3.getId()) && !robot.hasFlag(tileID.FLAG4.getId())){
                    robot.addFlag(tileID.FLAG1.getId());
                }
            }
            else if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG2.getId()){
                if (robot.hasFlag(tileID.FLAG1.getId()) && !robot.hasFlag(tileID.FLAG2.getId()) && !robot.hasFlag(tileID.FLAG3.getId()) && !robot.hasFlag(tileID.FLAG4.getId())){
                    robot.addFlag(tileID.FLAG2.getId());
                }
            }
            else if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG3.getId()){
                if (robot.hasFlag(tileID.FLAG1.getId()) && robot.hasFlag(tileID.FLAG2.getId()) && !robot.hasFlag(tileID.FLAG3.getId()) && !robot.hasFlag(tileID.FLAG4.getId())){
                    robot.addFlag(tileID.FLAG3.getId());
                }
            }
            else if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG4.getId()) {
                if (robot.hasFlag(tileID.FLAG1.getId()) && robot.hasFlag(tileID.FLAG2.getId()) && robot.hasFlag(tileID.FLAG3.getId()) && !robot.hasFlag(tileID.FLAG4.getId())) {
                    robot.addFlag(tileID.FLAG4.getId());
                }
            }
        }
        if(robot.numbFlags() == 4){
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
            return true;
        }
        else{
            return false;
        }

    }

    public boolean isOut(int x, int y, Robot robot) {
        if (x > width) {
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
            robot.setState(null);
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
            return true;
        } else if (x < 0) {
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
            robot.setState(null);
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
            return true;
        } else if (y > height) {
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
            robot.setState(null);
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
            return true;
        } else if (y < 0) {
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
            robot.setState(null);
            playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
            return true;
        } else return false;
    }

    public void setPlayer(Robot robot) {
        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
    }

    public boolean moveRobot(Robot robot, Direction dir) {
        switch (dir) {
            case LEFT:
                if (robot.getPosX() >= 0) {
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX() - 1, robot.getPosY());
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                    return true;
                }
                return false;
            case RIGHT:
                if (robot.getPosX() < width) {
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX() + 1, robot.getPosY());
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                    return true;
                }
                return false;
            case UP:
                if (robot.getPosY() < height) {
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX(), robot.getPosY() + 1);
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                    return true;
                }
                return false;
            case DOWN:
                if (robot.getPosY() >= 0) {
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                    robot.setPos(robot.getPosX(), robot.getPosY() - 1);
                    playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public TiledMap getMap() {
        return map;
    }

    public boolean canGo(Robot robot) {
        return false;
    }
}