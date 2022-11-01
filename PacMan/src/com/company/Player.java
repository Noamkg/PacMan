package com.company;

public class Player {
    public Position position;
    public Directions direction;
    private int score;
    private int screenNumber;


    public Player() {
        this.position = new Position(10, 3);
        this.direction = Directions.Left;
        this.score = 0;
        this.screenNumber = 0;
    }

    public void move(Directions direction) {
        this.position.move(direction);
    }
    public void resetPosition() {
        this.position.x = 10;
        this.position.y = 3;
        this.direction = Directions.Left;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void addScore(int score) {this.score += score; }
    public int getScreenNumber() {
        return screenNumber;
    }
    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }
    public void addScreenNumber(int newScreenNum) {this.screenNumber += newScreenNum; }
}
