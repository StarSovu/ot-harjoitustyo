package com.mycompany.tournament.logic;

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
public class GameTest {
    
    Game team1Victory;
    Game team2Victory;
    Game tie;
    Game extraTimeTie;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        team1Victory = new Game(2, 1);
        team2Victory = new Game(1, 2);
        tie = new Game(1, 1);
        extraTimeTie = new Game(1, 1, 1, 1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void team1VictoryActuallyVictory() {
        assertTrue(this.team1Victory.victory());
    }
    
    @Test
    public void team1VictoryNotALoss() {
        assertTrue(!team1Victory.loss());
    }
    
    @Test
    public void team1VictoryNotATie() {
        assertTrue(!team1Victory.tie());
    }
    
    @Test
    public void team2VictoryNotAVictory() {
        assertTrue(!team2Victory.victory());
    }
    
    @Test
    public void team2VictoryALoss() {
        assertTrue(team2Victory.loss());
    }
    
    @Test
    public void team2VictoryNotATie() {
        assertTrue(!team2Victory.tie());
    }
    
    @Test
    public void tieNotAVictory() {
        assertTrue(!tie.victory());
    }
    
    @Test
    public void tieNotALoss() {
        assertTrue(!tie.loss());
    }
    
    @Test
    public void tieATie() {
        assertTrue(tie.tie());
    }
    
    @Test
    public void regularTieDoesNotNeedPenalties() {
        assertTrue(!tie.needsPenalties());
    }
    
    @Test
    public void noExtraGoals1WhenVictory() {
        Game game = new Game(1, 2, 5, 4);
        assertTrue(game.getExtraTimeGoals1() == 1);
    }
    
    @Test
    public void noExtraGoals2WhenVictory() {
        Game game = new Game(1, 2, 5, 4);
        assertTrue(game.getExtraTimeGoals2() == 2);
    }
    
    @Test
    public void penaltiesNeededWhenExtraTimeTie() {
        assertTrue(extraTimeTie.needsPenalties());
    }
    
    @Test
    public void penaltiesNoLongerNeededAfterTenPenaltiesIfNoTie() {
        for (int i = 0; i < 10; i++) {
            extraTimeTie.addPenalty(i % 2 == 0);
        }
        assertTrue(!extraTimeTie.needsPenalties());
    }
    
    @Test
    public void penaltiesNeededAfterTenPenaltiesIfTie() {
        for (int i = 0; i < 10; i++) {
            extraTimeTie.addPenalty(true);
        }
        assertTrue(extraTimeTie.needsPenalties());
    }
    
}
