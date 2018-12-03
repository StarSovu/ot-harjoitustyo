package com.mycompany.tournament.logic;

import java.util.ArrayList;
import java.util.Collections;

public class Group {
    private ArrayList<Team> teams;
    private ArrayList<Team> sortedTeams;
    private boolean done;
    private int homeTeamIndex;
    private int awayTeamIndex;
    private boolean[] gamesPlayed;
    private Game[] gameResults;
    
    public Group(ArrayList<Team> teams) {
        this.teams = new ArrayList<>();
        this.teams = teams;
        
        this.sortedTeams = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            sortedTeams.add(teams.get(i));
        }
        
        int games = this.calculateNumberOfGames();
        this.gamesPlayed = new boolean[games];
        this.gameResults = new Game[games];
        for (int i = 0; i < games; i++) {
            gamesPlayed[i] = false;
            gameResults[i] = new Game(0, 0);
        }
        
        this.done = false;
        this.homeTeamIndex = 0;
        this.awayTeamIndex = 0;
    }
    
    public void playGame(int gameNumber, int goals1, int goals2) {
        if (gameNumber < 0 || gameNumber >= gamesPlayed.length || goals1 < 0 || goals2 < 0) {
            return;
        }
        this.setTeamsForGame(gameNumber);
        
        if (gamesPlayed[gameNumber]) {
            int oldGoals1 = gameResults[gameNumber].getGoals1();
            int oldGoals2 = gameResults[gameNumber].getGoals2();
            teams.get(this.homeTeamIndex).replaceGroupStageGame(new Game(oldGoals1, oldGoals2), new Game(goals1, goals2));
            teams.get(this.awayTeamIndex).replaceGroupStageGame(new Game(oldGoals2, oldGoals1), new Game(goals2, goals1));
        } else {
            teams.get(this.homeTeamIndex).addGroupStageGame(new Game(goals1, goals2));
            teams.get(this.awayTeamIndex).addGroupStageGame(new Game(goals2, goals1));
            gamesPlayed[gameNumber] = true;
        }
        
        gameResults[gameNumber] = new Game(goals1, goals2);
        this.arrangeTeams();
    }
    
    public void arrangeTeams() {
        Collections.sort(this.sortedTeams);
    }
    
    public boolean checkIfDone() {
        if (done) {
            return true;
        }
        if (this.numberOfGamesPlayed() == this.gamesPlayed.length) {
            this.done = true;
        }
        return done;
    }
    
    public boolean alreadyPlayed(int gameNumber) {
        if (gameNumber < 0 || gameNumber >= this.gamesPlayed.length) {
            return false;
        }
        
        return gamesPlayed[gameNumber];
    }
    
    public String gameResult(int gameNumber) {
        if (!this.alreadyPlayed(gameNumber)) {
            return "0-0";
        } else {
            return this.gameResults[gameNumber].toString();
        }
    }
    
    @Override
    public String toString() {
        return this.sortedTeams.toString();
    }
    
    public Team getTeamInPlace(int place) {
        return this.sortedTeams.get(place - 1);
    }
    
    public ArrayList<Team> listTeamsInOriginalOrder() {
        return this.teams;
    }
    
    public int numberOfGamesPlayed() {
        int numberOfGamesPlayed = 0;
        for (int i = 0; i < this.gamesPlayed.length; i++) {
            if (this.gamesPlayed[i]) {
                numberOfGamesPlayed++;
            }
        }
        return numberOfGamesPlayed;
    }
    
    private void setTeamsForGame(int gameNumber) {
        this.homeTeamIndex = 0;
        this.awayTeamIndex = 0;
        for (int i = 0; i <= gameNumber; i++) {
            if (this.awayTeamIndex == this.teams.size() - 1) {
                this.homeTeamIndex++;
                this.awayTeamIndex = homeTeamIndex + 1;
            } else {
                awayTeamIndex++;
            }
        }
    }
    
    private int calculateNumberOfGames() {
        int number = 1;
        if (teams.size() == 2) {
            return 1;
        }
        
        for (int i = 2; i < teams.size(); i++) {
            number += i;
        }
        return number;
    }
}
