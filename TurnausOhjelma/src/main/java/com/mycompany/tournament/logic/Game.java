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
        if (this.penalties.size() >= 10 && this.penalties.size() % 2 == 0) {
            if (!this.checkIfMorePenaltiesNeeded()) {
                this.needsPenalties = false;
            }
        }
    }
    
    private boolean checkIfMorePenaltiesNeeded() {
        int penalties1 = 0;
        int penalties2 = 0;
        
        for (int i = 0; i < this.penalties.size(); i+= 2) {
            if (this.penalties.get(i)) {
                penalties1++;
            }
        }
        
        for (int i = 1; i < this.penalties.size(); i+= 2) {
            if (this.penalties.get(i)) {
                penalties2++;
            }
        }
        
        return (penalties1 == penalties2);
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
    
    public boolean tie() {
        return (this.goals1 == this.goals2);
    }
    
    public boolean victory() {
        return (this.goals1 + this.extraGoals1 > this.goals2 + this.extraGoals2);
    }
    
    public boolean loss() {
        return (this.goals2 + this.extraGoals2 > this.goals1 + this.extraGoals2);
    }
    
    public boolean extraTimeVictory() {
        return (this.victory() && this.tie());
    }
    
    public boolean extraTimeLoss() {
        return (this.loss() && this.tie());
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
