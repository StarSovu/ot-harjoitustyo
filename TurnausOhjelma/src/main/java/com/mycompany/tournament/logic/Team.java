/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tournament.logic;

/**
 *
 * @author sovu
 */
public class Team implements Comparable<Team> {
    private String name;
    private int gamesPlayed;
    private int wins;
    private int ties;
    private int losses;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    private boolean in;
    
    public Team(String teamName) {
        this.name = teamName;
        this.gamesPlayed = 0;
        this.wins = 0;
        this.ties = 0;
        this.losses = 0;
        this.points = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.in = true;
    }
    
    public void addGroupStageGame(Game game) {
        this.gamesPlayed++;
        this.goalsFor += game.getGoals1();
        this.goalsAgainst += game.getGoals2();
        
        this.addPoints(game);
    }
    
    public void replaceGroupStageGame(Game oldGame, Game newGame) {
        this.goalsFor += newGame.getGoals1();
        this.goalsFor -= oldGame.getGoals1();
        this.goalsAgainst += newGame.getGoals2();
        this.goalsAgainst -= oldGame.getGoals2();
        
        this.addPoints(newGame);
        
        if (oldGame.victory()) {
            this.wins--;
            this.points -= 3;
        } else if (oldGame.loss()) {
            this.losses--;
        } else {
            this.ties--;
            this.points--;
        }
    }
    
    private void addPoints(Game game) {
        if (game.victory()) {
            this.wins++;
            this.points += 3;
        } else if (game.loss()) {
            this.losses++;
        } else {
            this.ties++;
            this.points++;
        }
    }
    
    public void loseKnockoutStage() {
        this.in = false;
    }
    
    public boolean isIn() {
        return this.in;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public int getGD() {
        return this.goalsFor - this.goalsAgainst;
    }
    
    public int getGoalsFor() {
        return this.goalsFor;
    }
    
    public int getGoalsAgainst() {
        return this.goalsAgainst;
    }
    
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }
    
    public String getTeamName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.points + " points, " + this.gamesPlayed
                + " " + this.wins + this.ties + this.losses + " "
                + this.goalsFor + "-" + this.goalsAgainst;
    }
    
    @Override
    public int compareTo(Team team2) {
        if (this.points != team2.getPoints()) {
            return team2.getPoints() - this.points;
        }
        
        if (this.getGD() != team2.getGD()) {
            return team2.getGD() - this.getGD();
        }
        
        if (this.goalsFor != team2.getGoalsFor()) {
            return team2.getGoalsFor() - this.goalsFor;
        }
        
        return 0;
    }
    
}
