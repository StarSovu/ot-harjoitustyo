package com.mycompany.turnausohjelma;

import com.mycompany.turnausohjelma.Group;
import com.mycompany.turnausohjelma.Team;

public class Main {

    public static void main(String[] args) {
        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");
        Team team3 = new Team("Team3");
        Team team4 = new Team("Team4");
        
        Group a = new Group(team1, team2, team3, team4);
        
        a.playGame(1, 2);
        a.playGame(3, 5);
        a.playGame(0, 4);
        a.playGame(1, 7);
        a.playGame(0, 0);
        a.playGame(100, 0);
        
        a.arrangeTeams();
    }
    
}
