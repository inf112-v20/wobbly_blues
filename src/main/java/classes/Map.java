package classes;


import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;
import enums.*;

import java.util.*;

public class Map extends MapLayers{

    protected final List<Vector2> startPositions;
    protected List<Robot> playerList;
    protected List<Robot> AIList;
    protected final List<Vector2> flagPos;
    protected final List<Laser> laserPos;
    protected final List<Belt> beltList;
    protected final List<Tool> tools;
    protected final List<RotorPad> rotorPos;
    protected final List<Pusher> pushers;
    protected List<RobotNames> robotNamesList;

    protected int playerIdx;

    /**
     * loads the map with the correct boardName
     * @param boardName
     */
    public Map(String boardName){
        super(boardName);

        GameLogic.setMap(this);

        startPositions = findStart();
        flagPos = findFlags();
        laserPos = findLasers();
        playerIdx=0;
        beltList = findBelts();
        tools = findTools();
        rotorPos = findRotors();
        pushers = findPusher();

    }

    //loads a standard board.
    public Map() {
        this("friboard.tmx");
    }


    /**
     * Add/set a robot to the player layer
     * @param robot robot in question
     */
    public void setPlayer(Robot robot) {
        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
    }

    public Robot getRobot(int x, int y){
        for (Robot robot: getListOfPlayers()) {
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
    public Robot getNeighbourRobot(int x, int y, Direction dir){
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

    /**
     * gets the position in the direction asked.
     * @param pos
     * @param dir
     * @return
     */
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

    /**
     * checks if there is a player in that position.
     * @param position
     * @return
     */
    public boolean hasPlayer(Vector2 position) {
        for (Robot r : getListOfPlayers()) {
            Vector2 pos = new Vector2(r.getPosX(),r.getPosY());
            if (pos.equals(position)) {
                return true;
            }
        }
        return false;
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
            if (getNeighbourRobot(robot.getPosX(),robot.getPosY(),dir) != null){
                if (!moveRobot(getNeighbourRobot(robot.getPosX(),robot.getPosY(),dir),dir)) return false;
            }
            switch (dir) {
                case LEFT:
                    if (robot.getPosX() >= 0) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX() - 1, robot.getPosY());
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        GameLogic.check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                case RIGHT:
                    if (robot.getPosX() < width) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX() + 1, robot.getPosY());
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        GameLogic.check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                case UP:
                    if (robot.getPosY() < height) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX(), robot.getPosY() + 1);
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        GameLogic.check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                case DOWN:
                    if (robot.getPosY() >= 0) {
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), null);
                        robot.setPos(robot.getPosX(), robot.getPosY() - 1);
                        playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());

                        GameLogic.check(robot.getPosX(), robot.getPosY(), robot);

                        return true;
                    }
                    return false;
                default:
                    return false;
            }

        }else
            return false;
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

    public boolean isPlayer(Robot robot){
        return playerList.indexOf(robot) == playerIdx;
    }

    /**
     * Place up to 8 players (robots) on the map, max amount is dependant on amount of starting positions
     * @param numbPLayers desired number of players
     * @return the list of robots that was added
     */
    public List<Robot> placePlayers(int numbPLayers) {
        robotNamesList = new ArrayList<>();
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
                robotNamesList.add(RobotNames.getById(i));
                setPlayer(r);
                playerList.add(r);
                startPositions.remove(pos);
            }
        }
        return playerList;
    }

    /**
     * Place up to 7 AI (robots) on the map, max amount is dependant on amount of starting positions and the amount of players.
     * @param numbAI desired number of players
     * @return the list of robots that was added
     */
    public List<Robot> placeAI(int numbAI) {
        if(numbAI >= 7) numbAI = 7;

        if(numbAI > startPositions.size()) numbAI=startPositions.size();

        AIList = new ArrayList<>();
        for (int i = 0; i < numbAI; i++) {
            Random rand = new Random();
            Vector2 pos = startPositions.get(rand.nextInt(startPositions.size()));

            if (playerLayer.getCell((int) pos.x, (int) pos.y) != null){
                pos = startPositions.get(rand.nextInt(startPositions.size()));
            }

            if (playerLayer.getCell((int) pos.x, (int) pos.y) == null) {
                RobotNames name = RobotNames.getById(i);
                while(robotNamesList.contains(name)){
                    int n = i;
                    n++;
                    name = RobotNames.getById(n);
                }

                Robot r = new Robot(pos, name);
                robotNamesList.add(name);
                setPlayer(r);
                AIList.add(r);
                startPositions.remove(pos);
            }
        }
        return AIList;
    }

    /**
     * respawns the robot at their backup position.
     * @param r
     */
    public void respawnPlayer(Robot r){
        playerLayer.setCell(r.getPosX(),r.getPosY(),null);
        r.setPos(r.getBp_x(), r.getBp_y());
        playerLayer.setCell(r.getBp_x(), r.getBp_y(), r.getState());
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


    /**
     * removes the last player that is dead.
     */
    public void removePrevPlayer(){
        Robot prevRobot;
        if(playerIdx == 0) prevRobot = playerList.get(playerList.size()-1);
        else  prevRobot = playerList.get(playerIdx - 1);
        playerLayer.setCell(prevRobot.getBp_x(), prevRobot.getBp_y(), null);
        playerList.remove(prevRobot);
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

    /**
     * checks if there is a conveyor belt on the position.
     * @param x
     * @param y
     * @return
     */
    public boolean isConveyor(int x, int y){
        return (conveyorLayer.getCell(x,y) != null);
    }

    /**
     * checks if there is a tool on the position.
     * @param x
     * @param y
     * @return
     */
    public boolean isTool(int x, int y){
        return (toolLayer.getCell(x,y) != null);
    }

    /**
     * checks if there is a rotor pad on the position.
     * @param x
     * @param y
     * @return
     */
    public boolean isRotorPad(int x, int y){
        return (rotorLayer.getCell(x,y) != null);
    }

    /**
     * checks if there is a pusher on the position.
     * @param x
     * @param y
     * @return
     */
    public boolean hasPusher(int x, int y){
        return (pusherLayer.getCell(x,y) != null);
    }

    /**
     * returns the belt that is on that position.
     * @param x
     * @param y
     * @return
     */
    public Belt getBelt(int x, int y){
        Vector2 pos = new Vector2(x,y);
        for (Belt belt: beltList) {
            if (belt.getPos().equals(pos)){
                return belt;
            }
        }
        throw new IllegalArgumentException("no such belt!");
    }
    /**
     * returns the rotor pad that is on that position.
     * @param x
     * @param y
     * @return
     */
    public RotorPad getPad(int x, int y){
        Vector2 pos = new Vector2(x,y);
        for (RotorPad pad: rotorPos) {
            if (pad.getPos().equals(pos)){
                return pad;
            }
        }
        throw new IllegalArgumentException("no such pad!");
    }

    /**
     * returns the tool that is on that position.
     * @param x
     * @param y
     * @return
     */
    public Pusher getPusher(int x, int y){
        Vector2 pos = new Vector2(x,y);
        for (Pusher pusher : pushers) {
            if (pusher.getPos().equals(pos)){
                return pusher;
            }
        }
        throw new IllegalArgumentException("no such pad!");
    }

    /**
     * returns a list of the AI
     * @return
     */
    public List<Robot> getAIList() {
        return AIList;
    }

    /**
     *returns a list fo both the players and the AI.
     */
    public List<Robot> getListOfPlayers(){
        List<Robot> list = new ArrayList<>();

        if(AIList != null){ list.addAll(AIList);}
        list.addAll(playerList);
        return list;
    }

    public void boardSwithPlayer(){
        board.setPlayer();
    }

}