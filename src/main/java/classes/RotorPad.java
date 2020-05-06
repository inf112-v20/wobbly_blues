package classes;

import com.badlogic.gdx.math.*;
import enums.*;

public class RotorPad {

    private Vector2 pos;
    private RotorType Type;
    /**
     * sets the rotor pad with th correct position and the corresponding type of rotorpad to the ID
     * @param pos
     * @param ID
     */
    public RotorPad(Vector2 pos, int ID){
        this.pos = pos;
        this.Type = getType(ID);
    }

    public RotorType getType() {
        return Type;
    }

    public Vector2 getPos() {
        return pos;
    }

    private RotorType getType(int ID){
        if(ID == TileID.ROTATE_PAD_LEFT.getId()){
            return  RotorType.LEFTTURN;
        }
        else if(ID == TileID.ROTATE_PAD_RIGHT.getId()){
            return  RotorType.RIGHTTURN;
        }
        else{
            throw new IllegalArgumentException("not a valid id!");
        }
    }

    public int getClockwiseRotation(){
        return Type.getclockwiseRotation();
    }

    /**
     * which why the pad rotates the player.
     */
    public enum RotorType {
        LEFTTURN(3),
        RIGHTTURN(1);

        private final int clockwiseRotation;

        RotorType(int clockwiseRotation) {
            this.clockwiseRotation = clockwiseRotation;
        }

        public int getclockwiseRotation() {
            return clockwiseRotation;
        }
    }

}
