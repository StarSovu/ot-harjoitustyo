package com.mycompany.tournament.logic;

import com.mycompany.tournament.calculations.Calculations;
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
    private Calculations calculations;
    
    public Group(ArrayList<Team> teams) {
        this.teams = new ArrayList<>();
        this.teams = teams;
        
        this.sortedTeams = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            sortedTeams.add(teams.get(i));
        }
        
        this.calculations = new Calculations();
        this.createGames(calculations.triangularNumber(teams.size() - 1));
        
        this.done = false;
        this.homeTeamIndex = 0;
        this.awayTeamIndex = 0;
    }
    
    private void createGames(int games) {
        this.gamesPlayed = new boolean[games];
        this.gameResults = new Game[games];
        
        for (int i = 0; i < games; i++) {
            gamesPlayed[i] = false;
            gameResults[i] = new Game(0, 0);
        }
    }
    
    public void playGame(int gameNumber, int goals1, int goals2) {
        if (gameNumber < 0 || gameNumber >= gamesPlayed.length || goals1 < 0 || goals2 < 0) {
            return;
        }
        this.setTeamsForGame(gameNumber);
        
        addGameToTeams(gameNumber, goals1, goals2);
        
        gameResults[gameNumber] = new Game(goals1, goals2);
        this.arrangeTeams();
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

    private void addGameToTeams(int gameNumber, int goals1, int goals2) {
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
    }
    
    private void arrangeTeams() {
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
    
    public ArrayList<Team> listTeamsInSortedOrder() {
        return this.sortedTeams;
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
}
