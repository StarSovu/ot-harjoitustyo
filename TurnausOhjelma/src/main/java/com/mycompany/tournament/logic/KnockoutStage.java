package com.mycompany.tournament.logic;
import java.util.ArrayList;

/**
 *
 * @author sovu
 */


public class KnockoutStage {
    private ArrayList<Team> teams;
    private int rounds;
    private int roundsComplete;
    private boolean[] gamesComplete;
    private Game[] games;
    
    public KnockoutStage(ArrayList<Team> teams) {
        this.teams = teams;
        
        this.rounds = 0;
        this.roundsComplete = 0;
        this.gamesComplete = new boolean[teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            this.gamesComplete[i] = false;
        }
        
        int numberOfTeams = teams.size();
        while (numberOfTeams > 1) {
            numberOfTeams /= 2;
            this.rounds++;
        }
    }
    
    public void playGame(int gameNumber, int homeGoals, int awayGoals) {
        
    }
    

}
