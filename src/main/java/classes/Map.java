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
    private OrthogonalTiledMapRenderer TMrenderer;
    int width, height;

    public Map(){
        map = new TmxMapLoader().load("fullboard.tmx");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");

        TMrenderer = new OrthogonalTiledMapRenderer(map, (float) 0.00333);
        MapProperties prop = map.getProperties();
        width = prop.get("width",Integer.class);
        height = prop.get("height",Integer.class);
    }

    public void setCamera(OrthographicCamera cam){
        TMrenderer.setView(cam);
    }

    public boolean isHole(float x, float y){
        return isHole((int)x,(int)y);
    }

    public boolean isHole(int x, int y){
        return holeLayer.getCell(x,y) != null;
    }

    public boolean isFlag(float x, float y){
        return isFlag((int)x,(int)y);
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
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                    robot.setPos(robot.getPosX() - 1, robot.getPosY());
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            case RIGHT:
                if (robot.getPosX() < width){
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                    robot.setPos(robot.getPosX() + 1, robot.getPosY());
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            case UP:
                if (robot.getPosY() < height){
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                    robot.setPos(robot.getPosX(), robot.getPosY()+1);
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            case DOWN:
                if (robot.getPosX() > 0){
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), null);
                    robot.setPos(robot.getPosX(), robot.getPosY()-1);
                    playerLayer.setCell((int) robot.getPosX(), (int) robot.getPosY(), robot.getState());
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
    public void moveRobot(Robot robot){
        playerLayer.setCell(robot.getPosX(),robot.getPosY(),robot.getState());
    }

    public void render(){
        TMrenderer.render();
    }

    public void dispose(){
        TMrenderer.dispose();
    }

}