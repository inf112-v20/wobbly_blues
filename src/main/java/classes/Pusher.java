package classes;

import com.badlogic.gdx.math.*;
import enums.*;

public class Pusher {

    private Direction dir;
    private Vector2 pos;

    public Pusher(Vector2 pos, int ID) {
        this.pos = pos;
        this.dir = getDir(ID);
    }

    private Direction getDir(int ID){
        if(ID == TileID.PUSH_DOWN.getId()){
            return Direction.DOWN;
        }
        else if(ID == TileID.PUSH_UP.getId()){
            return Direction.UP;
        }
        else if(ID == TileID.PUSH_RIGHT.getId()){
            return Direction.RIGHT;
        }
        else if(ID == TileID.PUSH_LEFT.getId()){
            return Direction.LEFT;
        }
        else{
            throw new IllegalArgumentException("not a valid id!");
        }
    }
    public Direction getDir(){
        return dir;
    }

    public Vector2 getPos(){
        return pos;
    }
}
