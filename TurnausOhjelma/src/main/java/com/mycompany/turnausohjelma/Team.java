/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.turnausohjelma;

/**
 *
 * @author sovu
 */
public class Team implements Comparable<Team> {
    private String name;
    private int gamesplayed;
    private int wins;
    private int ties;
    private int losses;
    private int points;
    private int goalsFor;
    private int goalsAgainst;
    
    public Team(String teamName) {
        this.name = teamName;
        this.gamesplayed = 0;
        this.wins = 0;
        this.ties = 0;
        this.losses = 0;
        this.points = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
    }
    
    public void addGroupStageGame(int goalsFor, int goalsAgainst) {
        this.gamesplayed++;
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        
        if (goalsFor > goalsAgainst) {
            this.wins++;
            this.points += 3;
        } else if (goalsFor < goalsAgainst) {
            this.losses++;
        } else {
            this.ties++;
            this.points++;
        }
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
    
    @Override
    public String toString() {
        return this.name;
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
