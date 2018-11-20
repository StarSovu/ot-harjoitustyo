package com.mycompany.turnausohjelma;

import com.mycompany.turnausohjelma.Group;
import com.mycompany.turnausohjelma.Team;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Team> groupATeams = new ArrayList<>();
        groupATeams.add(new Team("Team1"));
        groupATeams.add(new Team("Team2"));
        groupATeams.add(new Team("Team3"));
        groupATeams.add(new Team("Team4"));
        
        Group a = new Group(groupATeams);
        
        int homeTeamIndex = 0;
        int awayTeamIndex = 0;
        for (int i = 0; i < 6; i++) {
            if (awayTeamIndex == 3) {
                homeTeamIndex++;
                awayTeamIndex = homeTeamIndex + 1;
            } else {
                awayTeamIndex++;
            }
            System.out.println(groupATeams.get(homeTeamIndex).getTeamName() + " 1 - 2 " + groupATeams.get(awayTeamIndex).getTeamName());
            
            /*System.out.print(groupATeams.get(homeTeamIndex) + " goals: ");
            int goals1 = Integer.parseInt(scanner.nextLine());
            
            System.out.print(groupATeams.get(awayTeamIndex) + " goals: ");
            int goals2 = Integer.parseInt(scanner.nextLine());
            */
            
            int goals1 = 1;
            int goals2 = 2;
            
            a.playGame(i, goals1, goals2);
            
            a.arrangeTeams();
            System.out.println(a);
            }
        
        
    }
    
}
