package deprecated.interfaces;

import enums.Direction;

public interface IMap {
    void setCell();
    boolean canGo(int x, int y, Direction dir);
    void createRobot();

    void addCell(int i, int j, ICell cell);
}
