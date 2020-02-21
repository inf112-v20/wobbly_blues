package classes;

import enums.Direction;
import interfaces.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;

public class Robot implements IRobot {

    private TiledMapTileLayer.Cell state;

    private int x, y;

    public Robot(TiledMapTileLayer.Cell start){
        x = 0;
        y = 0;
        state = start;
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

}

