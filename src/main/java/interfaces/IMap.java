package interfaces;

import enums.Direction;

public interface IMap {
    void setCell();
    boolean canGo(Direction dir);
    ICell getCell(IPosition pos);
    void createRobot();

}
