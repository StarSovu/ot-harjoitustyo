package com.mycompany.turnausohjelma;

/**
 *
 * @author sovu
 */
public class Game {
    private int goals1;
    private int goals2;
    
    public Game(int goals1, int goals2) {
        this.goals1 = goals1;
        this.goals2 = goals2;
    }
    
    public int getGoals1() {
        return this.goals1;
    }
    
    public int getGoals2() {
        return this.goals2;
    }
    
    public boolean tie() {
        return (this.goals1 == this.goals2);
    }
    
    public boolean victory() {
        return (this.goals1 > this.goals2);
    }
    
    public boolean loss() {
        return (this.goals2 > this.goals1);
    }
}
