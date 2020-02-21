package deprecated.interfaces;

import enums.Direction;
import interfaces.ICell;

public interface IMap {
    void setCell();
    boolean canGo(Direction dir);
    ICell getCell(IPosition pos);
    void createRobot();

    void addCell(int i, int j, ICell cell);
}
