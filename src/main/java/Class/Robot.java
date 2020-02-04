package Class;

import Interface.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;

public class Robot implements IRobot {

    private Texture Robot;
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
    public void shootLaser() {

    }

    @Override
    public void input() {

    }

    @Override
    public boolean powerDown() {
        return false;
    }

    @Override
    public void creatHand() {
    }

    @Override
    public void getDirection() {
    }

    public TiledMapTileLayer.Cell getState(){
        return state;
    }

    public void setState(TiledMapTileLayer.Cell state){
        this.state = state;
    }
    public void setPos(float x, float y){
        this.Pos.set(x,y);
    }
    public float getPosX(){
        return Pos.x;
    }

    public float getPosY(){
        return Pos.y;
    }

}

