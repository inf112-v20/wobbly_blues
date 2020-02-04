package Class;

import Interface.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.tiles.*;
import com.badlogic.gdx.math.*;

public class Test_robot implements IRobot {

    private Texture Robot;

    private TiledMapTileLayer.Cell Normal;
    private TiledMapTileLayer.Cell Dead;
    private TiledMapTileLayer.Cell Won;
    private TiledMapTileLayer.Cell state;

    private Vector2 PlayerPos = new Vector2().set(0,0);

    public Test_robot(){
       Robot = new Texture(Gdx.files.internal("player.png"));

        Normal = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][0]));
        Dead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][1]));
        Won = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(new TextureRegion(Robot,900,300).split(300,300)[0][2]));

        state = Normal;
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
        this.PlayerPos.set(x,y);
    }
    public float getPosX(){
        return PlayerPos.x;
    }

    public float getPosY(){
        return PlayerPos.y;
    }

}

