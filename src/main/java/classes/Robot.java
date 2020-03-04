package classes;

import enums.Direction;
import interfaces.*;
import com.badlogic.gdx.maps.tiled.*;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class Robot implements IRobot {

    private TiledMapTileLayer.Cell state;

    private int x, y;
    private ArrayList<Card> hand;
    private int bp_x, bp_y;
    private ArrayList<Integer> flags;
    private int hp;
    private Direction direction;

    public Robot(TiledMapTileLayer.Cell start){
        hp = 3;
        x = 1;
        y = 1;
        bp_x = x;
        bp_y = y;
        state = start;
        flags = new ArrayList<>();
        direction = Direction.UP;
    }

    @Override
    public void takeDamage() {
        hp--;
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
    public void createHand() {
        hand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hand.add(new Card());
            hand.get(i).setRobot(this);
        }
    }

    @Override
    public boolean isReady() {
        //TODO: implement this!
        return false;
    }

    @Override
    public Direction getDirection() {return direction;}

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

    @Override
    public int getPosX(){ return x;}

    @Override
    public int getPosY(){return y;}

    public void setBackup(int x, int y){
        bp_y = y;
        bp_x = x;
    }

    public void addFlag(int id){
        flags.add(id);
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

    public int getHp(){
        return hp;
    }
    @Override
    public List<Card> getHand() {
        if (hand == null)
            createHand();
        else if (hand.isEmpty())
            createHand();
        return hand;
    }

}

