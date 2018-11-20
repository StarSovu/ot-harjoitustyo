package com.mycompany.turnausohjelma;

/**
 *
 * @author sovu
 */
public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private boolean done;
    private int goals1;
    private int goals2;
    
    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.done = false;
        this.goals1 = 0;
        this.goals2 = 0;
    }
    
    public void playGame(int homeGoals, int awayGoals) {
        this.goals1 = homeGoals;
        this.goals2 = awayGoals;
        this.done = true;
    }
    
    public int getHomeGoals() {
        return goals1;
    }
    
    public int getAwayGoals() {
        return goals2;
    }
    
    public boolean tie() {
        return (goals1 == goals2);
    }
    
    public boolean homeVictory() {
        return (goals1 > goals2);
    }
    
    public boolean awayVictory() {
        return (goals2 > goals1);
    }
}
