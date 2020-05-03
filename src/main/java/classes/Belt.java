package classes;

import com.badlogic.gdx.math.*;
import enums.Direction;
import enums.TileID;

import static enums.TileID.*;

public class Belt {

    private Direction dir;
    private Vector2 pos;

    public Belt(Direction dir, Vector2 pos) {
        this.dir = dir;
        this.pos = pos;
    }

    public Belt(Vector2 pos, int id){
        this.pos = pos;
        dir = translateID(id);
    }

    public Vector2 getPos() {
        return pos;
    }


    public Direction getDirection() {
        return dir;
    }

    /**
     * hei og velkommen til den ekleste metoden noen gang laget,
     * laget av selveste Tines Valen ;)
     * @param id
     * @return
     */
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

    public enum BeltType {
        NORMBELT(1, 0),
        FASTBELT(2, 0),
        NRORIGHTTURN(1, 1),
        LEFTTURN(1, 3);

        private final int cellsToMove;
        private final int clockwiseRotation;

        BeltType(int cellsToMove, int clockwiseRotation) {
            this.cellsToMove = cellsToMove;
            this.clockwiseRotation = clockwiseRotation;
        }

        public int getCellsToMove() {
            return cellsToMove;
        }
        public int getClockwiseRotation() {
            return clockwiseRotation;
        }
    }
}
