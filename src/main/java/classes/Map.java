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
    private TiledMapTileLayer laserLineLayer;
    private TiledMapTileLayer boardLayer;

    private List<Vector2> startPositions;
    private List<Robot> playerList;
    private List<Vector2> flagPos;
    private List<Laser> laserPos;

    private int width, height;

    private int playerIdx;

    private BoardScreen board;

    private final TiledMapTileSet tileSet;

    public Map(String boardName){
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

        startPositions = findStart();
        flagPos = findFlags();
        laserPos = findLasers();

        width = prop.get("width", Integer.class);
        height = prop.get("height", Integer.class);
        playerIdx=0;

    }

    public Map() {
        this("friboard.tmx");
    }

    /**
     * Check if robot is on a hole and do appropriate action
     * @param x xPos of robot
     * @param y yPos of robot
     * @param robot robot in question
     * @return True if robot was on hole
     */
    public boolean isHole(int x, int y, Robot robot) {
        if (holeLayer.getCell(x, y) != null) {
            decreaseLife(x, y, robot);
            return true;
        }
        else
            return false;
    }

    /**
     * retrives the board to the map
     * @param board
     */
    public void getBoard(BoardScreen board){
        this.board = board;
    }

    /**
     * Reduce the hp of robot, respawn the robot at current base position
     * @param x xPos robot was destroyed
     * @param y yPos robot was destroyed
     * @param robot robot in question
     */

    private void decreaseLife(int x, int y, Robot robot) {
        if (robot.getHp() > 0) {
            playerLayer.setCell(x, y, null);
            robot.looseLife();
            robot.setPos(robot.getBp_x(), robot.getBp_y());
            playerLayer.setCell(robot.getBp_x(), robot.getBp_y(), robot.getState());
            robot.setDied(true);
        }
       if (robot.getHp() == 0){
           System.out.println("Robot "+robot.getName()+" Died!");
           robot.setDeadState();

        }
    }

    public boolean isOutside(Vector2 pos){
        if(pos.x > boardLayer.getWidth()) return false;
        if(pos.y > boardLayer.getHeight()) return false;
        if(pos.x < 0) return false;
        if(pos.y < 0) return false;
        return true;
    }

    /**
     * Check if robot is on a flag and do appropriate action
     * @param x robots xPos
     * @param y robots yPos
     * @param robot robot in question
     * @return True if robot was on flag
     */
    public boolean isFlag(int x, int y, Robot robot) {
        if (flagLayer.getCell(x,y) != null){
           robot.addFlag(flagLayer.getCell(x,y).getTile().getId());
            if(robot.numbFlags() == flagPos.size()){
                playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                System.out.println("Robot " + robot.getName()+ " has Won!");
                Gdx.app.exit();
            }
           return true;
        }
        else if (flagLayer.getCell(x,y) == null){
            return false;
        }
        return false;
    }

    /**
     * Check if robot is out of bounds and do appropriate action
     * @param x robots xPos
     * @param y robots yPos
     * @param robot robot in question
     * @return True if robot was out of bounds
     */
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

    /**
     * Add/set a robot to the player layer
     * @param robot robot in question
     */
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

    /**
     * Get the robot that lies 1 step in the given direction from (x,y) (if any)
     * @param x posX
     * @param y posY
     * @param dir direction
     * @return robot that lies 1 step in the given direction from (x,y)
     */
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

    public Vector2 getNeighbourPos(Vector2 pos, Direction dir) {
        Vector2 neighbourPos = new Vector2(pos);
        switch (dir) {
            case RIGHT:
                neighbourPos.x++;
                break;
            case LEFT:
                neighbourPos.x--;
                break;
            case UP:
                neighbourPos.y++;
                break;
            case DOWN:
                neighbourPos.y--;
                break;
            default:
                break;
        }
        return neighbourPos;
    }

    public boolean hasPlayer(Vector2 position) {
        for (Robot r : playerList) {
            Vector2 pos = new Vector2(r.getPosX(),r.getPosY());
            if (pos.equals(position)) {
                return true;
            }
        }
        return false;
    }

    public Robot getPlayer(Vector2 position) {
        for (Robot r : playerList) {
            Vector2 pos = new Vector2(r.getPosX(),r.getPosY());
            if (pos.equals(position)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Move a robot 1 step in the given direction
     * @param robot robot to move
     * @param dir direction to move robot
     * @return True if robot was moved
     */
    public boolean moveRobot(Robot robot, Direction dir) {
        //TODO: make so robot pushes other robots when "respawning"

        if(canGo(robot.getPosX(), robot.getPosY(), dir)) {
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

        }else
            return false;
    }

    public TiledMap getMap() {
        return map;
    }

    /**
     * Check if robot can move in given direction
     * from the x,y positon
     * @param dir direction to move robot
     * @return if robot can move 1 in the given direction (ie no walls)
     */
    public boolean canGo(int x, int y, Direction dir) {
        TiledMapTileLayer.Cell cell = wallLayer.getCell(x, y);
        TiledMapTileLayer.Cell northCell = wallLayer.getCell(x, y + 1);
        TiledMapTileLayer.Cell southCell = wallLayer.getCell(x, y - 1);
        TiledMapTileLayer.Cell eastCell = wallLayer.getCell(x + 1, y);
        TiledMapTileLayer.Cell westCell = wallLayer.getCell(x - 1, y);

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
                    cell.getTile().getId() == TileID.NORTH_EAST_WALL.getId()||
                    cell.getTile().getId() == TileID.NORTH_LASER_WALL.getId();
        }
        return false;
    }

    //return true if cell has a wall on south side
    private boolean southWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == TileID.SOUTH_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_EAST_WALL.getId()||
                    cell.getTile().getId() == TileID.SOUTH_LASER_WALL.getId();
        }
        return false;
    }

    //returns true if cell has a wall on west side
    private boolean westWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == TileID.WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.NORTH_WEST_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_WEST_WALL.getId()||
                    cell.getTile().getId() == TileID.WEST_LASER_WALL.getId();
        }
        return false;
    }

    //return true if cell has a wall on east side
    private boolean eastWall(TiledMapTileLayer.Cell cell) {
        if (cell != null) {
            return cell.getTile().getId() == TileID.EAST_WALL.getId() ||
                    cell.getTile().getId() == TileID.NORTH_EAST_WALL.getId() ||
                    cell.getTile().getId() == TileID.SOUTH_EAST_WALL.getId()||
                    cell.getTile().getId() == TileID.EAST_LASER_WALL.getId();
        }
        return false;
    }

    /**
     * Check if robot is on a flag, hole or out of bounds, and do appropriate actions
     * @param x posX of robot
     * @param y posY of robot
     * @param robot robot in question
     */
    public void check(int x, int y, Robot robot){
        isFlag(x, y, robot);
        isOut(x, y, robot);
        isHole(x, y, robot);

        if(isPlayersDead()){
            System.out.println("All players are dead");
            Gdx.app.exit();
        }
        if(robot.getState() == robot.getDeadState()){
            board.setPlayer();
            removePlayer(robot);
        }

    }

    public boolean isPlayer(Robot robot){
        return playerList.indexOf(robot) == playerIdx;
    }

    /**
     * Finds all the starting positions on the map
     * @return list of starting positions
     */
    private List<Vector2> findStart(){
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

    private List<Vector2> findFlags(){
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
     * Place up to 8 players (robots) on the map, max amount is dependant on amount of starting positions
     * @param numbPLayers desired number of players
     * @return the list of robots that was added
     */
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
                Robot r = new Robot(pos, RobotNames.getById(i));
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

    /**
     * return the index of the next robot to switch to
     * @param r current robot
     * @return index of next robot
     */
    public int switchPlayer(Robot r) {
        if (r == null) {
            return 0;
        } else {
            int i = playerList.indexOf(r);
            i++;
            if (i >= playerList.size()) {
                i = 0;
            }
            playerIdx=i;
            return i;
        }
    }

    public boolean isPlayersDead(){
        int i = 0;
        for (Robot r : playerList){
            if(r.getDeadState() == r.getState()) i++;
        }
        if(i==playerList.size()) return true;
        else return false;
    }

    public void removePlayer(Robot r){
        Robot prevRobot;
        if(playerIdx == 0) prevRobot = playerList.get(playerList.size()-1);
        else  prevRobot = playerList.get(playerIdx - 1);
        playerLayer.setCell(prevRobot.getBp_x(), prevRobot.getBp_y(), null);
        playerList.remove(prevRobot);

    }

    private List<Laser> findLasers() {
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
        for (Laser l:list) {
            System.out.println(l.getPos());
        }

        return list;
    }

    /**
     * Add laser in position in the right direction
     *
     * @param position  to add laser
     * @param direction of laser
     */

    public void addLaser(Vector2 position, Direction direction) {
        TiledMapTileLayer.Cell cell = laserLineLayer.getCell((int) position.x, (int) position.y);

        if (cell == null) cell = new TiledMapTileLayer.Cell();
        if (direction == Direction.UP || direction == Direction.DOWN) {
            if (cell.getTile() == null) {
                cell.setTile(tileSet.getTile(TileID.VERTICAL_LASER.getId()));
            } else if (cell.getTile().getId() == TileID.HORIZONTAL_LASER.getId()) {
                cell.setTile(tileSet.getTile(TileID.CROSSED_LASER.getId()));
            }
        } else {
            if (cell.getTile() == null) {
                cell.setTile(tileSet.getTile(TileID.HORIZONTAL_LASER.getId()));
            } else if (cell.getTile().getId() == TileID.VERTICAL_LASER.getId()) {
                cell.setTile(tileSet.getTile(TileID.CROSSED_LASER.getId()));
            }
        }
        laserLineLayer.setCell((int) position.x, (int) position.y, cell);
    }

    public void fireLaser(Vector2 pos, Direction dir) {
        addLaser(pos, dir);
        if(!isOutside(pos)) return;

        if (hasPlayer(pos)) {
            getPlayer(pos).takeDamage();
        } else if (canGo((int)pos.x, (int)pos.y, dir)) {
            fireLaser(getNeighbourPos(pos, dir), dir);
        }
    }

    public void playerLaser(Vector2 pos, Direction dir){
        addLaser(pos, dir);
        if(!isOutside(pos)) return;

        if (hasPlayer(getNeighbourPos(pos, dir))){
            getPlayer(getNeighbourPos(pos, dir)).takeDamage();
        } else if (canGo((int)pos.x, (int)pos.y, dir)){
            playerLaser(getNeighbourPos(pos, dir), dir);
        }
    }
}