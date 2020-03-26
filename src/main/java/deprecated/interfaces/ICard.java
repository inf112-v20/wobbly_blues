package deprecated.interfaces;

import classes.Map;
import classes.Robot;

public interface ICard extends Comparable<ICard>{

    /**
     * Set what robot the card should interact with
     * @param robot target robot
     */
    void setRobot(Robot robot);

    /**
     * do the action specified by the card
     */
    void doAction(Map map);

    int getPriority();
}
