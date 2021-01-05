package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.Piece;
import Model.Chess.Arrow;
import Model.Chess.Chevron;
import Model.Chess.Plus;
import Model.Chess.Sun;
import Model.Chess.Triangle;
import View.Table;

/**
*@author Low Min Xuan
*@author Mang Yu Jie
*@author Lee Guang Shen
*/

//This class is the controller that manage the table and model of the game 
public class ChessController {
    public static ArrayList<Piece> chessBoard;
    public static Table table;

    private static ChessController controls = new ChessController();
    public static ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
    private Integer step;
    private Integer redStep;
    private Integer blueStep;
    private Integer transformRed;
    private Integer transformBlue;
    private ArrayList<Integer> availableSteps;
    private Integer source;
    private Piece selectedPiece;
    private Integer destination;
    

    /**
    *Constructor
    *
    *@author Low Min Xuan
    */
    public ChessController() { 
        chessBoard = new ArrayList<Piece>(Collections.nCopies(56, null));
        this.step = 0;
        this.blueStep = 0;
        this.redStep = 0;
        this.transformRed = null;
        this.transformBlue = null;
        this.availableSteps = new ArrayList<Integer>();
        this.source = null;
        this.selectedPiece = null;
        this.destination = null;
        
    }

    /**
    *Set the value of source
    *
    *@author Low Min Xuan
    */
    public void setSource(Integer source)
    {
        this.source = source;
    }

    /**
    *Get the value of source
    *
    *@author Low Min Xuan
    */
    public Integer getSource()
    {
        return this.source;
    }

    /**
    *Get the turn of player
    *
    *@author Low Min Xuan
    */
    public String getPlayerTurn()
    {
        if(step % 2 == 0)
        {
            return "red";
        }
        else
        {
            return "blue";
        }
    }


    /**
    *Get the value of redStep
    *
    *@author Low Min Xuan
    */
    public Integer getRedStep()
    {
        return redStep;
    }

    /**
    *Get the value of blueStep
    *
    *@author Low Min Xuan
    */
    public Integer getBlueStep()
    {
        return blueStep;
    }

    /**
    *Set the value of step
    *
    *@author Low Min Xuan
    */
    public void setStep(Integer step)
    {
        this.step = step;
    }

   

    /**
    *Set the value of availableSteps
    *
    *@author Low Min Xuan
    */
    public void setAvailableSteps(ArrayList<Integer> availableSteps)
    {
        this.availableSteps = availableSteps;
    }

    /**
    *Setter for controller
    *
    *@author Low Min Xuan
    */
    public void setClass(ChessController controls){
        this.controls = controls;
    }

    /**
    *Setter for table
    *
    *@author Low Min Xuan
    */
    public void setTable(Table table){
        this.table = table;
    }

    /**
    *Get the value of availableSteps
    *
    *@author Low Min Xuan
    */
    public ArrayList<Integer> getAvailableSteps()
    {
        return this.availableSteps;
    }

    /**
    *To check whether the clicked button is null
    *
    *@author Low Min Xuan
    */
    public boolean isClicked(){
        return source != null;
    }

    /**
    *To reset the variables
    *
    *@author Low Min Xuan
    */
    public void resetState()
    {
        controls.source = null;
        controls.selectedPiece = null;
        controls.destination = null;
        controls.availableSteps.clear();
    }

    /**
    *To initailize the default chess
    *
    *@author Low Min Xuan
    */
    public void initializeChess() {
        chessBoard = new ArrayList<Piece>(Collections.nCopies(56, null));
        setIcons();
        chessBoard.set(0, new Plus(0, "blue")); 
        chessBoard.set(1, new Triangle(1, "blue"));
        chessBoard.set(2, new Chevron(2, "blue"));
        chessBoard.set(3, new Sun(3, "blue"));
        chessBoard.set(4, new Chevron(4, "blue"));
        chessBoard.set(5, new Triangle(5, "blue")); 
        chessBoard.set(6, new Plus(6, "blue"));
        chessBoard.set(7, new Arrow(7, "blue"));
        chessBoard.set(9, new Arrow(9, "blue"));
        chessBoard.set(11,new Arrow(11, "blue"));
        chessBoard.set(13,new Arrow(13, "blue"));

        chessBoard.set(42, new Arrow(42, "red"));
        chessBoard.set(44, new Arrow(44, "red"));
        chessBoard.set(46, new Arrow(26, "red"));
		chessBoard.set(48, new Arrow(48, "red"));
        chessBoard.set(49, new Plus(49, "red"));
        chessBoard.set(50, new Triangle(50, "red"));
        chessBoard.set(51, new Chevron(51, "red"));
        chessBoard.set(52, new Sun(52, "red"));
        chessBoard.set(53, new Chevron(53, "red"));
        chessBoard.set(54, new Triangle(54, "red"));
        chessBoard.set(55, new Plus(55, "red"));
    }

    /**
    *Save Game Function
    *
    *@author Low Min Xuan
    */
    public void saveGame(File fileName, String gameName) {

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(step + "\n");
            writer.write(redStep + "\n");
            writer.write(blueStep + "\n");

            for (Integer i = 0; i < controls.chessBoard.size(); i++) {
                if (controls.chessBoard.get(i) != null) {
                    writer.write(i +"\n");
                    writer.write(controls.chessBoard.get(i).getIndex() + "\n");
                    writer.write(controls.chessBoard.get(i).getChessPieceColor() + "\n");
                    writer.write(controls.chessBoard.get(i).getChessPieceType() + "\n");
                } else
                    continue;
            }
            writer.flush();
            writer.close();
            Table.saveBox("Game is successfully saved as " + gameName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        } catch (IOException e) {
            System.out.println("Error !");
        }
    }

    /**
    *Load Game Function
    *
    *@author Low Min Xuan
    */
    public void loadGame(File fileName) {
        Integer list;
        Integer index;
        String pieceColor;
        String pieceType;

        try {
            Scanner file = new Scanner(fileName);
            controls.setStep(file.nextInt());
            redStep = file.nextInt();
            blueStep = file.nextInt();
            controls.chessBoard = new ArrayList<Piece>(Collections.nCopies(56, null));
            while (file.hasNext()) {

                list = file.nextInt();
                index = file.nextInt();
                pieceColor = file.next();
                pieceType = file.next();
                

                if (pieceType.equals("sun")) {
                    if(pieceColor.equals("red")){
                        controls.chessBoard.set(list, new Sun(index, "red"));
                    }
                    else if(pieceColor.equals("blue")){
                        controls.chessBoard.set(list, new Sun(index, "blue"));
                    }
                    
                } else if (pieceType.equals("chevron")) {
                    if(pieceColor.equals("red")){
                        controls.chessBoard.set(list, new Chevron(index, "red"));
                    }
                    else if(pieceColor.equals("blue")){
                        controls.chessBoard.set(list, new Chevron(index, "blue"));
                    }
                    
                } else if (pieceType.equals("triangle")) {
                    if(pieceColor.equals("red")){
                        controls.chessBoard.set(list, new Triangle(index, "red"));
                    }
                    else if(pieceColor.equals("blue")){
                        controls.chessBoard.set(list, new Triangle(index, "blue"));
                    }
                    
                } else if (pieceType.equals("plus")) {
                    if(pieceColor.equals("red")){
                        controls.chessBoard.set(list, new Plus(index, "red"));
                    }
                    else if(pieceColor.equals("blue")){
                        controls.chessBoard.set(list, new Plus(index, "blue"));
                    }
                    
                } else if (pieceType.equals("arrow")){
                    if(pieceColor.equals("red")){
                        controls.chessBoard.set(list, new Arrow(index, "red"));
                    }
                    else if(pieceColor.equals("blue")){
                        controls.chessBoard.set(list, new Arrow(index, "blue"));
                    }
                    
                }
            }
            file.close();
            fileName.delete();

            controls.refreshPanel();
            controls.resetState();
            controls.changePlayerTurn();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
    *Add the step after change player turn
    *
    *@author Low Min Xuan
    */
    public void addStep(String chessPieceColor)
    {
        if(chessPieceColor == "red")
        {
            this.redStep++;
        }
        else
        {
            this.blueStep++;
        }
        this.step++;
    }
   
    /**
    *To check whether the new available is inside 0-->55
    *
    *@author Mang Yu Jie
    */
    public boolean onBoard(Integer index, Integer location, Integer gap) {
        Integer engageMove = index + location;

        if (engageMove < 0 || engageMove > 55) {
            return false;
        } else {
            if (Math.abs(engageMove / 7 - index / 7) != gap) {
                return false;
            }
        }

        return true;
    }

    /**
    *Calculate the available movement for Sun
    *
    *@author Mang Yu Jie
    */
    public ArrayList<Integer> SunValidMoves(Integer index) {
        ArrayList<Integer> newLocation = new ArrayList<Integer>();
        //up and down
        if (onBoard(index, -7, 1)) {
            if(chessBoard.get(index - 7) != null){
                if(chessBoard.get(index - 7).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index - 7);
                }
            }
            else
            {newLocation.add(index - 7);}
        }
        if (onBoard(index, +7, 1)) {
            if(chessBoard.get(index + 7) != null){
                if(chessBoard.get(index + 7).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index + 7);
                }
            }
            else
            {newLocation.add(index + 7);}
        }
        //left and right
        if (onBoard(index, +1, 0)) {
            if(chessBoard.get(index + 1) != null){
                if(chessBoard.get(index + 1).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index + 1);
                }
            }
            else
            {newLocation.add(index + 1);}
        }
        if (onBoard(index, -1, 0)) {
            if(chessBoard.get(index - 1) != null){
                if(chessBoard.get(index - 1).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index - 1);
                }
            }
            else
            {newLocation.add(index - 1);}
        }
        //diagonally
        if (onBoard(index, -6, 1)) {
            if(chessBoard.get(index -6) != null){
                if(chessBoard.get(index -6).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index -6);
                }
            }
            else
            {newLocation.add(index -6);}
        }
        if (onBoard(index, -8, 1)) {
            if(chessBoard.get(index -8) != null){
                if(chessBoard.get(index -8).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index -8);
                }
            }
            else
            {newLocation.add(index -8);}
        }
        if (onBoard(index, +6, 1)) {
            if(chessBoard.get(index +6) != null){
                if(chessBoard.get(index +6).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +6);
                }
            }
            else
            {newLocation.add(index +6);}
        }
        if (onBoard(index, +8, 1)) {
            if(chessBoard.get(index +8) != null){
                if(chessBoard.get(index +8).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +8);
                }
            }
            else
            {newLocation.add(index +8);}
        }

        return newLocation;
    }

    /**
    *Calculate the available movement for Chevron
    *
    *@author Mang Yu Jie
    */
    public ArrayList<Integer> ChevronValidMoves(Integer index){
        ArrayList<Integer> newLocation = new ArrayList<Integer>();
        
        //up and down
        if (onBoard(index, -15, 2)) {
            if(chessBoard.get(index - 15) != null){
                if(chessBoard.get(index - 15).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index - 15);
                }
            }
            else
            {newLocation.add(index - 15);}
        }
        if (onBoard(index, -13, 2)) {
            if(chessBoard.get(index - 13) != null){
                if(chessBoard.get(index - 13).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index - 13);
                }
            }
            else
            {newLocation.add(index - 13);}
        }
        if (onBoard(index, +15, 2)) {
            if(chessBoard.get(index + 15) != null){
                if(chessBoard.get(index + 15).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index + 15);
                }
            }   
            else
            {newLocation.add(index + 15);}
        }
        if (onBoard(index, +13, 2)) {
            if(chessBoard.get(index + 13) != null){
                if(chessBoard.get(index + 13).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index + 13);
                }
            }   
            else
            {newLocation.add(index + 13);}
        }

        //left and right
        if (onBoard(index, +9, 1)) {
            if(chessBoard.get(index + 9) != null){
                if(chessBoard.get(index + 9).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index + 9);
                }
            }
            else
            {newLocation.add(index + 9);}
        }
        if (onBoard(index, +5, 1)) {
            if(chessBoard.get(index + 5) != null){
                if(chessBoard.get(index + 5).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index + 5);
                }
            }
            else
            {newLocation.add(index + 5);}
        }
        if (onBoard(index, -9, 1)) {
            if(chessBoard.get(index - 9) != null){
                if(chessBoard.get(index - 9).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index - 9);
                }
            }
            else
            {newLocation.add(index - 9);}
        }
        if (onBoard(index, -5, 1)) {
            if(chessBoard.get(index - 5) != null){
                if(chessBoard.get(index - 5).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index - 5);
                }
            }
            else
            {newLocation.add(index - 5);}
        }

        return newLocation;
    }

    /**
    *Calculate the available movement for Triangle
    *
    *@author Mang Yu Jie
    */
    public ArrayList<Integer>TriangleValidMoves(Integer index){
        ArrayList<Integer>newLocation = new ArrayList<Integer>();
        //forward-right
        for (int i = 1; i < 7; i++) {
            if (onBoard(index, -6 * i, i)) {
                if(chessBoard.get(index -6*i) != null && chessBoard.get(index -6*i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index -6*i) != null && chessBoard.get(index -6*i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index -6*i);
                    break;
                }
                else {
                    newLocation.add(index -6*i);
                }
            }
        }

        //forward-left
        for (int i = 1; i < 7; i++) {
            if (onBoard(index, -8 * i, i)) {
                if(chessBoard.get(index -8*i) != null && chessBoard.get(index -8*i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index -8*i) != null && chessBoard.get(index -8*i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index -8*i);
                    break;
                }
                else {
                    newLocation.add(index -8*i);
                }
            }
        }

        //backwards-right
        for (int i = 1; i < 7; i++) {
            if (onBoard(index, 8 * i, i)) {
                if(chessBoard.get(index +8*i) != null && chessBoard.get(index +8*i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index +8*i) != null && chessBoard.get(index +8*i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +8*i);
                    break;
                }
                else {
                    newLocation.add(index +8*i);
                }
            }
        }

        //backwards-left
        for (int i = 1; i < 7; i++) {
            if (onBoard(index, 6 * i, i)) {
                if(chessBoard.get(index +6*i) != null && chessBoard.get(index +6*i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index +6*i) != null && chessBoard.get(index +6*i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +6*i);
                    break;
                }
                else {
                    newLocation.add(index +6*i);
                }
            }
        }

        return newLocation;
    }

    /**
    *Calculate the available movement for Plus
    *
    *@author Mang Yu Jie
    */
    public ArrayList<Integer>PlusValidMoves(Integer index){
        ArrayList<Integer>newLocation = new ArrayList<Integer>();


        //right
        for(int i = 1; i <= 7; i++){
            if(onBoard(index,i,0)){
                if(chessBoard.get(index +i) != null && chessBoard.get(index +i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index +i) != null && chessBoard.get(index +i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +i);
                    break;
                }
                else {
                    newLocation.add(index +i);
                }
            }
        }
        //left
        for(int i = -1; i >= -7; i--){
            if(onBoard(index,i,0)){
                if(chessBoard.get(index +i) != null && chessBoard.get(index +i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index +i) != null && chessBoard.get(index +i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +i);
                    break;
                }
                else {
                    newLocation.add(index +i);
                }
            }
        }

        //forward
        for (int i = 1; i < 8; i++) {
            if (onBoard(index, -7 * i, i)) {
                if(chessBoard.get(index -7 * i) != null && chessBoard.get(index -7 * i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index -7 * i) != null && chessBoard.get(index -7 * i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index -7 * i);
                    break;
                }
                else {
                    newLocation.add(index -7 * i);
                }
            }
        }

        //backwards
        for (int i = 1; i < 8; i++) {
            if (onBoard(index, 7 * i, i)) {
                if(chessBoard.get(index +7 * i) != null && chessBoard.get(index +7 * i).getChessPieceColor() == getPlayerTurn()) {
                    break;
                }
                if(chessBoard.get(index +7 * i) != null && chessBoard.get(index +7 * i).getChessPieceColor() != getPlayerTurn()) {
                    newLocation.add(index +7 * i);
                    break;
                }
                else {
                    newLocation.add(index +7 * i);
                }
            }
        }

        return newLocation;
    }

    /**
    *Calculate the available movement for Arrow
    *
    *@author Mang Yu Jie
    */
    public ArrayList<Integer> ArrowValidMoves(Integer index) {
        ArrayList<Integer> newLocation = new ArrayList<Integer>();
        if (chessBoard.get(index).getChessPieceColor() == "red"||chessBoard.get(index).getChessPieceColor() == "blue") {
            if (index <= 6) {
                chessBoard.get(index).setMoveDirection("backward");
            } else if (index >= 49) {
                chessBoard.get(index).setMoveDirection("forward");
            }
        }

        if ((chessBoard.get(index).getMoveDirection() == "forward" && chessBoard.get(index).getChessPieceColor() == "red") || (chessBoard.get(index).getMoveDirection() == "forward" && chessBoard.get(index).getChessPieceColor() == "blue")) {
            for (int i = 1; i < 3; i++) {
                if (onBoard(index, -7 * i, i)) {
                    if(chessBoard.get(index - 7*i) != null && chessBoard.get(index - 7*i).getChessPieceColor() == getPlayerTurn()) {
                        break;
                    }
                    if(chessBoard.get(index - 7*i) != null && chessBoard.get(index - 7*i).getChessPieceColor() != getPlayerTurn()) {
                        newLocation.add(index - 7*i);
                        break;
                    }
                    else {
                        newLocation.add(index - 7*i);
                    }
                }
            }
        } else if ((chessBoard.get(index).getMoveDirection() == "backward" && chessBoard.get(index).getChessPieceColor() == "red") || (chessBoard.get(index).getMoveDirection() == "backward" && chessBoard.get(index).getChessPieceColor() == "blue")) {
            for (int i = 1; i < 3; i++) {
                if (onBoard(index, 7 * i, i)) {
                    if(chessBoard.get(index + 7*i) != null && chessBoard.get(index + 7*i).getChessPieceColor() == getPlayerTurn()) {
                        break;
                    }
                    if(chessBoard.get(index + 7*i) != null && chessBoard.get(index + 7*i).getChessPieceColor() != getPlayerTurn()) {
                        newLocation.add(index + 7*i);
                        break;
                    }
                    else {
                        newLocation.add(index + 7*i);
                    }
                }
            }
        }

        return newLocation;
    }

    /**
    *Swap Triangle and Plus after move 2 times for Red
    *
    *@author Lee Guang Shen
    */
    public boolean shouldTransformRed()
    {
        if(getRedStep() > 0)
        {
            if(getRedStep() % 2 == 0 )
            {
                return this.transformRed % 2 != 0;
            }
        }
        else
        {
            return false;
        }
        return false;
    }

    /**
    *Swap Triangle and Plus after move 2 times for Blue
    *
    *@author Lee Guang Shen
    */
    public boolean shouldTransformBlue() 
    {
        if(getBlueStep() > 0)
        {
            if(getBlueStep() % 2 == 0) //Every 2 Blue turns swap Triangle with Plus
            {
                return this.transformBlue % 2 != 0; //Swap Blue
            }
        }
        else
        {
            return false;
        }
        return false;
    }

    /**
    *How to swap Triangle and Plus for Red
    *
    *@author Lee Guang Shen
    */
    public void transformRed(){
        if(getRedStep() % 2 == 0 ) {
            for(int i=0; i<chessBoard.size(); i++) //Loop the whole chessBoard
            {
                if(chessBoard.get(i) != null)
                {
                    if(chessBoard.get(i).getChessPieceType() == "triangle" && chessBoard.get(i).getChessPieceColor() == "red") //if Red Triangle valid
                    {
                        chessBoard.set(i, new Plus(i, "red")); //Swap to Plus
                    }
                    else if(chessBoard.get(i).getChessPieceType() == "plus" && chessBoard.get(i).getChessPieceColor() == "red") //if Red Plus valid
                    {
                        chessBoard.set(i, new Triangle(i, "red")); //Swap to Triangle
                    }
                }
            }
        }
    }

    /**
    *How to swap Triangle and Plus for Blue
    *
    *@author Lee Guang Shen
    */
    public void transformBlue()
    {
        if(getBlueStep() % 2 == 0){
            for(int i=0; i<chessBoard.size(); i++) //Loop the whole chessBoard
            {
                if(chessBoard.get(i) != null)
                {
                    if(chessBoard.get(i).getChessPieceType() == "triangle" && chessBoard.get(i).getChessPieceColor() == "blue") //if Blue Triangle valid
                    {
                        chessBoard.set(i, new Plus(i, "blue")); //Swap to Plus
                    }
                    else if(chessBoard.get(i).getChessPieceType() == "plus" && chessBoard.get(i).getChessPieceColor() == "blue") //if Blue Plus valid
                    {
                        chessBoard.set(i, new Triangle(i, "blue")); //Swap to Triangle
                    }
                }
            }
        }
    }

    /**
    *Set all the button background to default colour
    *
    *@author Lee Guang Shen
    */
    public void resetValidPanel(){
        for(int i=0; i<56; i++){
            table.panel.labels.get(i).setBackground(Color.WHITE);
        }
    }

    /**
    *Show where the chess can move on the chessBoard
    *
    *@author Lee Guang Shen
    */
    public void showValidPanel(ArrayList<Integer> availableSteps){

        table.panel.labels.get(controls.chessBoard.indexOf(controls.selectedPiece)).setBackground(Color.GRAY);

        for(int i=0; i < availableSteps.size(); i++){
            table.panel.labels.get(availableSteps.get(i)).setBackground(Color.LIGHT_GRAY);
        }

    }

    /**
    *Reverse all the image icons in the ArrayList
    *
    *@author Lee Guang Shen
    */
    public void reverseIcon()
    {
        BufferedImage img[] = new BufferedImage[10];
        for(int i=0; i<getIcons().size(); i++)
        {
            img[i] = imageToBufferedImage(getIcons().get(i));
            controls.icons.set(i, new ImageIcon(rotateIcon(img[i], -Math.PI/1)));
        }
    }

    /**
    *Allows to rotate the image icon
    *
    *@author Lee Guang Shen
    */
    public BufferedImage rotateIcon(BufferedImage image, double angle) {
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gdev = genv.getDefaultScreenDevice();
        GraphicsConfiguration gcon = gdev.getDefaultConfiguration(); 
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth = (int)Math.floor((double)width * cos + (double)height * sin);
        int newHeight = (int)Math.floor((double)height * cos + (double)width * sin);
        int transparency = image.getColorModel().getTransparency();
        BufferedImage newicon = gcon.createCompatibleImage(newWidth, newHeight, transparency);
        Graphics2D g2d = newicon.createGraphics();
        g2d.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g2d.rotate(angle, (double)(width / 2), (double)(height / 2));
        g2d.drawRenderedImage(image, (AffineTransform)null);
        return newicon;
    }

    /**
    *
    *
    *@author Lee Guang Shen
    */
    public BufferedImage imageToBufferedImage(ImageIcon chessImg) {
        BufferedImage bimg = new BufferedImage
                (chessImg.getIconWidth(),chessImg.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g2d = bimg.createGraphics();
        chessImg.paintIcon(null, g2d, 0,0);
        g2d.dispose();

        return bimg;
    }

    /**
    *Get the image icon for all pieces on chessBoard and add into ArrayList
    *
    *@author Lee Guang Shen
    */
    public ArrayList<ImageIcon> getIcons(){
        ArrayList<ImageIcon> chessImg = new ArrayList<ImageIcon>();


        ImageIcon img1 = new ImageIcon(getClass().getResource("/View/ImageIcons/Blue_Triangle.png"));
        ImageIcon img2 = new ImageIcon(getClass().getResource("/View/ImageIcons/Blue_Plus.png"));
        ImageIcon img3 = new ImageIcon(getClass().getResource("/View/ImageIcons/Blue_Chevron.png"));
        ImageIcon img4 = new ImageIcon(getClass().getResource("/View/ImageIcons/Blue_Sun.png"));
        ImageIcon img5 = new ImageIcon(getClass().getResource("/View/ImageIcons/Blue_Arrow.png"));
        ImageIcon img6 = new ImageIcon(getClass().getResource("/View/ImageIcons/Red_Triangle.png"));
        ImageIcon img7 = new ImageIcon(getClass().getResource("/View/ImageIcons/Red_Plus.png"));
        ImageIcon img8 = new ImageIcon(getClass().getResource("/View/ImageIcons/Red_Chevron.png"));
        ImageIcon img9 = new ImageIcon(getClass().getResource("/View/ImageIcons/Red_Sun.png"));
        ImageIcon img10 = new ImageIcon(getClass().getResource("/View/ImageIcons/Red_Arrow.png"));

        chessImg.add(img1);
        chessImg.add(img2);
        chessImg.add(img3);
        chessImg.add(img4);
        chessImg.add(img5);
        chessImg.add(img6);
        chessImg.add(img7);
        chessImg.add(img8);
        chessImg.add(img9);
        chessImg.add(img10);

        return chessImg;
    }

    /**
    *Set the value of icons
    *
    *@author Lee Guang Shen
    */
    public void setIcons(){
        this.icons = getIcons();
    }

    /**
    *Refresh Panel
    *
    *@author Lee Guang Shen
    */
    public void refreshPanel(){
        table.boardPanel.removeAll();

        for(int i=0; i<56; i++)
        {
            if(controls.chessBoard.get(i) == null) {
                table.panel.labels.get(i).setIcon(null);
            } else if(controls.chessBoard.get(i).getChessPieceColor() == "red")
            {
                if(controls.chessBoard.get(i).getChessPieceType() == "arrow")
                {
                    if(controls.chessBoard.get(i).getMoveDirection() == "forward") {
                        table.panel.labels.get(i).setIcon(icons.get(9));
                    }
                    else
                    {
                        table.panel.labels.get(i).setIcon(new ImageIcon(rotateIcon(imageToBufferedImage(controls.icons.get(9)), -Math.PI/1)));
                    }
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "sun")
                {
                    table.panel.labels.get(i).setIcon(icons.get(8));
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "triangle")
                {
                    table.panel.labels.get(i).setIcon(icons.get(5));
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "plus")
                {
                    table.panel.labels.get(i).setIcon(icons.get(6));
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "chevron")
                {
                    table.panel.labels.get(i).setIcon(icons.get(7));
                }
            }
            else if(controls.chessBoard.get(i).getChessPieceColor() == "blue")
            {
                if(controls.chessBoard.get(i).getChessPieceType() == "arrow")
                {
                    if(controls.chessBoard.get(i).getMoveDirection() == "forward") {
                        table.panel.labels.get(i).setIcon(icons.get(4));
                    }
                    else
                    {
                        table.panel.labels.get(i).setIcon(new ImageIcon(rotateIcon(imageToBufferedImage(controls.icons.get(4)), -Math.PI/1)));
                    }
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "sun")
                {
                    table.panel.labels.get(i).setIcon(icons.get(3));
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "triangle")
                {
                    table.panel.labels.get(i).setIcon(icons.get(0));
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "plus")
                {
                    table.panel.labels.get(i).setIcon(icons.get(1));
                }
                else if(controls.chessBoard.get(i).getChessPieceType() == "chevron")
                {
                    table.panel.labels.get(i).setIcon(icons.get(2));
                }
            }
        }
        for(JLabel label:table.panel.labels){
            table.boardPanel.add(label);
        }
        table.boardPanel.repaint();
        table.boardPanel.revalidate();

    }

    /**
    *Change the player turn
    *
    *@author Lee Guang Shen
    */
    public void changePlayerTurn () {
        if (controls.getPlayerTurn() == "red") {
            controls.table.panel.playerTurn.setText("Red turn!");
            controls.table.panel.playerTurn.setForeground(Color.RED.darker());
        } else if (controls.getPlayerTurn() == "blue") {
            controls.table.panel.playerTurn.setText("Blue turn!");
            controls.table.panel.playerTurn.setForeground(Color.BLUE);
        }
        controls.table.boardPanel.revalidate();
    }

    /**
    *Reverse the arraylist
    *
    *@author Lee Guang Shen
    */
    public void rotateBoard(){
        Collections.reverse(controls.chessBoard);
    }

    /**
    *To check whether player is win
    *
    *@author Lee Guang Shen
    */
    public String isWin()
    {
        int red = 0;
        int blue = 0;
        for(int i=0; i<controls.chessBoard.size(); i++)
        {
            if(controls.chessBoard.get(i) != null){
                if(controls.chessBoard.get(i).getChessPieceColor() == "red")
                {
                    red++;
                    if(controls.chessBoard.get(i).getChessPieceType() != "sun")
                    {
                        red--;
                    }
                    else if(controls.chessBoard.get(i).getChessPieceType() == "sun")
                    {
                        red++;
                    }
                }
                else if(controls.chessBoard.get(i).getChessPieceColor() == "blue")
                {
                    blue++;
                    if(controls.chessBoard.get(i).getChessPieceType() != "sun")
                    {
                        blue--;
                    }
                    else if(controls.chessBoard.get(i).getChessPieceType() == "sun")
                    {
                        blue++;
                    }
                }
            }
        }

        if(red == 0)
        {
            return "blue";
        }
        else if(blue == 0)
        {
            return "red";
        }
        else
            return null;
    }

    /**
    *New Game
    *
    *@author Lee Guang Shen
    */
    public void newGame() {
        controls.initializeChess();
        controls.refreshPanel();
        controls.resetState();
        controls.setStep(0);
        controls.redStep = 0;
        controls.blueStep = 0;
        controls.transformRed = null;
        controls.transformBlue = null;
        controls.changePlayerTurn();
        controls.resetValidPanel();

    }

    /**
    *Callback for chess, after the piece cliked in the table
    *
    *@author Lee Guang Shen
    */
    public void chessPressed(Integer index) {
        if (controls.source == null) {
            controls.source = index; //Declare index = source
            controls.selectedPiece = controls.chessBoard.get(index); //selectedPiece is the initial press of the chess piece
            System.out.println("\nInitial chess");

            if (controls.selectedPiece == null) {
                resetValidPanel(); //To reset if no destination piece is selected
                resetState();
                System.out.println("Fail to move the chess piece, please reselect initial chess");
            } 
            else {
                if (controls.selectedPiece.getChessPieceColor() != getPlayerTurn()) {
                    resetState();
                } else if (controls.selectedPiece.getChessPieceType() == "triangle") { //Move for Traingle
                    resetValidPanel();
                    setAvailableSteps(controls.TriangleValidMoves(index));
                    showValidPanel(availableSteps);
                    refreshPanel();

                } else if (controls.selectedPiece.getChessPieceType() == "chevron") { //Move for Chevron
                    resetValidPanel();
                    setAvailableSteps(controls.ChevronValidMoves(index));
                    showValidPanel(availableSteps);
                    refreshPanel();

                } else if (controls.selectedPiece.getChessPieceType() == "plus") { //Move for Plus
                    resetValidPanel();
                    setAvailableSteps(controls.PlusValidMoves(index));
                    showValidPanel(availableSteps);
                    refreshPanel();

                } else if (controls.selectedPiece.getChessPieceType() == "sun") { //Move for Sun
                    resetValidPanel();
                    setAvailableSteps(controls.SunValidMoves(index));
                    showValidPanel(availableSteps);
                    refreshPanel();

                } else if (controls.selectedPiece.getChessPieceType() == "arrow") { //Move for Arrow
                    resetValidPanel();
                    setAvailableSteps(controls.ArrowValidMoves(index));
                    showValidPanel(availableSteps);
                    refreshPanel();

                }

            }
        } 
        else {
            controls.destination = index;
            System.out.println("\nDestination");

            if (controls.availableSteps.contains(controls.destination)) {
                resetValidPanel();
                controls.chessBoard.set(controls.source, null);
                controls.chessBoard.set(controls.destination, controls.selectedPiece);
                controls.addStep(controls.selectedPiece.getChessPieceColor());
                System.out.print("Red Step: "+getRedStep() + ", ");
                System.out.print("Blue Step: "+getBlueStep() + ", ");
                System.out.println("Total Step: " +step);
                System.out.println("Chess Moved Successfully");
                System.out.println();
                if(shouldTransformRed())
                {
                    transformRed();
                }
                else if(shouldTransformBlue())
                {
                    transformBlue();
                }
                this.transformRed = this.redStep;
                this.transformBlue = this.blueStep;
                resetState();

                if(isWin() == "red")
                {
                    System.out.println("Red Win");
                    Table.infoBox("Congratulations, Red Wins!");
                    controls.newGame();
                    rotateBoard();
                }
                else if(isWin() == "blue")
                {
                    System.out.println("Blue Win");
                    Table.infoBox("Congratulations, Blue Wins!");
                    controls.newGame();
                    rotateBoard();
                }
                if (controls.getPlayerTurn() == "blue"){
                    reverseIcon();
                }
                else
                {
                    setIcons();
                }
                changePlayerTurn();
                rotateBoard();
                refreshPanel();
            }
            else{
                resetState();
            }

        }
    }
}