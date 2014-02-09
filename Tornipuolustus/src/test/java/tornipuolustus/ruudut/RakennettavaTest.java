/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ruudut;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.toimijat.Hirvio;
import tornipuolustus.toimijat.Torni;
import tornipuolustus.tornipuolustus.Kentta;
import tornipuolustus.tornipuolustus.Sijainti;

/**
 *
 * @author Hatchy
 */
public class RakennettavaTest {
    

    Kentta kentta = new Kentta(10);
    Rakennettava rakennettava = new Rakennettava(1, 8);
    Kuljettava kuljettava = new Kuljettava(0, 8);
    Hirvio hirvio = new Hirvio(new Sijainti(1, 1), 9);
    Kuljettava hirvionYlos = new Kuljettava(1, 0);
    Kuljettava hirvionKeski = new Kuljettava(1, 1);
    Kuljettava hirvionAlas = new Kuljettava(1, 2);
    Kuljettava hirvionOikea = new Kuljettava(2, 1);
    Kuljettava hirvionVasen = new Kuljettava(0, 1);
    
    @Test
    public void kentanLuonnistaTyhjaRuudukko() {
        assertTrue(kentta.getRuudukko().isEmpty());
    }

    @Test
    public void torniLisataan() {
        rakennettava.rakennaTorni();
        Torni testi = rakennettava.getTorni();
        boolean asd = true;
        if (testi == null) {
            asd = false;
        }
        assertTrue(asd);
    }
    
    @Test
    public void torniLisataan2() {
        assertTrue(rakennettava.rakennaTorni());
    }

    @Test
    public void torniTuhotaan() {
        rakennettava.rakennaTorni();
        rakennettava.tuhoaTorni();
        Torni testi = rakennettava.getTorni();
        boolean asd = false;
        if (testi == null) {
            asd = true;
        }
        assertTrue(asd);
    }

    @Test
    public void torniaTorniinTaiTuhottuaTuhotaan() {
        rakennettava.rakennaTorni();
        assertTrue(!rakennettava.rakennaTorni());
        rakennettava.tuhoaTorni();
        assertTrue(!rakennettava.tuhoaTorni());
    }
}
