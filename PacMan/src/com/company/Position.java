package com.company;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public void move(Directions direction ) {
        switch (direction) {
            case Up -> this.y -= 1;
            case Down -> this.y += 1;
            case Right -> this.x += 1;
            case Left -> this.x -=1;
        }
        this.y = (this.y + 11) %11;
        this.x = (this.x + 20) %20;

    }

    public Position copy() {return new Position(x, y); }
}
