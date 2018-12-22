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
    
    static Scanner scanner = new Scanner(System.in);
    static Calculations calculations = new Calculations();

    public static void main(String[] args) {
        System.out.println("How many teams in a group? (must be between 2 and 44,721)");
        int numberOfTeamsInGroup = 0;
        while (numberOfTeamsInGroup < 2) {
            String teamNumber = scanner.nextLine();
            boolean acceptable = goThroughAcceptance(teamNumber, 2, 44721, false);
            if (acceptable) {
                numberOfTeamsInGroup = Integer.parseInt(teamNumber);
            }
        }
        System.out.println("");
        
        System.out.println("How many groups?");
        int numberOfGroups = 0;
        while (numberOfGroups < 2) {
            String groupNumber = scanner.nextLine();
            boolean acceptable = goThroughAcceptance(groupNumber, 2, 1000000000, true);
            if (acceptable) {
                numberOfGroups = Integer.parseInt(groupNumber);
            }
        }
        System.out.println("");
        
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            System.out.println("");
            System.out.println("Group " + i);
            
            ArrayList<Team> groupTeams = new ArrayList<>();
            
            for (int j = 0; j < numberOfTeamsInGroup; j++) {
                System.out.print("Name of team " + (j + 1) + ": ");
                String team = scanner.nextLine();
                groupTeams.add(new Team(team));
            }

            Group group = new Group(groupTeams);
            groups.add(group);
        }
        
        System.out.println("");
        
        GroupStage groupStage = new GroupStage(groups);
        
        int numberOfGames = calculations.triangularNumber(numberOfTeamsInGroup - 1);
        
        boolean advance = false;
        
        while (!advance) {
            System.out.println("In which group do you want to play? (Number 0 - "
                        + (numberOfGroups - 1) + ")");
            int groupNumber = numberOfGroups;
            
            while (groupNumber >= numberOfGroups) {
                String groupNumberString = scanner.nextLine();
                boolean acceptable = goThroughAcceptance(groupNumberString, 0, numberOfGroups, false);
                if (acceptable) {
                    groupNumber = Integer.parseInt(groupNumberString);
                }
            }
            System.out.println("");
            
            System.out.println("Which game do you want to play?");
            
            int homeTeamIndex = 0;
            int awayTeamIndex = 0;
            
            ArrayList<Team> teams = groupStage.getGroup(groupNumber).listTeamsInOriginalOrder();
            
            for (int i = 0; i < numberOfGames; i++) {
                if (awayTeamIndex == numberOfTeamsInGroup - 1) {
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
            int gameNumber = 999999999;
            while (gameNumber >= numberOfGames) {
                String gameNumberString = scanner.nextLine();
                boolean acceptable = goThroughAcceptance(gameNumberString, 0, numberOfGames, false);
                if (acceptable) {
                    gameNumber = Integer.parseInt(gameNumberString);
                }
            }
            System.out.println("");
            
            homeTeamIndex = 0;
            awayTeamIndex = 0;
            
            for (int i = 0; i <= gameNumber; i++) {
                if (awayTeamIndex == numberOfTeamsInGroup - 1) {
                    homeTeamIndex++;
                    awayTeamIndex = homeTeamIndex + 1;
                } else {
                    awayTeamIndex++;
                }
            }
            
            System.out.print(teams.get(homeTeamIndex).getTeamName() + " goals: ");
            int goals1 = getNumberOfGoals();
            
            System.out.print(teams.get(awayTeamIndex).getTeamName() + " goals: ");
            int goals2 = getNumberOfGoals();

            groupStage.playGame(groupNumber, gameNumber, goals1, goals2);
            
            System.out.println("");
            System.out.println("Current situation:");
            System.out.println(groupStage.getGroup(groupNumber));
            System.out.println("");
            
            if (groupStage.checkIfDone()) {
                System.out.println("You have completed all group stage games.");
                System.out.println("Advance to Knockout Stage? (Type y to advance. Anything else means you have to change a game result.)");
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
        String advancingTeams = scanner.nextLine();
        while (!goThroughAcceptance(advancingTeams, 1, numberOfTeamsInGroup, true)) {
            advancingTeams = scanner.nextLine();
        }
        int numberOfAdvancingTeams = Integer.parseInt(advancingTeams);
        
        System.out.println("");
        ArrayList<Team> knockoutStageTeams = new ArrayList<>();
        System.out.println("All knockout stage round 1 games:");
        for (int i = 0; i < numberOfAdvancingTeams; i++) {
            for (int j = 0; j < numberOfGroups; j += 2) {
                Team team1 = groupStage.getPlacementInGroup(i + 1, j);
                Team team2 = groupStage.getPlacementInGroup(numberOfAdvancingTeams - i, j + 1);
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
            int gamePlayed = knockoutSize;
            while (gamePlayed >= knockoutSize / 2) {
                String gamePlayedString = scanner.nextLine();
                if (goThroughAcceptance(gamePlayedString, 0, 1000000000, false)) {
                    gamePlayed = Integer.parseInt(gamePlayedString);
                    if(gamePlayed >= knockoutSize / 2) {
                        System.out.println("Number out of range. Try again.");
                    }
                }
            }
            
            String teamName1 = remaining.get(2 * gamePlayed).getTeamName();
            String teamName2 = remaining.get(2 * gamePlayed + 1).getTeamName();
            System.out.println("");
            
            Game game = playKnockoutGame(teamName1, teamName2);
            
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
            
            Game thirdPlace = playKnockoutGame(teamName1, teamName2);
            knockoutStage.playThirdPlace(thirdPlace);
        }
        
        System.out.println("");
            
        String teamName1 = knockoutStage.getCurrentTeams().get(0).getTeamName();
        String teamName2 = knockoutStage.getCurrentTeams().get(1).getTeamName();
        System.out.println("Final: " + teamName1 + " - " + teamName2);

        Game finalGame = playKnockoutGame(teamName1, teamName2);
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
    
    public static boolean canBeUsed(String text) {
        String newText = text.replaceAll("[^0-9]+","");
        if (newText.length() > 9) {
            return false;
        }
        if (!text.isEmpty()) {
            return (text.equals(newText));
        }
        return false;
    }
    
    public static boolean goThroughAcceptance(String text, int lowerBound, int upperBound, boolean mustBePowerOfTwo) {
        if (!canBeUsed(text)) {
            System.out.println("Number must be a non-negative integer and less than a billion. Try again.");
            return false;
        }
        int number = Integer.parseInt(text);
        if (number < lowerBound) {
            System.out.println("Number must be at least " + lowerBound + ". Try again.");
        } else if (number > upperBound) {
            System.out.println("Number cannot be more than " + upperBound + ". Try again.");
        } else if (!calculations.powerOfTwoChecker(number) && mustBePowerOfTwo) {
            System.out.println("Number must be a power of two. Try again.");
        } else {
            return true;
        }
        return false;
    }
    
    public static int getNumberOfGoals() {
        String goals = scanner.nextLine();
        while (!goalNumberAcceptance(goals)) {
            goals = scanner.nextLine();
        }
        return Integer.parseInt(goals);
    }
    
    public static boolean goalNumberAcceptance(String text) {
        if (!canBeUsed(text)) {
            System.out.println("Number of goals must be a non-negative integer and less than a billion. Try again.");
            return false;
        }
        return true;
    }

    public static Game playKnockoutGame(String teamName1, String teamName2) {
        System.out.print(teamName1 + " goals: ");
        int goals1 = getNumberOfGoals();

        System.out.print(teamName2 + " goals: ");
        int goals2 = getNumberOfGoals();

        Game game;

        if (goals1 != goals2) {
            game = new Game(goals1, goals2);
        } else {
            System.out.println("Type extra time goals. Note: Only count goals scored during extra time!");

            System.out.print(teamName1 + " goals: ");
            int extraGoals1 = getNumberOfGoals();

            System.out.print(teamName2 + " goals: ");
            int extraGoals2 = getNumberOfGoals();

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
