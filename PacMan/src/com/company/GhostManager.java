package com.company;

import java.util.ArrayList;

public class GhostManager {
    private ArrayList<Ghost> ghosts;
    private int activeGhostNum;


    public GhostManager() {
        ghosts = new ArrayList<Ghost>();
        for (int i = 0; i < 4; i++)  { ghosts.add(new Ghost(i));  }
        activeGhostNum = 0;
    }

    public int getActiveGhostNum() {
        return activeGhostNum;
    }

    public void setActiveGhostNum(int activeGhostNum) {
        this.activeGhostNum = activeGhostNum;
    }

    public int getGhostNum() {
        return this.ghosts.size();
    }

    public Ghost getGhost(int id) {
        return ghosts.get(id);
    }


}
