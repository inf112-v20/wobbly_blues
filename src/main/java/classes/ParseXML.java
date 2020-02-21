package classes;

import cells.BlankCell;
import cells.FlagCell;
import cells.HoleCell;
import interfaces.IMap;

public class ParseXML {

    public void parse(int[][][] arr, IMap map) {
        int rows = arr.length;
        int rowWidth = arr[0].length;
        int cellsInLoc = arr[0][0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rowWidth; j++) {
                for (int k = 0; k < cellsInLoc; k++) {
                    switch (arr[i][j][k]) {
                        case 0:
                            break;
                        case 5:
                            map.addCell(i, j, new BlankCell());
                            break;
                        case 6:
                            map.addCell(i, j, new HoleCell());
                            break;
                        case 55:
                            map.addCell(i, j, new FlagCell());
                            break;
                    }
                }
            }

        }
    }
    
}
