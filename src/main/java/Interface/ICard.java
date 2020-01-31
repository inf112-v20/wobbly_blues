package Interface;

public interface ICard {

    /**
     * Set what robot the card should interact with
     * @param robot target robot
     */
    void setRobot(IRobot robot);

    /**
     * do the action specified by the card
     */
    void doAction();
}
