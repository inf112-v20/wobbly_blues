package enums;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Direction getOpposite(){
        switch (this){
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            case UP: return DOWN;
            case DOWN: return UP;
        }
    return null;
    }
}

