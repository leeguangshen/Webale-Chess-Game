package View;

import Controller.ChessController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
*@author Leim Jing Han
*/

public class Panelling {

    public static ArrayList<JLabel> labels;
    public static ChessController controls;
    public static JLabel playerTurn;
    String pieceColor;

    //Set chessBoard
    public Panelling(ChessController controls){
        this.controls = controls;

        labels = new ArrayList<JLabel>();

        //Set the label of player turn at first
        playerTurn = new JLabel("Red turn!",JLabel.CENTER);
        playerTurn.setFont(new Font("Arial", Font.PLAIN, 20));
        playerTurn.setForeground(Color.RED.darker());

        //Loop to set the icon of piece
        for (int i = 0; i < 56; i++) {
            if (controls.chessBoard.get(i) == null) { //Set the chessBoard to white if no chess
                JLabel label = new JLabel();
                label.addMouseListener(new Table.MyMouseListener(i));
                label.setHorizontalAlignment(0);
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                labels.add(label);
            } else if (controls.chessBoard.get(i).getChessPieceType() == "sun") {
                if (controls.chessBoard.get(i).getChessPieceColor() == "blue") { //Set the image icon of blue sun
                    JLabel label = new JLabel(controls.icons.get(3),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(false);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                } else if (controls.chessBoard.get(i).getChessPieceColor() == "red") { //Set the image icon of red sun
                    JLabel label = new JLabel(controls.icons.get(8),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                }
            } else if (controls.chessBoard.get(i).getChessPieceType() == "chevron") {
                if (controls.chessBoard.get(i).getChessPieceColor() == "blue") { //Set the image icon of blue chevron
                    JLabel label = new JLabel(controls.icons.get(2),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                } else if (controls.chessBoard.get(i).getChessPieceColor() == "red") { //Set the image icon of red chevron
                    JLabel label = new JLabel(controls.icons.get(7),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                }
            } else if (controls.chessBoard.get(i).getChessPieceType() == "plus") {
                if (controls.chessBoard.get(i).getChessPieceColor() == "blue") { //Set the image icon of blue plus
                    JLabel label = new JLabel(controls.icons.get(1),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                } else if (controls.chessBoard.get(i).getChessPieceColor() == "red") { //Set the image icon of red plus
                    JLabel label = new JLabel(controls.icons.get(6),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                }
            } else if (controls.chessBoard.get(i).getChessPieceType() == "triangle") {
                if (controls.chessBoard.get(i).getChessPieceColor() == "blue") { //Set the image icon of blue triangle
                    JLabel label = new JLabel(controls.icons.get(0),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                } else if (controls.chessBoard.get(i).getChessPieceColor() == "red") { //Set the image icon of red triangle
                    JLabel label = new JLabel(controls.icons.get(5),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                }
            } else if (controls.chessBoard.get(i).getChessPieceType() == "arrow") {
                if (controls.chessBoard.get(i).getChessPieceColor() == "blue") { //Set the image icon of blue arrow
                    JLabel label = new JLabel(controls.icons.get(4),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                } else if (controls.chessBoard.get(i).getChessPieceColor() == "red") { //Set the image icon of red arrow
                    JLabel label = new JLabel(controls.icons.get(9),JLabel.CENTER);
                    label.addMouseListener(new Table.MyMouseListener(i));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    labels.add(label);
                }

            }
        }
    }
}
