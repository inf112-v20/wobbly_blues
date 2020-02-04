package Interface;

public interface IMapPiece {

    void setPos(IPosition pos);

    IPosition getPos();

    /**
     * Do an end of the turn action based on the type of the map piece
     */
    void doTurn();



}
