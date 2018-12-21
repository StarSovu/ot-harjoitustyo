package com.mycompany.tournament;

import com.mycompany.tournament.calculations.Calculations;
import com.mycompany.tournament.logic.Game;
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
            for (int j = 0; j < answer2; j += 2) {
                Team team1 = groupStage.getPlacementInGroup(i + 1, j);
                Team team2 = groupStage.getPlacementInGroup(answer3 - i, j + 1);
                knockoutStageTeams.add(team1);
                knockoutStageTeams.add(team2);
                System.out.println(team1.getTeamName() + "-" + team2.getTeamName());
            }
        }
        
        KnockoutStage knockoutStage = new KnockoutStage(knockoutStageTeams);
        
        if (knockoutStage.getRoundNumber() != 1) {
            advance = false;
        }
        
        
        while (!advance) {
            System.out.println("");
            
            ArrayList<Team> remaining = knockoutStage.getCurrentTeams();
            
            System.out.println("Which game do you want to play?");
            int knockoutSize = remaining.size();
            
            for (int i = 0; i < knockoutSize; i += 2) {
                if (!knockoutStage.alreadyPlayed(i / 2)) {
                    System.out.println(i / 2 + ": " + remaining.get(i).getTeamName() + " - " + remaining.get(i + 1).getTeamName());
                }
            }
            
            System.out.println("");
            int gamePlayed = Integer.parseInt(scanner.nextLine());
            while (gamePlayed < 0 || gamePlayed >= knockoutSize / 2) {
                System.out.println("Number out of range. Try again.");
                gamePlayed = Integer.parseInt(scanner.nextLine());
            }
            
            String teamName1 = remaining.get(2 * gamePlayed).getTeamName();
            String teamName2 = remaining.get(2 * gamePlayed + 1).getTeamName();
            System.out.println("");
            
            Game game = playKnockoutGame(teamName1, teamName2, scanner);
            
            knockoutStage.playGame(gamePlayed, game);
            knockoutStage.completeRound();
            
            if (knockoutStage.getRoundNumber() == 1) {
                advance = true;
            } 
        }
        
        if (knockoutStage.getOriginalRoundNumber() != 1) {
            System.out.println("");
            
            String teamName1 = knockoutStage.getCurrentTeams().get(2).getTeamName();
            String teamName2 = knockoutStage.getCurrentTeams().get(3).getTeamName();
            System.out.println("Third place match: " + teamName1 + " - " + teamName2);
            
            Game thirdPlace = playKnockoutGame(teamName1, teamName2, scanner);
            knockoutStage.playThirdPlace(thirdPlace);
        }
        
        System.out.println("");
            
        String teamName1 = knockoutStage.getCurrentTeams().get(0).getTeamName();
        String teamName2 = knockoutStage.getCurrentTeams().get(1).getTeamName();
        System.out.println("Final: " + teamName1 + " - " + teamName2);

        Game finalGame = playKnockoutGame(teamName1, teamName2, scanner);
        knockoutStage.playFinal(finalGame);
        
        System.out.println("");
        System.out.println("Final results:");
        ArrayList<Team> topTeams = knockoutStage.getCurrentTeams();
        
        int limit = 4;
        if (knockoutStage.getOriginalRoundNumber() == 1) {
            limit = 2;
        }
        
        for (int i = 0; i < limit; i++) {
            String teamName = topTeams.get(i).getTeamName();
            System.out.println((i + 1) + ". " + teamName);
        }
    }
    
    public static Game playKnockoutGame(String teamName1, String teamName2, Scanner scanner) {
        System.out.print(teamName1 + " goals: ");
        int goals1 = Integer.parseInt(scanner.nextLine());
        while (goals1 < 0) {
            System.out.println("Number of goals cannot be negative. Try again");
        }

        System.out.print(teamName2 + " goals: ");
        int goals2 = Integer.parseInt(scanner.nextLine());
        while (goals2 < 0) {
            System.out.println("Number of goals cannot be negative. Try again");
        }

        Game game;

        if (goals1 != goals2) {
            game = new Game(goals1, goals2);
        } else {
            System.out.println("Type extra time goals. Note: Only count goals scored during extra time!");

            System.out.print(teamName1 + " goals: ");
            int extraGoals1 = Integer.parseInt(scanner.nextLine());
            while (extraGoals1 < 0) {
                System.out.println("Number of goals cannot be negative. Try again");
            }

            System.out.print(teamName2 + " goals: ");
            int extraGoals2 = Integer.parseInt(scanner.nextLine());
            while (extraGoals2 < 0) {
                System.out.println("Number of goals cannot be negative. Try again");
            }

            game = new Game(goals1, goals2, extraGoals1, extraGoals2);

            if (game.needsPenalties()) {
                System.out.println("");
                System.out.println("Penalties:");
                int penaltyCounter = 0;
                while (game.needsPenalties()) {
                    penaltyCounter++;

                    String penaltyTeam;
                    if (penaltyCounter % 2 == 1) {
                        penaltyTeam = teamName1;
                    } else {
                        penaltyTeam = teamName2;
                    }

                    System.out.print(penaltyTeam + "'s turn to score. Will they succeed? ");
                    String success = scanner.nextLine();
                    while (!success.equals("yes") && !success.equals("no")) {
                        System.out.print("Answer must be yes or no. Try again.");
                        success = scanner.nextLine();
                    }

                    boolean succeeds = false;
                    if (success.equals("yes")) {
                        succeeds = true;
                    }

                    game.addPenalty(succeeds);
                }

                System.out.println("");
                if (game.victory()) {
                    System.out.println(teamName1 + " won.");
                } else {
                    System.out.println(teamName2 + " won.");
                }
            }
        }
        return game;
    }
}
