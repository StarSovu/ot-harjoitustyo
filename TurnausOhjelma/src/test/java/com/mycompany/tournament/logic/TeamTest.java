package com.mycompany.tournament.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class TeamTest {
    
    Team team1;
    
    public TeamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        team1 = new Team("Team1");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void teamNameIsTheTeamNameSet() {
        assertEquals("Team1", team1.getTeamName());
    }
    
    @Test
    public void defaultGoalDifferenceIs0() {
        assertTrue(team1.getGD() == 0);
    }
    
    @Test
    public void defaultToString() {
        assertEquals("Team1: 0 points, 0 000 0-0", team1.toString());
    }
    
    @Test
    public void toStringAfterOneGame() {
        team1.addGroupStageGame(new Game(1, 0));
        assertEquals("Team1: 3 points, 1 100 1-0", team1.toString());
    }
    
    @Test
    public void goalsAgainstIsOneAfterOneGoalAgainst() {
        team1.addGroupStageGame(new Game(3, 1));
        assertTrue(team1.getGoalsAgainst() == 1);
    }
    
    @Test
    public void gamesPlayedAfterOneGameIsOne() {
        team1.addGroupStageGame(new Game(1, 2));
        assertTrue(team1.getGamesPlayed() == 1);
    }
    
    @Test
    public void replacingGroupStageGameDoesNotChangeGamesPlayed() {
        team1.addGroupStageGame(new Game(1, 2));
        int gamesPlayed = team1.getGamesPlayed();
        team1.replaceGroupStageGame(new Game(1, 2), new Game(2, 1));
        assertTrue(team1.getGamesPlayed() == gamesPlayed);
    }
}
