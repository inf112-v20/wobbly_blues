package Interface;

import Enums.Direction;

public interface IMap {
    void setCell();
    boolean canGo(Direction dir);
    ICell getCell(IPosition pos);
    void createRobot();

}
