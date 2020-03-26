package classes;

import com.badlogic.gdx.math.*;
import enums.*;
import interfaces.IRobot;
import com.badlogic.gdx.maps.tiled.*;
import java.util.ArrayList;
import java.util.List;

public class Robot implements IRobot {

    private TiledMapTileLayer.Cell state;

    private int x, y;
    private ArrayList<Card> hand;
    private int bp_x, bp_y;
    private ArrayList<Integer> flags;
    private int hp;
    private int damageToken;
    private Direction direction;
    private boolean died;
    private Card[] cardsChosen;
    private ArrayList<Integer> lockedCardNums;
    private States states;

    public Robot(Vector2 pos){
        hp = 3;
        damageToken = 0;
        this.x = (int)pos.x;
        this.y = (int)pos.y;
        bp_x = x;
        bp_y = y;
        flags = new ArrayList<>();
        direction = Direction.UP;
        states = new States();
        setNormalState();
        cardsChosen = new Card[5];
        lockedCardNums = new ArrayList<>();
    }

    @Override
    public void looseLife() {
        hp--;
    }

    @Override
    public void takeDamage(){
        damageToken++;
        if (damageToken == 10){
            looseLife();
            damageToken = 0;
        }
    }

    @Override
    public void die() {
    }

    @Override
    public void shootLaser() {
    }

    @Override
    public void input() {
    }

    @Override
    public boolean powerDown(boolean input) {return input;}

    @Override
    public void createHand(int amount) {
        hand = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            hand.add(new Card());
            hand.get(i).setRobot(this);
        }
    }

    @Override
    public boolean isReady() {
        for (int i = 0; i < 5; i++) {
            if (cardsChosen[i]==null) return false;
        }
        return true;
    }

    @Override
    public Card[] getChosenCards() {
        return cardsChosen;
    }

    public boolean setReady(){
        if (isReady()) {
            for (int i = 0; i < cardsChosen.length; i++) {
                TurnHandler.addCard(i, cardsChosen[i]);
            }
            return true;
        }
        return false;
    }

    public void chooseCard(int cardNum){
        for (int i = 0; i < cardsChosen.length; i++) {
            if (lockedCardNums.contains(i)) continue;
            if (cardsChosen[i]==null) continue;
            if (cardsChosen[i].equals(hand.get(cardNum))) { //If
                cardsChosen[i] = null;
                return;
            }
        }
        if (isReady()){
            cardsChosen[4] = hand.get(cardNum);
        }
        for (int i = 0; i < cardsChosen.length; i++) {
            if (cardsChosen[i] == null) {
                cardsChosen[i] = hand.get(cardNum);
                continue;
            }
        }

    }

    @Override
    public Direction getDirection() {return direction;}


    //Needs tweeking, rotates all the players.
    @Override
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

    @Override
    public TiledMapTileLayer.Cell getState(){
        return state;
    }

    @Override
    public void setState(TiledMapTileLayer.Cell state){this.state = state;}

    @Override
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    private void clearChosen(){
        for (int i = 0; i < 5; i++) {
            if (!lockedCardNums.contains(i)){
                cardsChosen[i] = null;
            }
        }
    }

    @Override
    public void newHand(){
        lockedCardNums = new ArrayList<>();
        if (damageToken>=5){
            lockedCardNums.add(4);
        }
        if (damageToken>=6){
            lockedCardNums.add(3);
        }
        if (damageToken>=7){
            lockedCardNums.add(2);
        }
        if (damageToken>=8){
            lockedCardNums.add(1);
        }
        if (damageToken>=9){
            lockedCardNums.add(0);
        }
        clearChosen();
        createHand(9-damageToken);
    }

    @Override
    public int getPosX(){ return x;}

    @Override
    public int getPosY(){return y;}

    @Override
    public void setBackup(int x, int y){
        bp_y = y;
        bp_x = x;
    }

    @Override
    public boolean addFlag(int id, TiledMapTileLayer flagLayer){
        if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG1.getId() && !hasFlag(tileID.FLAG1.getId()) && !hasFlag(tileID.FLAG2.getId()) && !hasFlag(tileID.FLAG3.getId()) && !hasFlag(tileID.FLAG4.getId())){
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG2.getId() && hasFlag(tileID.FLAG1.getId()) && !hasFlag(tileID.FLAG2.getId()) && !hasFlag(tileID.FLAG3.getId()) && !hasFlag(tileID.FLAG4.getId())){
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG3.getId() && hasFlag(tileID.FLAG1.getId()) && hasFlag(tileID.FLAG2.getId()) && !hasFlag(tileID.FLAG3.getId()) && !hasFlag(tileID.FLAG4.getId())){
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else if(flagLayer.getCell(x,y).getTile().getId() == tileID.FLAG4.getId() && hasFlag(tileID.FLAG1.getId()) && hasFlag(tileID.FLAG2.getId()) && hasFlag(tileID.FLAG3.getId()) && !hasFlag(tileID.FLAG4.getId())) {
            flags.add(id);
            setBackup(x,y);
            return true;
        }
        else return false;
    }

    @Override
    public boolean hasFlag(int id){
        return flags.contains(id);
    }

    @Override
    public int numbFlags(){
        return flags.size();
    }

    @Override
    public int getBp_x() {
        return bp_x;
    }

    @Override
    public int getBp_y() {
        return bp_y;
    }

    @Override
    public int getHp(){
        return hp;
    }

    public int getDamageToken(){return damageToken;}

    @Override
    public List<Card> getHand() {
        if (hand == null)
            newHand();
        else if (hand.isEmpty())
            newHand();
        return hand;
    }
    @Override
    public void setDied(boolean b){
        died = b;
    }
    @Override
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
}

