package classes;

import com.badlogic.gdx.math.*;
import enums.Direction;

import static enums.TileID.*;

public class Belt {

    private Direction dir;
    private Vector2 pos;
    private BeltType type;

    /**
     * sets the belt with th correct position and the corresponding type of belt to the ID
     * @param pos
     * @param id
     */
    public Belt(Vector2 pos, int id){
        this.pos = pos;
        dir = translateID(id);
        type = beltType(id);
    }

    public Vector2 getPos() {
        return pos;
    }

    public Direction getDirection() {
        return dir;
    }

    public BeltType getType() {
        return type;
    }

    public Direction translateID(int id){
        if (id == SOUTH_TO_NORTH_BELT.getId() ||
            id == EAST_TO_NORTH_BELT.getId() ||
            id == EAST_SOUTH_TO_NORTH_BELT.getId() ||
            id == WEST_SOUTH_TO_NORTH_BELT.getId() ||
            id == WEST_TO_NORTH_BELT.getId() ||
            id == WEST_EAST_TO_NORTH_BELT.getId() ||
            id == EAST_SOUTH_TO_NORTH_EXPRESS_BELT.getId() ||
            id == EAST_TO_NORTH_EXPRESS_BELT.getId() ||
            id == SOUTH_TO_NORTH_EXPRESS_BELT.getId() ||
            id == WEST_EAST_TO_NORTH_EXPRESS_BELT.getId() ||
            id == WEST_SOUTH_TO_NORTH_EXPRESS_BELT.getId() ||
            id == WEST_TO_NORTH_EXPRESS_BELT.getId()){
            return Direction.UP;
        }
        if (id == EAST_TO_SOUTH_BELT.getId() ||
            id == EAST_NORTH_TO_SOUTH_BELT.getId() ||
            id == NORTH_TO_SOUTH_BELT.getId() ||
            id == WEST_EAST_TO_SOUTH_BELT.getId() ||
            id == WEST_NORTH_TO_SOUTH_BELT.getId() ||
            id == WEST_TO_SOUTH_BELT.getId() ||
            id == EAST_NORTH_TO_SOUTH_EXPRESS_BELT.getId() ||
            id == EAST_TO_SOUTH_EXPRESS_BELT.getId() ||
            id == NORTH_TO_SOUTH_EXPRESS_BELT.getId() ||
            id == WEST_EAST_TO_SOUTH_EXPRESS_BELT.getId() ||
            id == WEST_NORTH_TO_SOUTH_EXPRESS_BELT.getId() ||
            id == WEST_TO_SOUTH_EXPRESS_BELT.getId()) {
            return Direction.DOWN;
        }
        if (id == NORTH_SOUTH_TO_EAST_BELT.getId() ||
            id == NORTH_TO_EAST_BELT.getId() ||
            id == SOUTH_TO_EAST_BELT.getId() ||
            id == WEST_NORTH_TO_EAST_BELT.getId() ||
            id == WEST_SOUTH_TO_EAST_BELT.getId() ||
            id == WEST_TO_EAST_BELT.getId() ||
            id == NORTH_SOUTH_TO_EAST_EXPRESS_BELT.getId() ||
            id == NORTH_TO_EAST_EXPRESS_BELT.getId() ||
            id == SOUTH_TO_EAST_EXPRESS_BELT.getId() ||
            id == WEST_NORTH_TO_EAST_EXPRESS_BELT.getId() ||
            id == WEST_SOUTH_TO_EAST_EXPRESS_BELT.getId() ||
            id == WEST_TO_EAST_EXPRESS_BELT.getId()) {
            return Direction.RIGHT;
        }
        if (id == EAST_NORTH_TO_WEST_BELT.getId() ||
            id == EAST_SOUTH_TO_WEST_BELT.getId() ||
            id == EAST_TO_WEST_BELT.getId() ||
            id == NORTH_SOUTH_TO_WEST_BELT.getId() ||
            id == NORTH_TO_WEST_BELT.getId() ||
            id == SOUTH_TO_WEST_BELT.getId() ||
            id == EAST_NORTH_TO_WEST_EXPRESS_BELT.getId() ||
            id == EAST_SOUTH_TO_WEST_EXPRESS_BELT.getId() ||
            id == EAST_TO_WEST_EXPRESS_BELT.getId() ||
            id == NORTH_SOUTH_TO_WEST_EXPRESS_BELT.getId() ||
            id == NORTH_TO_WEST_EXPRESS_BELT.getId() ||
            id == SOUTH_TO_WEST_EXPRESS_BELT.getId()) {
            return Direction.LEFT;
        }
        else{
           throw new IllegalArgumentException("not a valid id!");
        }
    }

    public BeltType beltType(int id){
        if (id == EAST_TO_SOUTH_BELT.getId()||
                id == NORTH_TO_EAST_BELT.getId() ||
                id == WEST_TO_NORTH_BELT.getId() ||
                id == SOUTH_TO_WEST_BELT.getId() ||
                id == EAST_TO_NORTH_BELT.getId() ||
                id == NORTH_TO_WEST_BELT.getId() ||
                id == WEST_TO_SOUTH_BELT.getId() ||
                id == SOUTH_TO_EAST_BELT.getId() ||
                id == EAST_TO_WEST_BELT.getId() ||
                id == NORTH_TO_SOUTH_BELT.getId() ||
                id == WEST_TO_EAST_BELT.getId() ||
                id == SOUTH_TO_NORTH_BELT.getId() ||
                id == WEST_SOUTH_TO_NORTH_BELT.getId() ||
                id == EAST_SOUTH_TO_NORTH_BELT.getId() ||
                id == WEST_EAST_TO_NORTH_BELT.getId() ||
                id == WEST_NORTH_TO_SOUTH_BELT.getId() ||
                id == EAST_NORTH_TO_SOUTH_BELT.getId() ||
                id == WEST_EAST_TO_SOUTH_BELT.getId() ||
                id == WEST_SOUTH_TO_EAST_BELT.getId() ||
                id == WEST_NORTH_TO_EAST_BELT.getId() ||
                id == NORTH_SOUTH_TO_EAST_BELT.getId() ||
                id == EAST_SOUTH_TO_WEST_BELT.getId() ||
                id == EAST_NORTH_TO_WEST_BELT.getId() ||
                id == NORTH_SOUTH_TO_WEST_BELT.getId()) {
            return BeltType.NORMBELT;
        }
        else if(id == EAST_TO_SOUTH_EXPRESS_BELT.getId()||
                id == NORTH_TO_EAST_EXPRESS_BELT.getId() ||
                id == WEST_TO_NORTH_EXPRESS_BELT.getId() ||
                id == SOUTH_TO_WEST_EXPRESS_BELT.getId() ||
                id == EAST_TO_NORTH_EXPRESS_BELT.getId() ||
                id == NORTH_TO_WEST_EXPRESS_BELT.getId() ||
                id == WEST_TO_SOUTH_EXPRESS_BELT.getId() ||
                id == SOUTH_TO_EAST_EXPRESS_BELT.getId() ||
                id == EAST_TO_WEST_EXPRESS_BELT.getId() ||
                id == NORTH_TO_SOUTH_EXPRESS_BELT.getId() ||
                id == WEST_TO_EAST_EXPRESS_BELT.getId() ||
                id == SOUTH_TO_NORTH_EXPRESS_BELT.getId() ||
                id == WEST_SOUTH_TO_NORTH_EXPRESS_BELT.getId() ||
                id == EAST_SOUTH_TO_NORTH_EXPRESS_BELT.getId() ||
                id == WEST_EAST_TO_NORTH_EXPRESS_BELT.getId() ||
                id == WEST_NORTH_TO_SOUTH_EXPRESS_BELT.getId() ||
                id == EAST_NORTH_TO_SOUTH_EXPRESS_BELT.getId() ||
                id == WEST_EAST_TO_SOUTH_EXPRESS_BELT.getId() ||
                id == WEST_SOUTH_TO_EAST_EXPRESS_BELT.getId() ||
                id == WEST_NORTH_TO_EAST_EXPRESS_BELT.getId() ||
                id == NORTH_SOUTH_TO_EAST_EXPRESS_BELT.getId() ||
                id == EAST_SOUTH_TO_WEST_EXPRESS_BELT.getId() ||
                id == EAST_NORTH_TO_WEST_EXPRESS_BELT.getId() ||
                id == NORTH_SOUTH_TO_WEST_EXPRESS_BELT.getId()) {
            return BeltType.FASTBELT;
        }
        else{
            throw new IllegalArgumentException("not a valid id!");
        }
    }

    public enum BeltType {
        NORMBELT(1),
        FASTBELT(2);

        private final int cellsToMove;

        BeltType(int cellsToMove) {
            this.cellsToMove = cellsToMove;
        }

        public int getCellsToMove() {
            return cellsToMove;
        }
    }
}
