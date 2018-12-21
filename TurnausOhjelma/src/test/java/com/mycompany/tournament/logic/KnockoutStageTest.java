/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class KnockoutStageTest {
    
    KnockoutStage knockout8;
    
    public KnockoutStageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            teams.add(new Team("team" + i));
        }
        
        knockout8 = new KnockoutStage(teams);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void eightTeams() {
        assertTrue(knockout8.getCurrentTeams().size() == 8);
    }
    
    @Test
    public void eightTeamsThreeRounds() {
        assertTrue(knockout8.getRoundNumber() == 3);
    }
    
    @Test
    public void originalRoundNumberStaysAtThree() {
        for (int i = 0; i < 4; i++) {
            knockout8.playGame(i, new Game(1, 2));
        }
        knockout8.completeRound();
        assertTrue(knockout8.getOriginalRoundNumber() == 3);
    }
    
    @Test
    public void roundNumberUpdates() {
        for (int i = 0; i < 4; i++) {
            knockout8.playGame(i, new Game(1, 2));
        }
        knockout8.completeRound();
        assertTrue(knockout8.getRoundNumber() == 2);
    }
    
    @Test
    public void roundNotDoneByDefault() {
        assertTrue(!knockout8.roundDone());
    }
    
    @Test
    public void alreadyPlayedFalseWhenGameNotPlayed() {
        assertTrue(!knockout8.alreadyPlayed(0));
    }
    
    @Test
    public void alreadyPlayedTrueWhenGamePlayed() {
        knockout8.playGame(0, new Game(0, 1));
        assertTrue(knockout8.alreadyPlayed(0));
    }
    
    @Test
    public void alreadyPlayedFalseInNextRound() {
        for (int i = 0; i < 4; i++) {
            knockout8.playGame(i, new Game(1, 2));
        }
        knockout8.completeRound();
        assertTrue(!knockout8.alreadyPlayed(0));
    }
    
    @Test
    public void numberOfRemainingTeamsAfterFirstRoundIsDoneIsFour() {
        for (int i = 0; i < 4; i++) {
            knockout8.playGame(i, new Game(1, 2));
        }
        knockout8.completeRound();
        assertTrue(knockout8.getNumberOfRemainingTeams() == 4);
    }
    
    @Test
    public void knockoutStageWithTwoDoneAfterFinal() {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("team1"));
        teams.add(new Team("team2"));
        Game finalMatch = new Game(3, 2);
        
        KnockoutStage knockout2 = new KnockoutStage(teams);
        knockout2.playFinal(finalMatch);
        knockout2.completeRound();
        
        assertTrue(knockout2.done());
    }
    
}
