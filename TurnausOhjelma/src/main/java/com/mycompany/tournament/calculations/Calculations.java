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
    
    public int powerOfTwo(int number) {
        int power = 1;
        if (number >= 1) {
            for (int i = 0; i < number; i++) {
                power *= 2;
            }
        } else {
            for (int i = 0; i > number; i++) {
                power /= 2;
            }
        }
        return power;
    }
    
    public int log2(int number) {
        int count = 0;
        while (number > 1) {
            count++;
            number /= 2;
        }
        return count;
    }
}
