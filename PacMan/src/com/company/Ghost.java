package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Ghost {
    public Position position;
    public Directions direction;
    public int id;
    private char covered;
    private boolean isActive;
    private Directions prevDirection;

    public Ghost(int id) {
        this.id = id;
        this.position = new Position(8 + this.id, 5);
        this.direction = Directions.Left;
        this.covered = ' ';
        this.isActive = false;
        this.prevDirection = getOppositeDirection(this.direction);
    }

    public Directions move(Board board, int framesElapsed) {

        if (board.getLegalDirections(this.position).size() > 2 ) {
            if (framesElapsed < 10) {
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
        if ( legalDirections.size() > 1) {
            legalDirections.remove(this.prevDirection);
        }
        Directions randDirection = legalDirections.get(rnd.nextInt(legalDirections.size()) );
        this.position.move(randDirection);
        this.prevDirection = getOppositeDirection(this.direction);
        this.direction = randDirection;
        return randDirection;
    }

    private Directions moveContinue(Board board) {

        if (isLegalMove(board)) {
            this.position.move(this.direction);

        } else  {
            moveRandom(board);
        }
        return this.direction;
    }

    private Directions moveSmart(Board board) {
        Random rnd = new Random();
        ArrayList<Directions> legalDirections = board.getLegalDirections(this.position);

        Directions bestDir = legalDirections.get(0);
        int bestDirDistance = distanceToPacman(board, bestDir);
        for (int i = 0; i < legalDirections.size(); i++) {
            if (distanceToPacman(board, legalDirections.get(i)) < bestDirDistance) {
                bestDir = legalDirections.get(i);
                bestDirDistance = distanceToPacman(board, bestDir);
            }
        }
        this.position.move(bestDir);
        return bestDir;
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

    private Directions getOppositeDirection(Directions dir) {
        Directions oppositeDir = Directions.Right;
        switch (dir) {
            case Up -> oppositeDir = Directions.Down;
            case Left -> oppositeDir = Directions.Right;
            case Right -> oppositeDir = Directions.Left;
            case Down -> oppositeDir = Directions.Up;
        }
        return oppositeDir;
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

    private int distanceToPacman(Board board, Directions direction) {
        Position pacmanPos = board.getPlayerPosition();
        Position newPos = this.position.copy();
        newPos.move(direction);
//
//        if (Util.isPacman(board.getBoardAtPos(newPos))) {
//            return 3
//        }

        int distance = Math.abs(newPos.x - pacmanPos.x) + Math.abs(newPos.y - pacmanPos.y);
        return distance;
    }

}

