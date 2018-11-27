package com.mycompany.tournament.logic;

import java.util.ArrayList;

public class GroupStage {
    private ArrayList<Group> groups;
    private boolean done;
    
    public GroupStage(ArrayList<Group> groups) {
        this.groups = groups;
        this.done = false;
    }
    
    public void playGame(int groupNumber, int gameNumber, int goals1, int goals2) {
        this.groups.get(groupNumber).playGame(gameNumber, goals1, goals2);
        this.groups.get(groupNumber).arrangeTeams();
    }
    
    public Team getPlacementInGroup(int place, int groupNumber) {
        return this.groups.get(groupNumber).getTeamInPlace(place);
    }
    
    public Group getGroup(int groupNumber) {
        return this.groups.get(groupNumber);
    }
    
    public boolean checkIfDone() {
        if (done) {
            return done;
        }
        
        for (int i = 0; i < this.groups.size(); i++) {
            if (!this.groups.get(i).checkIfDone()) {
                return false;
            }
        }
        
        this.done = true;
        return true;
    }
}
