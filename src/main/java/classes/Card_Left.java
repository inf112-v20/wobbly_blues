package classes;

public class Card_Left {
    private Robot robot;

    public Card_Left(Robot r) {
        this.robot = r;
    }

    public void doTurn(){
        robot.setPos(robot.getPosX() - 1, robot.getPosY());
    }
}
