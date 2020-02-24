package deprecated.interfaces;

import cells.BlankCell;
import enums.Direction;
import interfaces.ICell;

public interface IMap {
    void setCell();
    boolean canGo(int x, int y, Direction dir);
    void createRobot();

    void addCell(int i, int j, ICell cell);
}
