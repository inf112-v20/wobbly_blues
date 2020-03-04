package classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import enums.Direction;
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
        TURNRIGHT   (0, 1),
        UTURN       (0, 2),
        TURNLEFT    (0, 3);

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
    private IRobot robot;
    static Texture cardTexture = new Texture("assets/card/card.png");
    private Texture cardSymbol;

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
        this.robot = robot;
    }

    @Override
    public void doAction(Map map) {
        for (int i = 0; i < getCellsToMove(); i++) {
            map.moveRobot(robot, robot.getDirection());
        }
        for (int i = 0; i < getClockwiseRotation(); i++) {
            robot.setDirection(robot.getDirection().turnRight());
        }
    }

    public String getName(){
        switch (cardType){
            case UTURN:
                return "U Turn";
            case MOVEONE:
                return "Move One";
            case MOVETWO:
                return "Move Two";
            case TURNLEFT:
                return "Turn Left";
            case MOVETHREE:
                return "Move Three";
            case TURNRIGHT:
                return "Turn Right";
        }
        return "ERROR";
    }

    public void getCardTexture(){
        cardSymbol = new Texture("assets/card/"+cardType.name()+".png");
    }

    public void render(SpriteBatch batch, BitmapFont font, int x, int y,
                       int w, int h){
        int padding = 5;
        batch.draw(cardTexture,x,y,w,h);
        font.draw(
            batch,
            getName(),
            x,
            y+h-padding,
            w,
            Align.center,
            true
        );
        if (cardSymbol == null) getCardTexture();
        batch.draw(cardSymbol,x,y+padding,w,w);
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
