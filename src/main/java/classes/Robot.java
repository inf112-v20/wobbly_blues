package classes;

import com.badlogic.gdx.math.*;
import enums.*;
import com.badlogic.gdx.maps.tiled.*;
import java.util.ArrayList;
import java.util.List;

public class Robot {

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
    private robotNames name;

    public Robot(Vector2 pos, robotNames n){
        name = n;
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

    public void looseLife() {
        hp--;
    }


    public void takeDamage(){
        damageToken++;
        if (damageToken == 10){
            looseLife();
            damageToken = 0;
        }
    }

    /**
     * Fills a hand with random cards
     */

    public void createHand() {
        hand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hand.add(new Card());
            hand.get(i).setRobot(this);
        }
    }

    public boolean isReady() {
        //TODO: implement this!
        return false;
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

    public robotNames getName() {
        return name;
    }
}

