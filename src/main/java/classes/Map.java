package classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;
import enums.*;

import java.util.*;

public class Map {

    private TiledMap map;
    private TiledMapTileLayer flagLayer;
    private TiledMapTileLayer holeLayer;
    private TiledMapTileLayer playerLayer;
    private TiledMapTileLayer wallLayer;
    private TiledMapTileLayer startPos;

    private List<Vector2> startPositions;
    private List<Robot> playerList;

    private int width, height;

    public Map(String boardName){
        map = new TmxMapLoader().load(boardName);
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

    public Map() {
        this("fullboard.tmx");
    }

    public boolean isHole(int x, int y, Robot robot) {
        if (holeLayer.getCell(x, y) != null) {
            decreaseLife(x, y, robot);
            return true;
        }
        else
            return false;
    }

    private void decreaseLife(int x, int y, Robot robot) {
        if (robot.getHp() > 0) {
            playerLayer.setCell(x, y, null);
            robot.looseLife();
            robot.setPos(robot.getBp_x(), robot.getBp_y());
            playerLayer.setCell(robot.getBp_x(), robot.getBp_y(), robot.getState());
            robot.setDied(true);
        }
       if (robot.getHp() == 0){
            System.out.println("You are dead!");
            Gdx.app.exit();
        }
    }

    public boolean isFlag(int x, int y, Robot robot) {
        if (flagLayer.getCell(x,y) != null){
           robot.addFlag(flagLayer.getCell(x,y).getTile().getId(), flagLayer);
            if(robot.numbFlags() == 4){
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

    public boolean isOut(int x, int y, Robot robot) {
        if (x > width-1) {
            decreaseLife(x,y,robot);
            return true;
        } else if (x < 0) {
            decreaseLife(x,y,robot);
            return true;
        } else if (y > height-1) {
            decreaseLife(x,y,robot);
            return true;
        } else if (y < 0) {
            decreaseLife(x,y,robot);
            return true;
        } else
            return false;
    }

    public void setPlayer(Robot robot) {
        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
    }

    public Robot getRobot(int x, int y){
        for (Robot robot: playerList) {
            if (robot.getPosX() == x && robot.getPosY() == y){
                return robot;
            }
        }
        return null;
    }

    public Robot getRobot(int x, int y, Direction dir){
        switch (dir){
            case DOWN:
                return getRobot(x,y-1);
            case UP:
                return getRobot(x,y+1);
            case RIGHT:
                return getRobot(x+1,y);
            case LEFT:
                return getRobot(x-1,y);
        }
        return null;
    }

    //TODO: make so robot pusher other robots when "respawning"
    public boolean moveRobot(Robot robot, Direction dir) {

        if(canGo(robot,dir)) {
            if (getRobot(robot.getPosX(),robot.getPosY(),dir) != null){
                if (!moveRobot(getRobot(robot.getPosX(),robot.getPosY(),dir),dir)) return false;
            }
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

    public boolean canGo(Robot robot, Direction dir) {
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
            return cell.getTile().getId() == TileID.NORTH_WALL.getId() ||
                    cell.getTile().getId() == TileID.NORTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.NORTH_EAST_WALL.getId();
        }
        return false;
    }

    //return true if cell has a wall on south side
    private boolean southWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == TileID.SOUTH_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_EAST_WALL.getId();
        }
        return false;
    }

    //returns true if cell has a wall on west side
    private boolean westWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == TileID.WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.NORTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_WEST_WALL.getId();
        }
        return false;
    }

    //return true if cell has a wall on east side
    private boolean eastWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == TileID.EAST_WALL.getId() ||
                    cell.getTile().getId() == TileID.NORTH_EAST_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_EAST_WALL.getId();
        }
        return false;
    }

    public void check(int x, int y, Robot robot){
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


    public List<Robot> placePlayers(int numbPLayers) {
        if(numbPLayers >= 8) numbPLayers = 8;

        if(numbPLayers > startPositions.size()) numbPLayers=startPositions.size();


        playerList = new ArrayList<>();
        for (int i = 0; i < numbPLayers; i++) {
            Random rand = new Random();
            Vector2 pos = startPositions.get(rand.nextInt(startPositions.size()));

            if (playerLayer.getCell((int) pos.x, (int) pos.y) != null){
                pos = startPositions.get(rand.nextInt(startPositions.size()));
            }

            if (playerLayer.getCell((int) pos.x, (int) pos.y) == null) {
                Robot r = new Robot(pos);
                setPlayer(r);
                playerList.add(r);
                startPositions.remove(pos);
            }
        }
        return playerList;
    }

    public List<Robot> getPlayerList(){
        return playerList;
    }

    public int switchPlayer(Robot r) {
        if (r == null) {
            return 0;
        } else {
            int i = playerList.indexOf(r);
            i++;
            if (i >= playerList.size()) {
                i = 0;
            }
            return i;
        }
    }
}