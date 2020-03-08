package classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;
import enums.*;
import interfaces.IRobot;
import java.util.*;

public class Map {

    private final States states;

    private TiledMap map;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer wallLayer;
    private TiledMapTileLayer startPos;

    private List<Vector2> startPositions;

    private int width, height;

    public Map() {

        states = new States();

        map = new TmxMapLoader().load("fullboard.tmx");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        wallLayer = (TiledMapTileLayer) map.getLayers().get("Walls");
        startPos =  (TiledMapTileLayer) map.getLayers().get("startPos");

        MapProperties prop = map.getProperties();

        startPositions = findStart();

        width = prop.get("width", Integer.class);
        height = prop.get("height", Integer.class);
    }

    public boolean isHole(int x, int y, IRobot robot) {
        if (holeLayer.getCell(x, y) != null) {
            if (robot.getHp() > 0) {
                playerLayer.setCell(x, y, null);
                robot.takeDamage();
                robot.setPos(robot.getBp_x(), robot.getBp_y());
                playerLayer.setCell(robot.getBp_x(), robot.getBp_y(), robot.getState());
                robot.setDied(true);
            }
           else if (robot.getHp() == 0){
                System.out.println("You are dead!");
                Gdx.app.exit();
            }
           return true;
        }
        else
            return false;
    }

    public boolean isFlag(int x, int y, IRobot robot) {
        if (flagLayer.getCell(x,y) != null){
           robot.addFlag(flagLayer.getCell(x,y).getTile().getId(), flagLayer);
            if(robot.numbFlags() == 4){
                robot.setState(states.getWon());
                playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                System.out.println("You won!");
                Gdx.app.exit();
            }
           return true;
        }
        else if (flagLayer.getCell(x,y) == null){
            return false;
        }
        return false;
    }

    public boolean isOut(int x, int y, IRobot robot) {
        if (x > width-1) {
            System.out.println("You are dead!");
            Gdx.app.exit();
            return true;
        } else if (x < 0) {
            System.out.println("You are dead!");
            Gdx.app.exit();
            return true;
        } else if (y > height-1) {
            System.out.println("You are dead!");
            Gdx.app.exit();
            return true;
        } else if (y < 0) {
            System.out.println("You are dead!");
            Gdx.app.exit();
            return true;
        } else
            return false;
    }

    public void setPlayer(IRobot robot) {
        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
    }

    public boolean moveRobot(IRobot robot, Direction dir) {
        if(canGo(robot,dir)) {
            switch (dir) {
                case LEFT:
                    if (robot.getPosX() >= 0) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX() - 1, robot.getPosY());
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                case RIGHT:
                    if (robot.getPosX() < width) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX() + 1, robot.getPosY());
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                case UP:
                    if (robot.getPosY() < height) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX(), robot.getPosY() + 1);
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                case DOWN:
                    if (robot.getPosY() >= 0) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX(), robot.getPosY() - 1);
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        }else return false;
    }

    public TiledMap getMap() {
        return map;
    }

    public boolean canGo(IRobot robot, Direction dir) {
        TiledMapTileLayer.Cell cell = wallLayer.getCell(robot.getPosX(), robot.getPosY());
        TiledMapTileLayer.Cell northCell = wallLayer.getCell(robot.getPosX(), robot.getPosY() + 1);
        TiledMapTileLayer.Cell southCell = wallLayer.getCell(robot.getPosX(), robot.getPosY() - 1);
        TiledMapTileLayer.Cell eastCell = wallLayer.getCell(robot.getPosX() + 1, robot.getPosY());
        TiledMapTileLayer.Cell westCell = wallLayer.getCell(robot.getPosX() - 1, robot.getPosY());

        switch (dir) {
            case UP:
                if (northWall(cell) || southWall(northCell)) {
                    return false;
                }
                break;
            case DOWN:
                if (southWall(cell) || northWall(southCell)) {
                    return false;
                }
                break;
            case LEFT:
                if (westWall(cell) || eastWall(westCell)) {
                    return false;
                }
                break;
            case RIGHT:
                if (eastWall(cell) || westWall(eastCell)) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    //return true if cell has a wall on north side
    private boolean northWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == tileID.NORTH_WALL.getId() ||
                    cell.getTile().getId() == tileID.NORTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == tileID.NORTH_EAST_WALL.getId();
        }
        return false;
    }

    //return true if cell has a wall on south side
    private boolean southWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == tileID.SOUTH_WALL.getId() ||
                    cell.getTile().getId() == tileID.SOUTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == tileID.SOUTH_EAST_WALL.getId();
        }
        return false;
    }

    //returns true if cell has a wall on west side
    private boolean westWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == tileID.WEST_WALL.getId() ||
                    cell.getTile().getId() == tileID.NORTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == tileID.SOUTH_WEST_WALL.getId();
        }
        return false;
    }

    //return true if cell has a wall on east side
    private boolean eastWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == tileID.EAST_WALL.getId() ||
                    cell.getTile().getId() == tileID.NORTH_EAST_WALL.getId() ||
                    cell.getTile().getId() == tileID.SOUTH_EAST_WALL.getId();
        }
        return false;
    }

    public void check(int x, int y, IRobot robot){
        isFlag(x, y, robot);
        isOut(x, y, robot);
        isHole(x, y, robot);
    }

    //finds all the starting positions on the map
    private List<Vector2> findStart(){
        List<Vector2> list = new ArrayList<>();
        for(int i= 0; i<startPos.getWidth(); i++){
            for(int j = 0; j<startPos.getHeight();j++){
                if(startPos.getCell(i,j) != null)
                {
                    list.add(new Vector2(i,j));
                }
            }
        }
        return list;
    }

    public List<Vector2> getStartPositions(){
        return startPositions;
    }

    public List<IRobot>  placePlayers() {
        List<IRobot> list= new ArrayList<>();
        for (Vector2 pos : startPositions) {
            if (playerLayer.getCell((int) pos.x, (int) pos.y) == null) {
                IRobot r = new Robot(states.getNormal(),pos);
                playerLayer.setCell((int) pos.x, (int) pos.y, r.getState());
            }
        }
        return list;
    }
}