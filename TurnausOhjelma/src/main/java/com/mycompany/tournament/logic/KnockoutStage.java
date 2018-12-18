package com.mycompany.tournament.logic;

import com.mycompany.tournament.calculations.Calculations;
import java.util.ArrayList;

public class KnockoutStage {
    ArrayList<Team> teams;
    int roundNumber;
    int originalRoundNumber;
    int gamesPlayedInRound;
    boolean done;
    Calculations calculations;
    
    public KnockoutStage(ArrayList<Team> teams) {
        this.teams = teams;
        this.calculations = new Calculations();
        this.roundNumber = calculations.log2(teams.size());
        this.originalRoundNumber = this.roundNumber;
        this.done = false;
    }
    
    public void playGame(int gameNumber, Game game) {
        if (this.roundNumber < 2) {
            return;
        }
        if (game.loss()) {
            teams.get(gameNumber * 2).loseKnockoutStage();
        } else {
            teams.get(gameNumber * 2 + 1).loseKnockoutStage();
        }
        
        this.gamesPlayedInRound++;
    }
    
    public boolean roundDone() {
        return (gamesPlayedInRound != teams.size() / 2);
    }
    
    public void completeRound() {
        if (!this.roundDone() || done) {
            return;
        }
        if (roundNumber == 1) {
            done = true;
        }
        if (roundNumber > 2) {
            this.removeTeamsThatAreOut();
        }
        this.roundNumber--;
        this.gamesPlayedInRound = 0;
    }
    
    private void removeTeamsThatAreOut() {
        ArrayList<Team> teams2 = new ArrayList<>();
        
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).isIn()) {
                teams2.add(teams.get(i));
            }
        }
        
        teams = teams2;
    }
    
    public ArrayList<Team> getCurrentTeams() {
        return this.teams;
    }
    
    public int getNumberOfRemainingTeams() {
        return this.teams.size();
    }
}
