package interfaces;

import cells.BlankCell;
import enums.Direction;

public interface IMap {
    void setCell();
    boolean canGo(Direction dir);
    ICell getCell(IPosition pos);
    void createRobot();

    void addCell(int i, int j, ICell cell);
}
