package interfaces;


public interface IMapPiece {

    void setPos(int x, int y);

    int getX();
    int getY();

    /**
     * Do an end of the turn action based on the type of the map piece
     */
    void doTurn();



}
