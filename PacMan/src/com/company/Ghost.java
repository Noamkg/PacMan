package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Ghost {
    public Position position;
    public Directions direction;
    public int id;
    private char covered;
    private boolean isActive;

    public Ghost(int id) {
        this.id = id;
        this.position = new Position(8 + this.id, 5);
        this.direction = Directions.Left;
        this.covered = ' ';
        this.isActive = false;
    }

    public Directions move(Board board, int framesElapsed) {

        if (board.getLegalDirections(this.position).size() > 2 ) {
            if (framesElapsed < 50) {
                return moveRandom(board);
            } else {
                return moveSmart(board);
            }
        } else {

            return moveContinue(board);
        }
    }

    private Directions moveRandom(Board board) {
        Random rnd = new Random();
        ArrayList<Directions> legalDirections = board.getLegalDirections(this.position);
        Directions randDirection = legalDirections.get(rnd.nextInt(legalDirections.size()));
        this.position.move(randDirection);
        return randDirection;
    }

    private Directions moveContinue(Board board) {
        if (isLegalMove(board)) {
            this.position.move(this.direction);
        } else moveRandom(board);
        return this.direction;
    }

    private Directions moveSmart(Board board) {
        Random rnd = new Random();
        ArrayList<Directions> legalDirections = board.getLegalDirections(this.position);
        Directions randDirection = legalDirections.get(rnd.nextInt(legalDirections.size()));
        this.position.move(randDirection);
        return randDirection;
    }

    private boolean isLegalMove(Board board) {
        Position newPos = this.position.copy();
        newPos.move(direction);
        return !Util.hasWall(board.getBoardAtPos(newPos));// && board.getBoardAtPos(newPos) != '_';
    }
    public void resetPosition() {
        this.position.x = 8 + this.id;
        this.position.y = 5;
        this.direction = Directions.Left;
    }

    public char getCovered() {
        return covered;
    }

    public void setCovered(char covered) {
        if (covered == 'D' || covered == 'G') {
            this.covered = ' ';
        }
        else {
            this.covered = covered;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        this.position.y = 3;
        this.covered = ' ';
    }
}
