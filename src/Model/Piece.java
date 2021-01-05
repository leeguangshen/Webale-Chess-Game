package Model;


/**
*@author Oi Zhen Fan
*/

//The main piece model the store current piece identity
public abstract class Piece {

    protected int index;
    protected String chessPieceColor;

    //Construtor
    public Piece( int index, String chessPieceColor)
    {
        this.index = index;
        this.chessPieceColor = chessPieceColor;
    }

    //Get the value of ChessPieceType
    public abstract String getChessPieceType();

    //Get the value of index
    public abstract Integer getIndex();

    //Get the value of ChessPieceColor
    public abstract String getChessPieceColor();

    //Set the value of moveDirection
    public abstract void setMoveDirection(String direction);

    //Get the value of moveDirection
    public abstract String getMoveDirection();

	public Object getPlayerTurn() {
		return null;
	}
}