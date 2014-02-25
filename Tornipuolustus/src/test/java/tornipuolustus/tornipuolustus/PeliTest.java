/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.tornipuolustus;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.toimijat.Torni;

public class PeliTest {

    Peli peli = new Peli(new Scanner(System.in));

    @Test
    public void peliLuokentanjaPelaajan() {
        assertTrue(peli.getPelaaja() != null);
        assertTrue(peli.getKentta() != null);
    }

    @Test
    public void pelaajanVuoroAluksi() {
        assertTrue(peli.getPelaajanVuoro());
    }

    @Test
    public void vuoronLopettaminenLopettaaVuoron() { //...
        peli.lopetaVuoro();
        assertTrue(!peli.getPelaajanVuoro());
    }

    @Test
    public void torniaRakennetaanPelaajallaEiRahaa() {
        peli.getKentta().tayta();
        peli.tornia(0, 0);
        Torni t = peli.getKentta().getRakennettavat().get(0).getTorni();
        assertTrue(t == null);
    }
    
    @Test
    public void torniaRakennetaanPelaajallaRahaa() {
        peli.getKentta().tayta();
        peli.tornia(0, 0);
        Torni t = peli.getKentta().getRakennettavat().get(0).getTorni();
        assertTrue(t != null);
    }

    @Test
    public void torniTuhotaan() {
        peli.getKentta().tayta();
        peli.tornia(0, 0);
        peli.jyraa(0, 0);
        Torni t = peli.getKentta().getRakennettavat().get(0).getTorni();
        assertTrue(t == null);
    }
}
