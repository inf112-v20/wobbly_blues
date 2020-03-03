package classes;

import interfaces.ICard;
import interfaces.IRobot;

import java.util.Random;

public class Card implements ICard {

    @Override
    public int compareTo(ICard o) {
        if (getPriority() > o.getPriority()) return 1;
        else return -1;
    }

    public enum CardType{
        MOVEONE     (1, 0),
        MOVETWO     (2, 0),
        MOVETHREE   (3, 0),
        TURNLEFT    (0, -1),
        TURNRIGHT   (0, 1),
        UTURN       (0, 2);

        //Midlertidig for å vise tankegang.
        private final int cellsToMove;
        private final int clockwiseRotation;
        CardType(int cellsToMove, int clockwiseRotation) {
            this.cellsToMove = cellsToMove;
            this.clockwiseRotation = clockwiseRotation;
        }

        public int getCellsToMove() {
            return cellsToMove;
        }

        public int getClockwiseRotation() {
            return clockwiseRotation;
        }
    }

    private CardType cardType;
    private int priority;

    public Card(){
        Random r = new Random();
        this.cardType = CardType.values()[r.nextInt(CardType.values().length)];
        priority = r.nextInt(100);
    }

    public Card(CardType type){
        this.cardType = type;
        priority = 10;
    }

    @Override
    public void setRobot(IRobot robot) {

    }

    @Override
    public void doAction() {

    }

    @Override
    public int getPriority() {
        return priority;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getCellsToMove(){
        return cardType.cellsToMove;
    }

    public int getClockwiseRotation(){
        return cardType.clockwiseRotation;
    }

}
