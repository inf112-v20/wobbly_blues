package Interface;

public interface IRobot {

    /*
    this does not need to take in anything or return, because it is an internal action
     */
    void takeDamage();
    /*
    It shoots the laser
     */
    void shootLaser();
    /*
    determins what the robot is going to do
     */
    void input();
    /*
    tels the system if the robot is going to power down or not
     */
    boolean powerDown();
    /*
    Creats the hand that holds the program cards that relates to that robot
     */
    void creatHand();

    /*
    not sure
     */
    void getDirection();
}
