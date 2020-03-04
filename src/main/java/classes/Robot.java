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

    public Robot(TiledMapTileLayer.Cell start){
        x = 1;
        y = 1;
        bp_x = x;
        bp_y = y;
        state = start;
        flags = new ArrayList<>();

    }

    @Override
    public void takeDamage() {

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
    public Direction getDirection() {return null;}

    @Override
    public TiledMapTileLayer.Cell getState(){return state;}

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

    @Override
    public List<Card> getHand() {
        if (hand == null)
            createHand();
        else if (hand.isEmpty())
            createHand();
        return hand;
    }

}

