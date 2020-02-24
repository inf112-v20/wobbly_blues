package deprecated.classes;

import deprecated.interfaces.IMap;
import deprecated.interfaces.IPosition;
import enums.Direction;
import interfaces.ICell;

import java.util.ArrayList;

public class Map implements IMap {

    List3D<ICell> mapList;

    public Map(int w, int h){
        mapList = new List3D<>(w, h);
    }

    public int getWidth(){
        return mapList.getWidth();
    }

    public int getHeight() {
        return mapList.getHeight();
    }

    @Override
    public void setCell(){
    }

    //TODO: add canGo after walls have been implemented
    @Override
    public boolean canGo(int x, int y, Direction dir) {
        switch (dir){
            case DOWN:
                getCellsAtPos(x, y-1);
                return false;
            case UP:
                return false;
            case RIGHT:
                return false;
            case LEFT:
                return false;
            default:
                return false;
        }
    }

    public ArrayList<ICell> getCellsAtPos(int x, int y) {
        return mapList.get(x,y);
    }

    public ICell getCell(int x, int y, int z){
        return mapList.get(x,y,z);
    }

    @Override
    public void createRobot() {

    }

    @Override
    public void addCell(int i, int j, ICell cell) {
        mapList.add(i, j, cell);
    }

}
