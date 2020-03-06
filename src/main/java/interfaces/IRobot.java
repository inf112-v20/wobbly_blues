package interfaces;

import classes.Card;
import enums.Direction;
import com.badlogic.gdx.maps.tiled.*;

import java.util.List;

public interface IRobot {

    /**
    this does not need to take in anything or return, because it is an internal action
     */
    void takeDamage();

    void die();

    /**
    It shoots the laser
     */
    void shootLaser();
    /**
    determines what the robot is going to do
     */
    void input();
    /**
    tells the system if the robot is going to power down or not
     */
    boolean powerDown(boolean input);
    /**
    Creates the hand that holds the program cards that relates to that robot
     */
    void createHand();

    boolean isReady();

    /**
     *
     * @return the direction the robot is facing
     */
    Direction getDirection();

    /**
     * sets the robotsdirection
     */
    void setDirection(Direction direction);

    /*Returns the current state of the robot*/
    TiledMapTileLayer.Cell getState();

    /*sets the state of the robot*/
    void setState(TiledMapTileLayer.Cell state);

    /*sets the current Pos of the robot in a Vector2(2dvector)*/
    void setPos(int x, int y);

    /*returns the Y of the robot*/
    int getPosX();

    /*returns the X of the robot*/
    int getPosY();

    //Sets the backup positions
    void setBackup(int x, int y);

    //adds a flag that the robot has touched
    boolean addFlag(int id, TiledMapTileLayer flagLayer);

    //checks if the robot has already been at that flag
    boolean hasFlag(int id);

    //the number of flags the robot has visited
    int numbFlags();

    //get x of the backup position
    int getBp_x();

    //gets the y of the backup position
    int getBp_y();

    //gets the current hp of the robot
    int getHp();

    //gets the card of the robot
    List<Card> getHand();

    void setDied(boolean b);
    boolean getDied();
}
