package classes;

import com.badlogic.gdx.math.*;
import enums.*;

public class Laser {

    private Vector2 pos;
    private Direction dir;

    public Laser(Vector2 pos, Direction dir){
        this.pos = pos;
        this.dir = dir;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Direction getDir() {
        return dir;
    }

}
