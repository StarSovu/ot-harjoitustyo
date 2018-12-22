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
public class GroupStageTest {
    
    GroupStage groupStage;
    
    public GroupStageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<Team> teams = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                Team team = new Team("ij");
                teams.add(team);
            }
            groups.add(new Group(teams));
        }
        groupStage = new GroupStage(groups);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void groupStageNotDoneWhenNothingHasHappenedYet() {
        assertTrue(!groupStage.checkIfDone());
    }
}
