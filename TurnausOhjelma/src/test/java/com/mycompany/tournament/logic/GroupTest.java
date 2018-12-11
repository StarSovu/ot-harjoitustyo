package com.mycompany.tournament.logic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sovu
 */
public class GroupTest {
    
    Group group;
    ArrayList<Team> teams;
    
    public GroupTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        teams = new ArrayList<>();
        teams.add(new Team("team1"));
        teams.add(new Team("team2"));
        teams.add(new Team("team3"));
        teams.add(new Team("team4"));
        group = new Group(teams);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void notDone() {
        assertTrue(!group.checkIfDone());
    }
    
    @Test
    public void team1WinningAfterFirstGameWhenVictory() {
        group.playGame(0, 3, 0);
        assertTrue(group.getTeamInPlace(1).equals(teams.get(0)));
    }
    
    @Test
    public void groupOfFourOverAfterSixGames() {
        for (int i = 0; i < 6; i++) {
            group.playGame(i, 2, 3);
        }
        assertTrue(group.checkIfDone());
    }
    
    @Test
    public void groupOfTwoOverAfterOneGame() {
        ArrayList<Team> list = new ArrayList<>();
        Team team1 = new Team("team1");
        Team team2 = new Team("team2");
        list.add(team1);
        list.add(team2);
        Group group2 = new Group(list);
        
        group2.playGame(0, 0, 0);
        
        assertTrue(group2.checkIfDone());
    }
    
    @Test
    public void game0NotAlreadyPlayed() {
        assertTrue(!group.alreadyPlayed(0));
    }
    
    @Test
    public void game0AlreadyPlayedAfterItIsPlayed() {
        group.playGame(0, 0, 0);
        assertTrue(group.alreadyPlayed(0));
    }
    
    @Test
    public void playingAlreadyPlayedGameReplacesScore() {
        group.playGame(0, 0, 0);
        group.playGame(0, 3, 0);
        assertTrue(teams.get(0).getGoalsFor() == 3);
    }
    
    @Test
    public void playingGameWithNumberTooBigDoesNothing() {
        group.playGame(6, 0, 0);
        assertTrue(group.numberOfGamesPlayed() == 0);
    }
    
    @Test
    public void alreadyPlayedGameWithTooBigNumberIsFalse() {
        group.playGame(6, 0, 0);
        assertTrue(!group.alreadyPlayed(6));
    }
    
    @Test
    public void gameResultOfGameNotYetPlayedIs00() {
        assertEquals("0-0", group.gameResult(0));
    }
    
    @Test
    public void giveCorrectResultOfAlreadyPlayedGame() {
        group.playGame(0, 0, 1);
        assertEquals("0-1", group.gameResult(0));
    }
    
    @Test
    public void playingGameWithNegativeGoalsDoesNothing() {
        group.playGame(0, -1, -1);
        assertTrue(!group.alreadyPlayed(0));
    }
    
    @Test
    public void doneWhenAlreadyDone() {
        for (int i = 0; i < 6; i++) {
            group.playGame(i, 4, 3);
        }
        
        group.checkIfDone();
        assertTrue(group.checkIfDone());
    }
}
