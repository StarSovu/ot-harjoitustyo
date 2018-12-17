package com.mycompany.tournament;

import com.mycompany.tournament.calculations.Calculations;
import com.mycompany.tournament.logic.Group;
import com.mycompany.tournament.logic.GroupStage;
import com.mycompany.tournament.logic.KnockoutStage;
import com.mycompany.tournament.logic.Team;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculations calculations = new Calculations();
        
        System.out.println("Start new? (0 = new tournament, 1 = get existing tournament)");
        System.out.println("");
        
        System.out.println("How many teams in a group?");
        int answer1 = Integer.parseInt(scanner.nextLine());
        while (answer1 <= 1) {
            System.out.println("Number of teams in a group must be at least 2. Try again.");
            answer1 = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("");
        
        System.out.println("How many groups?");
        int answer2 = Integer.parseInt(scanner.nextLine());
        while (!calculations.powerOfTwoChecker(answer2) || answer2 == 1) {
            System.out.println("Number of groups must be a power of two and greater than one. Try again.");
            answer2 = Integer.parseInt(scanner.nextLine());
        }
        
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < answer2; i++) {
            System.out.println("");
            System.out.println("Group " + i);
            
            ArrayList<Team> groupTeams = new ArrayList<>();
            
            for (int j = 0; j < answer1; j++) {
                System.out.print("Name of team " + (j + 1) + ": ");
                String team = scanner.nextLine();
                groupTeams.add(new Team(team));
            }

            Group group = new Group(groupTeams);
            groups.add(group);
        }
        
        System.out.println("");
        
        GroupStage groupStage = new GroupStage(groups);
        
        int numberOfGames = calculations.triangularNumber(answer1 - 1);
        
        boolean advance = false;
        
        while (!advance) {
            System.out.println("In which group do you want to play? (Number 0 - "
                        + (answer2 - 1) + ")");
            int groupNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("");
            
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
            while (gameNumber < 0 || gameNumber >= numberOfGames) {
                System.out.println("Number you gave is out of range. Try again.");
                gameNumber = Integer.parseInt(scanner.nextLine());
            }
            
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
        System.out.println("How many teams per group advance?");
        int answer3 = Integer.parseInt(scanner.nextLine());
        while (!calculations.powerOfTwoChecker(answer3) || answer3 >= answer1) {
            System.out.println("Number of advancing teams must be a power of two" +
                    " and less than the number of teams per group. Try again.");
            answer3 = Integer.parseInt(scanner.nextLine());
        }
        
        System.out.println("");
        ArrayList<Team> knockoutStageTeams = new ArrayList<>();
        System.out.println("All knockout stage round 1 games:");
        for (int i = 0; i < answer3; i++) {
            for (int j = 0; j < answer2; j+=2) {
                Team team1 = groupStage.getPlacementInGroup(i+1, j);
                Team team2 = groupStage.getPlacementInGroup(answer3-i, j+1);
                knockoutStageTeams.add(team1);
                knockoutStageTeams.add(team2);
                System.out.println(team1.getTeamName() + "-" + team2.getTeamName());
            }
        }
        
        System.out.println("");
        
        KnockoutStage knockoutStage = new KnockoutStage(knockoutStageTeams);
        
        advance = false;
        while (!advance) {
            
        }
        
    }
}
