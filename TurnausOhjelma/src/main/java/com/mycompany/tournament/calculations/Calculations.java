package com.mycompany.tournament.calculations;

public class Calculations {
    public Calculations() {
    }
    
    public int triangularNumber(int number) {
        int triangular = 0;
        
        for (int i = 1; i <= number; i++) {
            triangular += i;
        }
        
        return triangular;
    }
    
    public boolean powerOfTwoChecker(int number) {
        if (number <= 0) {
            return false;
        }
        
        for (int i = number; i > 1; i /= 2) {
            if (i % 2 == 1) {
                return false;
            }
        }
        return true;
    }
}
