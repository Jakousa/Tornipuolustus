/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.toimijat;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.ruudut.Rakennettava;
import tornipuolustus.tornipuolustus.Kentta;
import tornipuolustus.tornipuolustus.Sijainti;

/**
 *
 * @author Hatchy
 */
public class HirvioTest {
    

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
    public void hirvionElamat() {
        int elama = hirvio.getElama();
        assertEquals(elama, 9);
    }

    @Test
    public void hirvionElamat2() {
        hirvio.setElama(8);
        assertEquals(hirvio.getElama(), 8);
    }

    @Test
    public void hirvioLoytaaSuunnan() {
        ArrayList<Kuljettava> Kuljettavat = new ArrayList<>();
        Kuljettavat.add(hirvionKeski);
        Kuljettavat.add(hirvionOikea);
        hirvio.liiku(Kuljettavat);
        assertTrue(hirvio.getSijainti().equals(hirvionOikea.getSijainti()));
    }

    @Test
    public void hirvioValitseeOikein() {
        ArrayList<Kuljettava> Kuljettavat = new ArrayList<>();
        Kuljettava Oikeampi = new Kuljettava(3, 1);
        Kuljettavat.add(Oikeampi);
        Kuljettavat.add(hirvionKeski);
        Kuljettavat.add(hirvionOikea);
        hirvio.liiku(Kuljettavat);
        hirvio.liiku(Kuljettavat);
        assertTrue(hirvio.getSijainti().equals(Oikeampi.getSijainti()));

    }

    @Test
    public void hirviolleAnnetaanSuunta() {
        hirvio.setSuunta(0);
        assertEquals(hirvio.getSuunta(), 0);
    }

    @Test
    public void hirvioLoytaaSuunnan2() {
        ArrayList<Kuljettava> Kuljettavat = new ArrayList<>();
        Kuljettavat.add(hirvionKeski);
        Kuljettavat.add(hirvionVasen);
        hirvio.liiku(Kuljettavat);
        assertTrue(hirvio.getSijainti().equals(hirvionVasen.getSijainti()));
    }

    @Test
    public void hirvioLiikkuuOikein() {
        ArrayList<Kuljettava> Kuljettavat = new ArrayList<>();
        Kuljettavat.add(hirvionKeski);
        Kuljettavat.add(hirvionAlas);
        hirvio.liiku(Kuljettavat);
        Kuljettavat.add(hirvionYlos);
        hirvio.liiku(Kuljettavat);
        hirvio.liiku(Kuljettavat);
        assertTrue(hirvio.getSijainti().equals(hirvionYlos.getSijainti()));
    }

    @Test
    public void hirvioEiKohdallaJatkaaLiiketta() {
        ArrayList<Kuljettava> Kuljettavat = new ArrayList<>();
        Kuljettavat.add(hirvionKeski);
        Kuljettavat.add(hirvionOikea);
        hirvio.liiku(Kuljettavat);
        Kuljettavat.add(hirvionVasen);
        Kuljettavat.remove(hirvionKeski);
        hirvio.liiku(Kuljettavat);
        hirvio.liiku(Kuljettavat);
        assertTrue(hirvio.getSijainti().equals(hirvionVasen.getSijainti()));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
