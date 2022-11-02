package com.company;

public class Util {
    public static String getDirectionsArrow(Directions dir) {
        String arrow = "";
        switch (dir){
            case Left -> arrow = "<-";
            case Right -> arrow = "->";
            case Up -> arrow = "^";
            case Down -> arrow = "V";
        }
        return arrow;
    }
    public static boolean hasWall(char ch) {
        return "POIUYTREWQ_-|".indexOf(ch) != -1;

    }
    public static char getPlayerDirectionChar(Directions direction) {
        char ch = ' ';
        switch (direction){
            case Up -> ch = 'A';
            case Down -> ch = 'S';
            case Left -> ch = 'D';
            case Right -> ch = 'F';
        }
        return ch;
    }

    public static boolean isPacman(char ch) {
        return "ASDF".indexOf(ch) != -1;
    }
}
