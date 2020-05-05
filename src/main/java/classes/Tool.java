package classes;

import com.badlogic.gdx.math.*;
import enums.*;

public class Tool {

    private Vector2 pos;
    private TileID Type;

    public Tool(Vector2 pos, int ID){
        this.pos = pos;
        Type = getType(ID);
    }

    public TileID getType() {
        return Type;
    }

    public Vector2 getPos() {
        return pos;
    }

    private TileID getType(int ID){
        if(ID == TileID.WRENCH.getId()){
            return TileID.WRENCH;
        }
        else if(ID == TileID.DOUBLE_WRENCH.getId()){
            return TileID.DOUBLE_WRENCH;
        }
        else{
            throw new IllegalArgumentException("not a valid id!");
        }
    }

}
