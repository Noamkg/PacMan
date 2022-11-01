package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    /**
     * The Board is 20x11
     * This Will be the board:


        0 1 2 3 4 5 6 7 8 9 -->               20
     0  X X X X X X X X X X X X X X X X X X X X
     1  X * . . . X . . . . . . . . X . . . . X
     2  X . X X . X . X X X X X X . X . X X . X
     3  X . X . . . . . . . D . . . . . . X . X
     4  X . X . X X . X X     X X . X X . X . X
     |  X . . . . . . X G G G G X . . . . . . X
     |  X . X . X X . X X X X X X . X X . X . X
     V  X . X . . . . . . . . . . . . . . X . X
        X . X X . X . X X X X X X . X . X X . X
        X . . . . X . . . . . . . . X . . . * X
     11 X X X X X X X X X X X X X X X X X X X X


     R _ _ _ _ T _ _ _ _ _ _ _ _ T _ _ _ _ Y
     | * . . . | . . . . . . . . | . . . . |
     | . R P . U . O _ _ _ _ P . U . O Y . |
     | . | . . . . . . . D . . . . . . | . |
     U . U . O P . R _ - - _ Y . O P . U . U
     . . . . . . . | G G G G | . . . . . . .
     I . I . O P . Q _ _ _ _ E . O P . I . I
     | . | . . . . . . . . . . . . . . | . |
     | . Q P . I . O _ _ _ _ P . I . O E . |
     | . . . . | . . . . . . . . | . . . * |
     Q _ _ _ _ W _ _ _ _ _ _ _ _ W _ _ _ _ E

     * Small Food is Represented By "." and large is represented by "*"
     * The player is represented by "D" and the ghosts by "G"
     * '_' horiz middle pipe || 'p' horiz end right || 'o' horiz end left
     * '|' vert middle || 'I' vert top || 'U' vert bottom
     * 'Y' left down || 'T' left Right Down || 'R' right down
     * 'E' left up || 'W' left right up || 'Q' right Up
     *
     *
     */
    private int boardHeight = 11;
    private int boardWidth = 20;
    private char[][] board;
    private Player player;
    private GhostManager ghostManager;

    public Board() {
        this.board = new char[boardHeight][boardWidth];
        createStarterBoard();
        this.player = new Player();
        this.ghostManager = new GhostManager();

    }

    /**
     *
     * @param
     * @return
     */
    public void movePlayer() {
        if (isLegalMove(this.player.direction)) {
            Food food = updatePlayerMoveOnBoard();
            this.player.addScore(food.getPoints());
        }
        if (isBoardEmpty()) {
            resetBoard();
        }
    }
    public void moveGhosts(int framesElapsed) {
        if (framesElapsed == 10) {
            ghostManager.getGhost(ghostManager.getActiveGhostNum()).setActive(true);
        }
        updateGhostMoveOnBoard(framesElapsed);
    }

    public void changeDirection(Directions direction) {
        player.direction = direction;
        setBoardAtPos(this.player.position, Util.getPlayerDirectionChar(getPLayerDir()));

    }
    public void createStarterBoard() {
        String boardStr = "R____T________T____Y\n" +
                "|*...|........|....|\n" +
                "|.RP.U.O____P.U.OY.|\n" +
                "|.|.......D......|.|\n" +
                "U.U.OP.R_--_Y.OP.U.U\n" +
                ".......|GGGG|.......\n" +
                "I.I.OP.Q____E.OP.I.I\n" +
                "|.|..............|.|\n" +
                "|.QP.I.O____P.I.OE.|\n" +
                "|....|........|...*|\n" +
                "Q____W________W____E";
        String[] rowStrings =  boardStr.split("\n");
        for (int i = 0; i < rowStrings.length; i++) {
             String[] row = rowStrings[i].split("");
             for (int j = 0; j < row.length; j++) {
                 board[i][j] = row[j].charAt(0);
             }
        }



    }
    public void print() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                String letter = board[i][j] + "";
                letter = colorLetter(letter);
                System.out.print(letter);
            }
            System.out.println();
        }
        System.out.println("Direction: " + Util.getDirectionsArrow(getPLayerDir()));
        System.out.println("Score: " + player.getScore());
    }
    public Directions getPLayerDir() {
        return player.direction;
    }
    public Food updatePlayerMoveOnBoard() {
        setBoardAtPos(this.player.position, ' ' );
        this.player.move(this.player.direction);
        char foodChar = getBoardAtPos(this.player.position);
        setBoardAtPos(this.player.position, Util.getPlayerDirectionChar(getPLayerDir()));
        return new Food(foodChar);
    }
    public void updateGhostMoveOnBoard(int framesElapsed) {
        for (int i = 0; i < ghostManager.getGhostNum(); i++) {
            Ghost ghost = this.ghostManager.getGhost(i);
            setBoardAtPos(ghost.position, ghost.getCovered());
            ghost.move(this, framesElapsed);
            ghost.setCovered(getBoardAtPos(ghost.position));
            setBoardAtPos(ghost.position, 'G');
        }
    }
    private boolean isLegalMove(Directions direction) {
        Position newPos = this.player.position.copy();
        newPos.move(direction);
        return !Util.hasWall(getBoardAtPos(newPos)) ; //newPos.x >= 0 && newPos.x <20 && newPos.y >= 0 && newPos.y <11 &&

    }
    char getBoardAtPos(Position pos) {
        return board[pos.y][pos.x];
    }
    private void setBoardAtPos(Position pos, char val) {
        board[pos.y][pos.x] = val;
    }
    private String colorLetter(String letter) {

        switch (letter) {
            case "X":
                letter = "\u001B[34m" + letter + "\u001B[0m";
                break;
            case "D":
                letter = "\u001B[33m" + letter + "\u001B[0m";
        }

        return letter + " ";
    }
    private boolean isBoardEmpty() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (Food.isTypeOfFoodChar(board[i][j])) { return false; }
            }
        }
        return true;
    }
    private void resetBoard() {
        createStarterBoard();
        player.resetPosition();
    }

    public ArrayList<Directions> getLegalDirections(Position pos) {
        ArrayList<Directions> legalDirections = new ArrayList<Directions>();
        for (int i = 0; i < 4; i++) {
            Position newPos = pos.copy();
            newPos.move(Directions.values()[i]);
            if( !Util.hasWall(getBoardAtPos(newPos))) {legalDirections.add(Directions.values()[i]);}
        }
        return legalDirections;
    }

    public char[][] getBoard() {
        return board;
    }

}
