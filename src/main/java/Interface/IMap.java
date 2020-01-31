package Interface;

import Enums.Direction;

public interface IMap {
    boolean canGo(Direction dir);
    ICell getCell(int x, int y);
}
