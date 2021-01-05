package Model.Chess;

import Model.Piece;

/**
*@author Oi Zhen Fan
*/

public class Triangle extends Piece {

    private String chessPieceType;
    private String moveDirection;

    //Constructot
    public Triangle(int index, String chessPieceColor){
        super(index, chessPieceColor);
        chessPieceType = "triangle";
        moveDirection = "forward";
    }

    //Set the value of index, chessPieceColor and chessPieceType
    public void configure(Integer index, String chessPieceColor, String chessPieceType)
    {
        this.chessPieceColor = chessPieceColor;
        this.index = index;
        this.chessPieceType = chessPieceType;
    }

    //Get the value of chessPieceType
    @Override
    public String getChessPieceType() {
        return this.chessPieceType;
    }

    //Get the value of index
    @Override
    public Integer getIndex(){
        return this.index;
    }

    //Get the value of chessPieceColor
    @Override
    public String getChessPieceColor(){
        return this.chessPieceColor;
    }

    //Get the value of moveDirection
    @Override
    public String getMoveDirection()
    {
        return this.moveDirection;
    }

    //Set the value of moveDirection
    @Override
    public void setMoveDirection (String moveDirection){
        this.moveDirection = moveDirection;

    }
}
