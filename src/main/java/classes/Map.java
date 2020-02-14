package classes;

import enums.Direction;
import interfaces.ICell;
import interfaces.IMap;
import interfaces.IPosition;

import java.util.ArrayList;

public class Map implements IMap {

    List3D<ICell> mapList;

    public Map(int x, int y){
        mapList = new List3D<>(x, y);
    }


    @Override
    public void setCell() {

    }

    @Override
    public boolean canGo(Direction dir) {
        return false;
    }

    @Override
    public ICell getCell(IPosition pos) {
        return null;
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
