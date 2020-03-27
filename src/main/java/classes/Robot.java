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
    public void createHand() {
        hand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hand.add(new Card());
            hand.get(i).setRobot(this);
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
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
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
            createHand();
        else if (hand.isEmpty())
            createHand();
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

    public TiledMapTileLayer.Cell getDeadState(){
       return states.getDead();
    }
}

