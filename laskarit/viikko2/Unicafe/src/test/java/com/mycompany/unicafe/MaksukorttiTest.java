package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(20);
        assertTrue(kortti.saldo() == 30);
    }
    
    @Test
    public void saldoVaheneeOikeinKunRahaaOnTarpeeksi() {
        kortti.otaRahaa(9);
        assertTrue(kortti.saldo() == 1);
    }
    
    @Test
    public void saldoEiMuutuKunRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(20);
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void otaRahaaPalauttaaTrueKunRahaaOnTarpeeksi() {
        assertTrue(kortti.otaRahaa(9));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseKunRahaaEiOleTarpeeksi() {
        assertTrue(!kortti.otaRahaa(20));
    }
    
    @Test
    public void toStringPalautuuOikein() {
        assertEquals(kortti.toString(), "saldo: 0.10");
    }
}
