package com.mycompany.tournament.logic;

import com.mycompany.tournament.calculations.Calculations;
import java.util.ArrayList;

public class KnockoutStage {
    ArrayList<Team> teams;
    int roundNumber;
    int originalRoundNumber;
    boolean bronzeNeeded;
    boolean finalNeeded;
    int gamesPlayedInRound;
    boolean[] gamesPlayedInRoundTrue;
    boolean done;
    Calculations calculations;
    
    public KnockoutStage(ArrayList<Team> teams) {
        this.teams = teams;
        this.calculations = new Calculations();
        this.roundNumber = calculations.log2(teams.size());
        this.gamesPlayedInRound = 0;
        this.originalRoundNumber = this.roundNumber;
        this.bronzeNeeded = false;
        this.finalNeeded = (this.roundNumber == 1);
        
        this.gamesPlayedInRoundTrue = new boolean[teams.size() / 2];
        for (int i = 0; i < teams.size() / 2; i++) {
            this.gamesPlayedInRoundTrue[i] = false;
        }
            
        this.done = false;
    }
    
    public void playGame(int gameNumber, Game game) {
        if (this.roundNumber < 2 || gamesPlayedInRoundTrue[gameNumber]) {
            return;
        }
        if (game.loss()) {
            teams.get(gameNumber * 2).loseKnockoutStage();
        } else {
            teams.get(gameNumber * 2 + 1).loseKnockoutStage();
        }
        
        this.gamesPlayedInRound++;
        this.gamesPlayedInRoundTrue[gameNumber] = true;
    }
    
    public void playThirdPlace(Game game) {
        if (!bronzeNeeded) {
            return;
        }
        
        this.bronzeNeeded = false;
        
        if (game.loss()) {
            teams.add(2, teams.get(3));
            teams.remove(4);
        }
    }
    
    public void playFinal(Game game) {
        if (!finalNeeded) {
            return;
        }
        
        this.finalNeeded = false;
        
        if (game.loss()) {
            teams.add(0, teams.get(1));
            teams.remove(2);
        }
    }
    
    public boolean roundDone() {
        return (gamesPlayedInRound == teams.size() / 2);
    }
    
    public void completeRound() {
        if (!this.roundDone() || done) {
            return;
        }
        
        if (roundNumber == 1) {
            done = true;
        } else if (roundNumber > 2) {
            this.requiredWhenRoundNumberGreaterThanTwo();
        } else if (roundNumber == 2) {
            this.requiredWhenRoundNumberTwo();
        }
        
        this.roundNumber--;
        this.gamesPlayedInRound = 0;
    }
    
    private void requiredWhenRoundNumberGreaterThanTwo() {
        this.removeTeamsThatAreOut();
        this.gamesPlayedInRoundTrue = new boolean[teams.size() / 2];
        for (int i = 0; i < teams.size() / 2; i++) {
            this.gamesPlayedInRoundTrue[i] = false;
        }
    }
    
    private void requiredWhenRoundNumberTwo() {
        this.finalNeeded = true;
        this.bronzeNeeded = true;
        
        ArrayList<Team> teams2 = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            if (teams.get(i).isIn()) {
                teams2.add(teams.get(i));
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!teams.get(i).isIn()) {
                teams2.add(teams.get(i));
            }
        }
        
        teams = teams2;
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
    
    public int getRoundNumber() {
        return this.roundNumber;
    }
    
    public int getOriginalRoundNumber() {
        return this.originalRoundNumber;
    }
    
    public boolean alreadyPlayed(int gameNumber) {
        if (gameNumber < 0 || gameNumber >= this.teams.size() / 2) {
            return false;
        }
        return this.gamesPlayedInRoundTrue[gameNumber];
    }
}
