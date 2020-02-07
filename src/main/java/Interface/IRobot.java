package Interface;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;

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

    /*Returns the current state of the robot*/
    TiledMapTileLayer.Cell getState();

    /*sets the backup of the robot, it stores the current pos.*/
    void setBackup();

    /*returns the backup pos*/
    Vector2 getBackup();

    /*sets the state of the robot*/
    void setState(TiledMapTileLayer.Cell state);

    /*sets the current Pos of the robot in a Vector2(2dvector)*/
    void setPos(float x, float y);

    /*returns the Y of the robot*/
    float getPosX();

    /*returns the X of the robot*/
    float getPosY();
}
