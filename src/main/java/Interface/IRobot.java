package Interface;

import Enums.Direction;

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
    boolean powerDown();
    /**
    Creates the hand that holds the program cards that relates to that robot
     */
    void createHand();

    /**
     *
     * @return the direction the robot is facing
     */
    Direction getDirection();
}
