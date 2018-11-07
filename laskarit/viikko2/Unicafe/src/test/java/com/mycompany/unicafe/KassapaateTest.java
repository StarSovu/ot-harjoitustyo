package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sovu
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void luodunKassapaatteenRahamaaraOnOikea() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    //KÄTEISOSTO
    
    @Test
    public void luodunKassapaatteenMyytyjenEdullistenLounaidenMaaraOnOikea() {
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void luodunKassapaatteenMyytyjenMaukkaidenLounaidenMaaraOnOikea() {
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kassassaOlevaRahamaaraKasvaaOikeinKunEdulliseenRiittaaRahaa() {
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.kassassaRahaa() == 100240);
    }
    
    @Test
    public void edullinenLounasAntaaOikeanVaihtorahan() {
        assertTrue(kassapaate.syoEdullisesti(300) == 60);
    }
    
    @Test
    public void edullisenLounaanOstaminenKasvattaaMyytyjenEdullistenLounaidenMaaraa() {
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuKunEdulliseenEiRiitaRaha() {
        kassapaate.syoEdullisesti(200);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kaikkiRahaPalautetaanVaihtorahanaKunEdulliseenLounaaseenEiRiitaRaha() {
        assertTrue(kassapaate.syoEdullisesti(200) == 200);
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraEiKasvaKunEdulliseenEiRiitaRaha() {
        kassapaate.syoEdullisesti(200);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kassassaOlevaRahamaaraKasvaaOikeinKunMaukkaaseenRiittaaRahaa() {
        kassapaate.syoMaukkaasti(500);
        assertTrue(kassapaate.kassassaRahaa() == 100400);
    }
    
    @Test
    public void maukasLounasAntaaOikeanVaihtorahan() {
        assertTrue(kassapaate.syoMaukkaasti(500) == 100);
    }
    
    @Test
    public void maukkaanLounaanOstaminenKasvattaaMyytyjenEdullistenLounaidenMaaraa() {
        kassapaate.syoMaukkaasti(500);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuKunMaukkaaseenEiRiitaRaha() {
        kassapaate.syoMaukkaasti(200);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kaikkiRahaPalautetaanVaihtorahanaKunMaukkaaseenLounaaseenEiRiitaRaha() {
        assertTrue(kassapaate.syoMaukkaasti(200) == 200);
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraEiKasvaKunMaukkaaseenEiRiitaRaha() {
        kassapaate.syoMaukkaasti(200);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    //KORTTIOSTO
    
    @Test
    public void josKortillaTarpeeksiRahaaEdulliseenVeloitetaanOikeaSumma() {
        Maksukortti kortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 60);
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaSyoEdullisestiPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(300);
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMyytyjenEdullistenLounaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutuEdullistaOstaessa() {
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 200);
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaMyytyjenEdullistenLounaidenMaaraMuuttumaton() {
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaSyoEdullisestiPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(200);
        assertTrue(!kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuEdullistaOstaessaKortilla() {
        Maksukortti kortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMaukkaaseenVeloitetaanOikeaSumma() {
        Maksukortti kortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 100);
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaSyoMaukkaastiPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(500);
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMyytyjenMaukkaidenLounaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutuMaukastaOstaessa() {
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 200);
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaMyytyjenMaukkaidenLounaidenMaaraMuuttumaton() {
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaSyoMaukaastiPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(200);
        assertTrue(!kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuMaukastaOstaessaKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void kortilleLadattaessaKortinSaldoMuuttuu() {
        Maksukortti kortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo() == 200);
    }
    
    @Test
    public void kortilleLadattaessaKassanRahaKasvaa() {
        Maksukortti kortti = new Maksukortti(100);
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertTrue(kassapaate.kassassaRahaa() == 100100);
    }
}
