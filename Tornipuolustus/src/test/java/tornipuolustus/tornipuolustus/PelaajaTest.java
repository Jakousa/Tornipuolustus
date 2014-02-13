/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.tornipuolustus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hatchy
 */
public class PelaajaTest {

    Pelaaja pelaaja = new Pelaaja();
    Pelaaja pelaaja2 = new Pelaaja(); //Helpottanee vertailuoperaatioita
    
    @Test
    public void pelajaaAloittaaNollista(){
        int x = pelaaja.getKursori().getX();
        int y = pelaaja.getKursori().getY();
        assertEquals(0,x);
        assertEquals(0,y);
    } 
    
    @Test
    public void testaillaanVaikeustasonVaikutusta() {
        boolean tosi = true;
        pelaaja.aloitusElama(1);
        pelaaja.aloitusRahat(1);
        pelaaja2.aloitusElama(2);
        pelaaja2.aloitusRahat(2);
        
        if (pelaaja.getRahat() < pelaaja2.getRahat()) {
            tosi = false;
        } else if (pelaaja.getElama() < pelaaja2.getElama()) {
            tosi = false;
        }
        
        pelaaja.aloitusElama(3);
        pelaaja.aloitusRahat(3);
        
        if (pelaaja.getRahat() > pelaaja2.getRahat()) {
            tosi = false;
        } else if (pelaaja.getElama() > pelaaja2.getElama()) {
            tosi = false;
        }
        
        assertTrue(tosi);
    }
    
    @Test
    public void pelaajanElamaVahenee(){
        pelaaja.aloitusElama(1);
        int aluksi = pelaaja.getElama();
        pelaaja.otaOsumaa();
        assertTrue(pelaaja.getElama() < aluksi);
    }
    
    @Test
    public void pelaajallePakotetaanPisteet(){
        int randompisteet = 124;
        pelaaja.setPisteet(randompisteet);
        assertEquals(pelaaja.getPisteet(), randompisteet);
    }
    
    @Test
    public void pelaajaSaaRahaa() {
        pelaaja.aloitusRahat(1);
        int alkurahat = pelaaja.getRahat();
        int alkupisteet = pelaaja.getPisteet();
        pelaaja.tienaa(5);
        
        assertTrue(pelaaja.getPisteet() > alkupisteet);
        assertTrue(pelaaja.getRahat() > alkurahat);
    }
    
    @Test
    public void pelaajaOstaa() {
        pelaaja.aloitusRahat(2);
        int alkurahat = pelaaja.getRahat();
        pelaaja.rakenna(200);
        assertTrue(pelaaja.getRahat() < alkurahat);
    }
    
    @Test
    public void pelaajaBuyback() {
        pelaaja.aloitusRahat(1);
        int aloitusrahat = pelaaja.getRahat();
        int torninHinta = 200;
        pelaaja.rakenna(torninHinta);
        int valirahat = pelaaja.getRahat();
        pelaaja.tuhoaTorni(torninHinta);
        int loppurahat = pelaaja.getRahat();
        assertTrue(loppurahat < aloitusrahat);
        assertTrue(loppurahat > valirahat);
    }
}
