/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tournament.logic;

import com.mycompany.tournament.calculations.Calculations;
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
    
    Calculations calculations;
    
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
        calculations = new Calculations();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void triangularNumberOne() {
        assertTrue(calculations.triangularNumber(1) == 1);
    }
    
    @Test
    public void largerTriangularNumber() {
        assertTrue(calculations.triangularNumber(100) == 5050);
    }
    
    @Test
    public void negativeNumbersAreNotPowersOfTwo() {
        assertTrue(!calculations.powerOfTwoChecker(-2));
    }
    
    @Test
    public void oneIsAPowerOfTwo() {
        assertTrue(calculations.powerOfTwoChecker(1));
    }
    
    @Test
    public void sixIsNotAPowerOfTwo() {
        assertTrue(!calculations.powerOfTwoChecker(6));
    }
}
