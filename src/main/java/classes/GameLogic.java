package classes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import enums.*;

public class GameLogic {

    private static Map map;


    public static void setMap(Map map){
        GameLogic.map = map;
    }
    /**
     * Check if robot is on a flag, hole or out of bounds, and do appropriate actions
     * @param x posX of robot
     * @param y posY of robot
     * @param robot robot in question
     */
    public static void check(int x, int y, Robot robot){
        isFlag(x, y, robot);
        isOut(x, y, robot);
        isHole(x, y, robot);

        if(isPlayersDead()){
            System.out.println("All players are dead");
            Gdx.app.exit();
        }
        map.isThisPLayerDead(robot);
    }

    /**
     * Check if robot is on a hole and do appropriate action
     * @param x xPos of robot
     * @param y yPos of robot
     * @param robot robot in question
     * @return True if robot was on hole
     */
    public static boolean isHole(int x, int y, Robot robot) {
        if (map.holeLayer.getCell(x, y) != null) {
            System.out.println();
            decreaseLife(robot);
            return true;
        }
        else
            return false;
    }

    /**
     * Check if robot is on a flag and do appropriate action
     * @param x robots xPos
     * @param y robots yPos
     * @param robot robot in question
     * @return True if robot was on flag
     */
    public static boolean isFlag(int x, int y, Robot robot) {
        if (map.flagLayer.getCell(x,y) != null){
            robot.addFlag(map.flagLayer.getCell(x,y).getTile().getId());
            if(robot.numbFlags() == map.flagPos.size()){
                map.playerLayer.setCell(robot.getPosX(), robot.getPosY(), robot.getState());
                System.out.println("Robot " + robot.getName()+ " has Won!");
                Gdx.app.exit();
            }
            return true;
        }
        else if (map.flagLayer.getCell(x,y) == null){
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
    public static boolean isOut(int x, int y, Robot robot) {
        if (x > map.width-1) {
            decreaseLife(robot);
            return true;
        } else if (x < 0) {
            decreaseLife(robot);
            return true;
        } else if (y > map.height-1) {
            decreaseLife(robot);
            return true;
        } else if (y < 0) {
            decreaseLife(robot);
            return true;
        } else
            return false;
    }

    public static boolean isPlayersDead(){
        int i = 0;
        for (Robot r : map.playerList){
            if(r.getNormState() == r.getState()) i++;
        }
        if(i==0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Reduce the hp of robot, respawn the robot at current base position
     * @param robot robot in question
     */
    static void decreaseLife(Robot robot) {
        if (robot.getHp() > 0) {
            map.respawnPlayer(robot);
            robot.looseLife();
            robot.setDied(true);
        }
        if (robot.getHp() == 0){
            System.out.println("Robot "+robot.getName()+" Died!");
            robot.setDeadState();

        }
    }

    public static void fireLaser(Vector2 pos, Direction dir) {
        map.addLaser(pos, dir);
        if(!map.isOutside(pos)) return;

        if (map.hasPlayer(map.getNeighbourPos(pos, dir))){
            Robot r = map.getNeighbourRobot((int)pos.x, (int)pos.y,dir);
            r.takeDamage();
            if(r.getHp() == 0) {
                r.setDeadState();
                map.playerList.remove(r);
                map.playerLayer.setCell(r.getPosX(), r.getPosY(), null);
                map.boardSwithPlayer();
            } else if(r.getDamageToken() == 0){
                map.respawnPlayer(r);
            }
        } else if (map.canGo((int)pos.x, (int)pos.y, dir)){
            fireLaser(map.getNeighbourPos(pos, dir), dir);
        }
    }

    public static void fireAllLasers(){
        for(Laser l : map.laserPos){
            fireLaser(l.getPos(),l.getDir());
        }
    }

    public static void clearLasers() {
        for (int y = 0; y < map.laserLineLayer.getHeight(); y++) {
            for (int x = 0; x < map.laserLineLayer.getWidth(); x++) {
                map.laserLineLayer.setCell(x, y, null);
            }
        }
    }

    public static void doConveyor(Robot robot){
        if (map.isConveyor(robot.getPosX(),robot.getPosY())){
            Belt belt = map.getBelt(robot.getPosX(),robot.getPosY());
            if(belt.getType() == Belt.BeltType.FASTBELT){
                map.moveRobot(robot,belt.getDirection());
                if(map.isConveyor(robot.getPosX(),robot.getPosY())) {
                    if(map.getBelt(robot.getPosX(),robot.getPosY()).getType() == Belt.BeltType.FASTBELT) {
                        map.moveRobot(robot, map.getBelt(robot.getPosX(), robot.getPosY()).getDirection());
                    }
                }
            }
            else {
                map.moveRobot(robot, belt.getDirection());
            }
        }
    }

    public static void rotorPad(Robot robot){
        if(map.isRotorPad(robot.getPosX(),robot.getPosY())){
            RotorPad pad = map.getPad(robot.getPosX(),robot.getPosY());
            for (int i = 0; i < pad.getClockwiseRotation(); i++) {
                assert robot.getDirection().turnRight() != null;
                robot.setDirection(robot.getDirection().turnRight());
            }
        }
    }

    public static void repeair(Robot robot){
        if(map.isTool(robot.getPosX(),robot.getPosY())){
            robot.repeair();
            robot.setBackup(robot.getPosX(),robot.getPosY());

        }
    }
}
