package com.company;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private int pixelSize;

    public Window(char[][] board) throws HeadlessException {
        pixelSize = 50;
        setTitle("Pac-Man!");
        setSize(pixelSize*20, pixelSize*11);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);


        createBoard(board);
        GridLayout gd = new GridLayout(11,20);

        setLayout(gd);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void createBoard(char[][] board) {

        JPanel[][] jPanels = new JPanel[11][20];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                JPanel jp = new JPanel();
                char c = ' ';
                switch (board[i][j]){
                    case 'X': jp.setBackground(Color.BLUE); break;
                    case 'D': jp.setBackground(Color.YELLOW); break;
                    case 'G': jp.setBackground(Color.PINK); break;
                    case '.': case '*': jp.setBackground(Color.white); break;

                    default: jp.setBackground(Color.BLACK); break;
                }
                jPanels[i][j] = jp;
                add(jp);
            }
        }
        getComponentAt(25,25).setBackground(Color.BLACK);
    }

    public void updateFrame(char[][] board) {
        getContentPane().removeAll();
        System.out.println("sos");
        JPanel[][] jPanels = new JPanel[11][20];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                JPanel jp = new JPanel();
                char c = ' ';
                jp.setBackground(Color.BLACK);
                switch (board[i][j]){
                    case 'X': jp.setBackground(Color.BLUE); break;
                    case 'G': jp.add(new JLabel(new ImageIcon("src/com/company/assets/ghost_1.jpg"))); break;
                    case '.': jp.add(new JLabel(new ImageIcon("src/com/company/assets/small_food.png"))); break;
                    case '*': jp.add(new JLabel(new ImageIcon("src/com/company/assets/bigfood.png"))); break;
                    case '_': jp.add(new JLabel(new ImageIcon("src/com/company/assets/horiz_middle.png"))); break;
                    case 'P': jp.add(new JLabel(new ImageIcon("src/com/company/assets/horiz_right.png"))); break;
                    case 'O': jp.add(new JLabel(new ImageIcon("src/com/company/assets/horiz_left.png"))); break;
                    case '|': jp.add(new JLabel(new ImageIcon("src/com/company/assets/vert_middle.png"))); break;
                    case 'I': jp.add(new JLabel(new ImageIcon("src/com/company/assets/vert_top.png"))); break;
                    case 'U': jp.add(new JLabel(new ImageIcon("src/com/company/assets/vert_bottom.png"))); break;
                    case 'Y': jp.add(new JLabel(new ImageIcon("src/com/company/assets/left_down.png"))); break;
                    case 'T': jp.add(new JLabel(new ImageIcon("src/com/company/assets/right_left_down.png"))); break;
                    case 'R': jp.add(new JLabel(new ImageIcon("src/com/company/assets/right_down.png"))); break;
                    case 'E': jp.add(new JLabel(new ImageIcon("src/com/company/assets/left_up.png"))); break;
                    case 'W': jp.add(new JLabel(new ImageIcon("src/com/company/assets/right_left_up.png"))); break;
                    case 'Q': jp.add(new JLabel(new ImageIcon("src/com/company/assets/right_up.png"))); break;
                    case 'A': jp.add(new JLabel(new ImageIcon("src/com/company/assets/Pacman_up.jpg"))); break;
                    case 'S': jp.add(new JLabel(new ImageIcon("src/com/company/assets/Pacman_down.jpg"))); break;
                    case 'D': jp.add(new JLabel(new ImageIcon("src/com/company/assets/Pacman_left.jpg"))); break;
                    case 'F': jp.add(new JLabel(new ImageIcon("src/com/company/assets/Pacman_right.jpg"))); break;



                    default: jp.setBackground(Color.BLACK); break;
                }
                jPanels[i][j] = jp;
                add(jp);
            }
        }

        revalidate();
    }




}
