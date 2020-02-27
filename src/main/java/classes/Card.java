package classes;

import interfaces.ICard;
import interfaces.IRobot;

public class Card implements ICard {
    public enum cardType{
        MOVEONE     (1, 0),
        MOVETWO     (2, 0),
        MOVETHREE   (3, 0),
        TURNLEFT    (0, -1),
        TURNRIGHT   (0, 1),
        UTURN       (0, 2);

        //Midlertidig for å vise tankegang.
        private final int cellsToMove;
        private final int clockwiseRotation;
        cardType(int cellsToMove, int clockwiseRotation) {
            this.cellsToMove = cellsToMove;
            this.clockwiseRotation = clockwiseRotation;
        }
    }

    @Override
    public void setRobot(IRobot robot) {

    }

    @Override
    public void doAction() {

    }
}
