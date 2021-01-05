import Controller.ChessController;
import View.Panelling;
import View.Table;

import javax.swing.*;
import java.awt.*;

/**
*@author Mang Yu Jie
*@author Lee Guang Shen
*@author Leim Jing Han
*@author Low Min Xuan
*@author Oi Zhen Fan
 */

public class JChess {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChessController controls = new ChessController();
                controls.setClass(controls);
                controls.initializeChess();
                Panelling panels = new Panelling(controls);
                Table table = new Table(controls, panels);
                controls.setTable(table);

                JFrame frame = new JFrame("Webale Chess");
                frame.setSize(1000,800);
                frame.setMinimumSize(new Dimension(1000,800));
                frame.setVisible(true);
                frame.setLayout(new BorderLayout());
                frame.add(controls.table.boardPanel, BorderLayout.CENTER);
                frame.add(controls.table.statePanel, BorderLayout.WEST);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });
    }
}
