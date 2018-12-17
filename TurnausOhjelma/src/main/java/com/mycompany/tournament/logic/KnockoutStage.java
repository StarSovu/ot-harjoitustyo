package com.mycompany.tournament.logic;

import com.mycompany.tournament.calculations.Calculations;
import java.util.ArrayList;

public class KnockoutStage {
    ArrayList<Team> teams;
    int roundNumber;
    int gamesPlayedInRound;
    boolean done;
    Calculations calculations;
    
    public KnockoutStage(ArrayList<Team> teams) {
        this.teams = teams;
        this.calculations = new Calculations();
        this.roundNumber = calculations.log2(teams.size());
        this.done = false;
    }
    
    public void playNormalGame(int gameNumber, boolean firstTeamWins) {
        
        if (!firstTeamWins) {
            teams.get(gameNumber * 2).loseKnockoutStage();
        } else {
            teams.get(gameNumber * 2 + 1).loseKnockoutStage();
        }
        
        this.gamesPlayedInRound++;
    }
    
    public boolean completeRound() {
        if (gamesPlayedInRound != teams.size() / 2 || done) {
            return false;
        }
        if (roundNumber == 1) {
            done = true;
        }
        if (roundNumber > 2) {
            this.removeTeamsThatAreOut();
        }
        this.roundNumber--;
        this.gamesPlayedInRound = 0;
        return true;
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
}
