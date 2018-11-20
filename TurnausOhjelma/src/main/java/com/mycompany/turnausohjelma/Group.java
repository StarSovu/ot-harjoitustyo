package com.mycompany.turnausohjelma;

import com.mycompany.turnausohjelma.Team;

import java.util.ArrayList;
import java.util.Collections;

public class Group {
    private ArrayList<Team> teams;
    private int gamesPlayed;
    private boolean done;
    private int homeTeamIndex;
    private int awayTeamIndex;
    
    public Group(Team team1, Team team2, Team team3, Team team4) {
        this.teams = new ArrayList<>();
        this.teams.add(team1);
        this.teams.add(team2);
        this.teams.add(team3);
        this.teams.add(team4);
        this.gamesPlayed = 0;
        this.done = false;
        this.homeTeamIndex = 0;
        this.awayTeamIndex = 1;
    }
    
    public void playGame(int goals1, int goals2) {
        if (done) {
            return;
        }
        
        teams.get(this.homeTeamIndex).addGroupStageGame(goals1, goals2);
        teams.get(this.awayTeamIndex).addGroupStageGame(goals2, goals1);
        
        gamesPlayed++;
        if (gamesPlayed == 6) {
            done = true;
            return;
        }
        
        this.updatePlayingTeams();
    }
    
    public void updatePlayingTeams() {
        if (awayTeamIndex == 3) {
            homeTeamIndex++;
            awayTeamIndex = homeTeamIndex + 1;
        } else {
            awayTeamIndex++;
        }
    }
    
    public void arrangeTeams() {
        System.out.println(this.teams);
        Collections.sort(this.teams);
        System.out.println(this.teams);
    }
}
