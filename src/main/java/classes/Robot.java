package classes;

import com.badlogic.gdx.math.*;
import enums.*;
import com.badlogic.gdx.maps.tiled.*;
import java.util.ArrayList;
import java.util.List;

public class Robot {

    private TiledMapTileLayer.Cell state;

    protected int x, y;
    protected ArrayList<Card> hand, discardPile, cardsChosen;
    protected int bp_x, bp_y;
    protected ArrayList<Integer> flags;
    protected int hp;
    protected int damageToken;

    protected Direction direction;
    protected boolean died;
    protected States states;
    protected RobotNames name;

    protected boolean ready = false;

    protected int numbCards;
    protected int numbRegister;

    public Robot(Vector2 pos, RobotNames name){
        this.name = name;
        hp = 3;
        damageToken = 0;
        discardPile = new ArrayList<>();
        this.x = (int)pos.x;
        this.y = (int)pos.y;
        bp_x = x;
        bp_y = y;
        flags = new ArrayList<>();
        direction = Direction.UP;
        states = new States();
        setNormalState();
        cardsChosen = new ArrayList<>();
        numbCards = 10;
        numbRegister = 5;

    }

    public void looseLife() {
        hp--;
    }

    public int getNumbCards() {
        return numbCards;
    }
    public int getNumbRegister() {
        return numbRegister;
    }

    public void takeDamage(){
        damageToken++;
        numbCards--;
        if(damageToken >=5){
            numbRegister--;
        }
        if (damageToken == 10){
            looseLife();
            damageToken = 0;
            numbCards = 9;
        }
    }

    /**
     * Fills a hand with random cards
     */

    public void createHand() {
        hand = new ArrayList<>();
        for (int i = 0; i < numbCards; i++) {
            hand.add(new Card());
            hand.get(i).setRobot(this);
        }
    }

    public void selectCard(int cardNr){
        if (cardNr<0 || cardNr>=hand.size()){
            throw new IllegalArgumentException("illegal card number");
        }
        if (cardsChosen.size() > 5){
            cardsChosen.remove(4);
        }
        cardsChosen.add(hand.get(cardNr));
    }

    public void unselectCard(int cardNr){
        if (cardNr<0 || cardNr>=hand.size()){
            throw new IllegalArgumentException("illegal card number");
        }
        Card card = hand.get(cardNr);
        TurnHandler.removeCard(card);
        cardsChosen.remove(card);
    }

    public boolean setReady(){
        if (cardsChosen.size() == numbRegister){
            for (int i = 0; i < 5; i++) {
                TurnHandler.addCard(i,cardsChosen.get(i));
            }
            ready=true;
            return true;
        }
        return false;
    }

    public boolean isReady() {
        return ready;
    }
    public void notReady() {
        ready = false;
    }


    public Direction getDirection() {return direction;}

    public void setDirection(Direction direction) {
        switch (direction){
            case LEFT:
                state.setRotation(1);
                break;
            case RIGHT:
                state.setRotation(3);
                break;
            case UP:
                state.setRotation(0);
                break;
            case DOWN:
                state.setRotation(2);
                break;
        }
        this.direction = direction;
    }

    public TiledMapTileLayer.Cell getState(){
        return state;
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getPosX(){ return x;}

    public int getPosY(){return y;}

    public void setBackup(int x, int y){
        bp_y = y;
        bp_x = x;
    }

    /**
     * Add flag to robot if it is the correct flag
     * @param id tile id of the flag to add
     * @return true if flag was added
     */
    public boolean addFlag(int id){
        if(id == TileID.FLAG1.getId() && !hasFlag(TileID.FLAG1.getId()) && !hasFlag(TileID.FLAG2.getId()) && !hasFlag(TileID.FLAG3.getId()) && !hasFlag(TileID.FLAG4.getId())){
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else if(id == TileID.FLAG2.getId() && hasFlag(TileID.FLAG1.getId()) && !hasFlag(TileID.FLAG2.getId()) && !hasFlag(TileID.FLAG3.getId()) && !hasFlag(TileID.FLAG4.getId())){
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else if(id == TileID.FLAG3.getId() && hasFlag(TileID.FLAG1.getId()) && hasFlag(TileID.FLAG2.getId()) && !hasFlag(TileID.FLAG3.getId()) && !hasFlag(TileID.FLAG4.getId())){
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else if(id == TileID.FLAG4.getId() && hasFlag(TileID.FLAG1.getId()) && hasFlag(TileID.FLAG2.getId()) && hasFlag(TileID.FLAG3.getId()) && !hasFlag(TileID.FLAG4.getId())) {
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else return false;
    }

    public boolean hasFlag(int id){
        return flags.contains(id);
    }

    public int numbFlags(){
        return flags.size();
    }

    public int getBp_x() {
        return bp_x;
    }

    public int getBp_y() {
        return bp_y;
    }

    public Vector2 getPos(){
        return new Vector2(x,y);
    }

    public int getHp(){
        return hp;
    }

    public int getDamageToken(){return damageToken;}

    public List<Card> getHand() {
        if (hand == null)
            createHand();
        else if (hand.isEmpty())
            createHand();
        return hand;
    }

    public void clearHand(){
        for (int i = 0; i < hand.size(); i++) {
            discardPile.add(hand.get(i));
        }
        cardsChosen.clear();
        createHand();
    }

    public void setDied(boolean b){
        died = b;
    }

    public boolean getDied(){
        return died;
    }

    public void setNormalState(){
        state = states.getNormal();
    }

    public void setDeadState(){
        state = states.getDead();
    }

    public void setWonState(){
        state = states.getWon();
    }

    public TiledMapTileLayer.Cell getDeadState(){
       return states.getDead();
    }
    public TiledMapTileLayer.Cell getNormState(){
        return states.getNormal();
    }

    public RobotNames getName() {
        return name;
    }

    public int repeair(){
        damageToken=damageToken-1;
        return damageToken;
    }


    /**
     * if there is AI in the game this is the method that controls the robot
     * Selects random cards from the hand
     * It is stupid, but it works.
     */

    public void doTurn() {
        createHand();
        while (cardsChosen.size() < numbRegister) {
            int rand = (int) (Math.random() * numbCards);
            while(cardsChosen.contains(hand.get(rand))) {
                rand = (int) (Math.random() * numbCards);
            }
            selectCard(rand);
        }
        setReady();
    }
}

