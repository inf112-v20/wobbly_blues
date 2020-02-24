package cells;

import enums.Direction;

public class WallCell {

    Direction wallDir;

    public WallCell(Direction dir){
        wallDir = dir;
    }

    @Override
    public String toString() {
        return "WallCell "+wallDir.name();
    }
}
