package interfaces;

import enums.Direction;
import com.badlogic.gdx.maps.tiled.*;

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

    /**
     *
     * @return the direction the robot is facing
     */
    Direction getDirection();

    /*Returns the current state of the robot*/
    TiledMapTileLayer.Cell getState();

    /*sets the state of the robot*/
    void setState(TiledMapTileLayer.Cell state);

    /*sets the current Pos of the robot in a Vector2(2dvector)*/
    void setPos(float x, float y);

    /*returns the Y of the robot*/
    float getPosX();

    /*returns the X of the robot*/
    float getPosY();
}
