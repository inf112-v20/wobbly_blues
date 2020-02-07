package classes;

import enums.Direction;
import interfaces.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;

public class Robot implements IRobot {

    private TiledMapTileLayer.Cell state;

    private Vector2 Pos;

    public Robot(TiledMapTileLayer.Cell start){
        Pos = new Vector2().set(0,0);
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
    public void setPos(float x, float y){this.Pos.set(x,y);}

    @Override
    public float getPosX(){ return Pos.x;}

    @Override
    public float getPosY(){return Pos.y;}

}

