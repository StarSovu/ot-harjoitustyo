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
}
