package classes;

import com.badlogic.gdx.math.*;
import enums.*;

public class Belt {

    private Direction dir;
    private Vector2 pos;

    public Belt(Vector2 pos, Direction dir){

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
