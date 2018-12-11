package com.mycompany.tournament;

import com.mycompany.tournament.logic.Group;
import com.mycompany.tournament.logic.GroupStage;
import com.mycompany.tournament.logic.Team;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many teams in a group?");
        int answer1 = Integer.parseInt(scanner.nextLine());
        while (answer1 <= 1) {
            System.out.println("Number of teams in a group must be at least 2. Try again.");
            answer1 = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("");
        
        System.out.println("How many groups?");
        int answer2 = Integer.parseInt(scanner.nextLine());
        while (answer2 <= 0) {
            System.out.println("Number of groups must be positive. Try again.");
            answer2 = Integer.parseInt(scanner.nextLine());
        }
        
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < answer2; i++) {
            System.out.println("");
            System.out.println("Group " + i);
            
            ArrayList<Team> groupTeams = new ArrayList<>();
            
            for (int j = 0; j < answer1; j++) {
                System.out.print("Name of team " + (j+1) + ": ");
                String team = scanner.nextLine();
                groupTeams.add(new Team(team));
            }

            Group group = new Group(groupTeams);
            groups.add(group);
        }
        
        System.out.println("");
        
        GroupStage groupStage = new GroupStage(groups);
        
        int numberOfGames = 0;
        for (int i = 1; i < answer1; i++) {
            numberOfGames += i;
        }
        
        boolean advance = false;
        
        while (!advance) {
            int groupNumber = 0;
            
            if (answer2 > 1) {
                System.out.println("In which group do you want to play? (Number "
                        + "0 - " + (answer2 - 1) + ")");
                groupNumber = Integer.parseInt(scanner.nextLine());
                System.out.println("");
            }
            
            System.out.println("Which game do you want to play?");
            
            int homeTeamIndex = 0;
            int awayTeamIndex = 0;
            
            ArrayList<Team> teams = groupStage.getGroup(groupNumber).listTeamsInOriginalOrder();
            
            for (int i = 0; i < numberOfGames; i++) {
                if (awayTeamIndex == answer1 - 1) {
                    homeTeamIndex++;
                    awayTeamIndex = homeTeamIndex + 1;
                } else {
                    awayTeamIndex++;
                }
                String alreadyPlayedNote = "";
                if (groupStage.getGroup(groupNumber).alreadyPlayed(i)) {
                    alreadyPlayedNote = " - replace current result "
                            + groupStage.getGroup(groupNumber).gameResult(i);
                }
                System.out.println(i + ": " + teams.get(homeTeamIndex).getTeamName()
                        + "-" + teams.get(awayTeamIndex).getTeamName() + alreadyPlayedNote);
            }
            
            int gameNumber = Integer.parseInt(scanner.nextLine());
            homeTeamIndex = 0;
            awayTeamIndex = 0;
            
            for (int i = 0; i <= gameNumber; i++) {
                if (awayTeamIndex == answer1 - 1) {
                    homeTeamIndex++;
                    awayTeamIndex = homeTeamIndex + 1;
                } else {
                    awayTeamIndex++;
                }
            }
            
            System.out.print(teams.get(homeTeamIndex).getTeamName() + " goals: ");
            int goals1 = Integer.parseInt(scanner.nextLine());
            while (goals1 < 0) {
                System.out.print("Number of goals cannot be negative. Try again: ");
                goals1 = Integer.parseInt(scanner.nextLine());
            }
            
            System.out.print(teams.get(awayTeamIndex).getTeamName() + " goals: ");
            int goals2 = Integer.parseInt(scanner.nextLine());
            while (goals2 < 0) {
                System.out.print("Number of goals cannot be negative. Try again: ");
                goals2 = Integer.parseInt(scanner.nextLine());
            }

            groupStage.playGame(groupNumber, gameNumber, goals1, goals2);
            
            System.out.println("");
            System.out.println("Current situation:");
            System.out.println(groupStage.getGroup(groupNumber));
            System.out.println("");
            
            if (groupStage.checkIfDone()) {
                System.out.println("You have completed all group stage games.");
                System.out.println("Advance to Knockout Stage? (y/n)");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    advance = true;
                } else {
                    System.out.println("");
                }
            }
        }
        
        System.out.println("");
        
        boolean powerOf2Groups = true;
        for (int i = answer2; i > 1; i /= 2) {
            if (i % 2 == 1) {
                powerOf2Groups = false;
                break;
            }
        }
        
        if (powerOf2Groups) {
            System.out.println("How many teams per group advance?");
            int advancingTeams = Integer.parseInt(scanner.nextLine());
            
            boolean powerOf2Teams = true;
            for (int i = answer2; i > 1; i /= 2) {
                if (i % 2 == 1) {
                    powerOf2Groups = false;
                }
            }
            
            while (!powerOf2Teams || advancingTeams < 1 || advancingTeams >= answer1) {
                System.out.println("Number of teams advancing not acceptable. Try again.");
                advancingTeams = Integer.parseInt(scanner.nextLine());
            
                powerOf2Teams = true;
                for (int i = answer2; i > 1; i /= 2) {
                    if (i % 2 == 1) {
                        powerOf2Groups = false;
                    }
                }
            }
        }
        
    }
}
