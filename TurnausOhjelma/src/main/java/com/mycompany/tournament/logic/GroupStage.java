package com.mycompany.tournament.logic;

import java.util.ArrayList;

public class GroupStage {
    private ArrayList<Group> groups;
    
    public GroupStage(ArrayList<Group> groups) {
        this.groups = groups;
    }
    
    public void playGame(int groupNumber, int gameNumber, int goals1, int goals2) {
        this.groups.get(groupNumber).playGame(gameNumber, goals1, goals2);
    }
    
    public Team getPlacementInGroup(int place, int groupNumber) {
        return this.groups.get(groupNumber).getTeamInPlace(place);
    }
    
    public Group getGroup(int groupNumber) {
        return this.groups.get(groupNumber);
    }
}
