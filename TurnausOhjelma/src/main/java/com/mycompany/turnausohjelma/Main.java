package com.mycompany.turnausohjelma;

import com.mycompany.turnausohjelma.Group;
import com.mycompany.turnausohjelma.Team;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Name of team 1: ");
        String team1 = scanner.nextLine();
        System.out.print("Name of team 2: ");
        String team2 = scanner.nextLine();
        System.out.print("Name of team 3: ");
        String team3 = scanner.nextLine();
        System.out.print("Name of team 4: ");
        String team4 = scanner.nextLine();
        
        ArrayList<Team> groupATeams = new ArrayList<>();
        groupATeams.add(new Team(team1));
        groupATeams.add(new Team(team2));
        groupATeams.add(new Team(team3));
        groupATeams.add(new Team(team4));
        
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
            System.out.println(groupATeams.get(homeTeamIndex).getTeamName() + "-" + groupATeams.get(awayTeamIndex).getTeamName());
            
            System.out.print(groupATeams.get(homeTeamIndex).getTeamName() + " goals: ");
            int goals1 = Integer.parseInt(scanner.nextLine());
            while (goals1 < 0) {
                System.out.print("Number of goals cannot be negative. Try again: ");
                goals1 = Integer.parseInt(scanner.nextLine());
            }
            
            System.out.print(groupATeams.get(awayTeamIndex).getTeamName() + " goals: ");
            int goals2 = Integer.parseInt(scanner.nextLine());
            while (goals2 < 0) {
                System.out.print("Number of goals cannot be negative. Try again: ");
                goals2 = Integer.parseInt(scanner.nextLine());
            }

            a.playGame(i, goals1, goals2);
            
            a.arrangeTeams();
            System.out.println(a);
            }
        
        
    }
    
}
