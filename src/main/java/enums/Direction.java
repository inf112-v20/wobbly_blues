package enums;

public enum Direction {
    UP      (0, 0, 1),
    RIGHT   (1, 1, 0),
    DOWN    (2, 0, -1),
    LEFT    (3, -1, 0);

    private final int directionAsInt;
    private final int xMovement;
    private final int yMovement;
    Direction(int directionAsInt, int xMovement, int yMovement){
        this.directionAsInt = directionAsInt;
        this.xMovement = xMovement;
        this.yMovement = yMovement;
    }
    public int getDirectionAsInt(){return directionAsInt;}
    public int getXMovement(){return xMovement;}
    public int getYMovement(){return yMovement;}

    public static Direction getDirectionFromInt(int directionAsInt){
        for (Direction direction: Direction.values()){
            if (directionAsInt == direction.getDirectionAsInt()){
                return direction;
            }
        }
        return Direction.UP;
    }


    public Direction getOpposite(){
        switch (this){
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            case UP: return DOWN;
            case DOWN: return UP;
        }
        return null;
    }

    public Direction turnRight(){
        switch (this){
            case LEFT: return UP;
            case RIGHT: return DOWN;
            case UP: return RIGHT;
            case DOWN: return LEFT;
        }
        return null;
    }

    public Direction turnLeft(){
        switch (this){
            case LEFT: return DOWN;
            case RIGHT: return UP;
            case UP: return LEFT;
            case DOWN: return RIGHT;
        }
        return null;
    }
}

