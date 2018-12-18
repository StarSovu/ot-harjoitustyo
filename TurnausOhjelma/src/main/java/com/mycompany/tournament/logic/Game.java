package com.mycompany.tournament.logic;

import java.util.ArrayList;

/**
 *
 * @author sovu
 */
public class Game {
    private int goals1;
    private int goals2;
    private int extraGoals1;
    private int extraGoals2;
    private boolean extraTime;
    private boolean needsPenalties;
    private ArrayList<Boolean> penalties;
    private int penaltyGoals1;
    private int penaltyGoals2;
    
    public Game(int goals1, int goals2) {
        this.basicConstructor(goals1, goals2);
        this.extraGoals1 = 0;
        this.extraGoals2 = 0;
        this.extraTime = false;
        this.needsPenalties = false;
        this.penalties = new ArrayList<>();
    }
    
    public Game(int goals1, int goals2, int extraGoals1, int extraGoals2) {
        this.basicConstructor(goals1, goals2);
        if (goals1 == goals2) {
            this.extraGoals1 = extraGoals1;
            this.extraGoals2 = extraGoals2;
            this.extraTime = true;
            if (extraGoals1 == extraGoals2) {
                this.needsPenalties = true;
            }
        }
    }
    
    private void basicConstructor(int goals1, int goals2) {
        this.goals1 = goals1;
        this.goals2 = goals2;
        this.extraGoals1 = 0;
        this.extraGoals2 = 0;
        this.extraTime = false;
        this.needsPenalties = false;
        this.penalties = new ArrayList<>();
    }
    
    public void addPenalty(boolean success) {
        if (!needsPenalties) {
            return;
        }
        this.penalties.add(success);
        
        if (success) {
            if (this.penalties.size() % 2 == 1) {
                this.penaltyGoals1++;
            } else {
                this.penaltyGoals2++;
            }
        }
        
        if (this.penalties.size() >= 10 && this.penalties.size() % 2 == 0) {
            this.needsPenalties = (this.penaltyGoals1 == this.penaltyGoals2);
        }
    }
    
    public int getGoals1() {
        return this.goals1;
    }
    
    public int getGoals2() {
        return this.goals2;
    }
    
    public int getExtraTimeGoals1() {
        return this.goals1 + this.extraGoals1;
    }
    
    public int getExtraTimeGoals2() {
        return this.goals2 + this.extraGoals2;
    }
    
    public boolean tie() {
        return (this.goals1 == this.goals2);
    }
    
    public boolean victory() {
        return (this.victoryNumber1() > this.victoryNumber2());
    }
    
    public boolean loss() {
        return (this.victoryNumber2() > this.victoryNumber1());
    }
    
    private int victoryNumber1() {
        return this.goals1 + this.extraGoals1 + this.penaltyGoals1;
    }
    
    private int victoryNumber2() {
        return this.goals2 + this.extraGoals2 + this.penaltyGoals2;
    }
    
    public boolean needsPenalties() {
        return (this.needsPenalties);
    }
    
    @Override
    public String toString() {
        String string = this.goals1 + "-" + this.goals2;
        
        if (this.extraTime == true) {
            string += " (" + (goals1 + extraGoals1) + "-" + (goals2 + extraGoals2) + " a.e.t.)";
        }
        
        return string;
    }
}
