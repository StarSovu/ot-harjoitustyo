package com.mycompany.tournament;

import com.mycompany.tournament.logic.Group;
import com.mycompany.tournament.logic.GroupStage;
import com.mycompany.tournament.logic.Team;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many groups?");
        int answer = Integer.parseInt(scanner.nextLine());
        while (answer <= 0) {
            System.out.println("Number of groups must be positive. Try again");
            answer = Integer.parseInt(scanner.nextLine());
        }
        
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < answer; i++) {
            System.out.println("");
            System.out.println("Group " + i);
            
            System.out.print("Name of team 1: ");
            String team1 = scanner.nextLine();
            System.out.print("Name of team 2: ");
            String team2 = scanner.nextLine();
            System.out.print("Name of team 3: ");
            String team3 = scanner.nextLine();
            System.out.print("Name of team 4: ");
            String team4 = scanner.nextLine();
            
            ArrayList<Team> groupTeams = new ArrayList<>();
            groupTeams.add(new Team(team1));
            groupTeams.add(new Team(team2));
            groupTeams.add(new Team(team3));
            groupTeams.add(new Team(team4));

            Group group = new Group(groupTeams);
            groups.add(group);
        }
        
        System.out.println("");
        
        GroupStage groupStage = new GroupStage(groups);
        
        while (true) {
            System.out.println("In which group do you want to play? (Number 0 - "
                    + (answer - 1) + ")");
            int groupNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("");
            
            System.out.println("Which game do you want to play?");
            
            int homeTeamIndex = 0;
            int awayTeamIndex = 0;
            
            ArrayList<Team> teams = groupStage.getGroup(groupNumber).listTeamsInOriginalOrder();
            
            for (int i = 0; i < 6; i++) {
                if (awayTeamIndex == 3) {
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
                if (awayTeamIndex == 3) {
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
            }
        }
    }
