package com.company;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GameManager implements NativeKeyListener {
    private Board board;
    private int gameStatus; // 0 - no game; 1 - game;
    private int framesElapsed;
    private Window window;


    public GameManager() {
        this.gameStatus = 0;


    }

    public void start(){
        if (gameStatus == 0) {

            this.gameStatus = 1;
            this.framesElapsed = 0;
            initGameElements();
            window = new Window(board.getBoard());
            startListener();

            while (gameStatus == 1) {

                board.moveGhosts(framesElapsed);
                board.movePlayer();
                board.print();
                window.updateFrame(board.getBoard());
                framesElapsed++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void initGameElements() {
        this.board = new Board();
    }

    private void startListener() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
        }
        GlobalScreen.addNativeKeyListener((NativeKeyListener) this);
        LogManager.getLogManager().reset();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        Directions newDir = getDirectionFromKeyCode(e.getKeyCode());
        board.changeDirection(newDir);
        window.updateFrame(board.getBoard());

    }
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

    }
    public Directions getDirectionFromKeyCode(int keyCode) {
        Directions newDir = board.getPLayerDir();
            //d = 32; 57421 = ->
            //s = 31; 57424 = V
            //a = 30; 57419 = <-
            //w = 17; 57416 = ^
            switch (keyCode) {
                case 32:
                case 57421:
                    newDir = Directions.Right;
                    break;
                case 31:
                case 57424:
                    newDir = Directions.Down;
                    break;
                case 30:
                case 57419:
                    newDir = Directions.Left;
                    break;
                case 17:
                case 57416:
                    newDir = Directions.Up;
                    break;
                default:
                    break;
            }

        return newDir;
    }

}
