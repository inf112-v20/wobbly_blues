package interfaces;

import cells.BlankCell;
import com.badlogic.gdx.math.Vector2;
import enums.Direction;

public interface IMap {
    void setCell();
    boolean canGo(int x, int y, Direction dir);
    void createRobot();

    void addCell(int i, int j, ICell cell);
}
