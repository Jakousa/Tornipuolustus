/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.toimijat;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.ruudut.Rakennettava;
import tornipuolustus.tornipuolustus.Sijainti;

/**
 *
 * @author Hatchy
 */
public class TorniTest {
    
    Rakennettava rakennettava = new Rakennettava(1, 8);

    @Test
    public void torniPiirtyy() {
        rakennettava.rakennaTorni();
        assertEquals(rakennettava.toString(), "T");
    }

    @Test
    public void torniLoytaaKohteen() {
        ArrayList<Hirvio> asd = new ArrayList<>();
        Hirvio olio = new Hirvio(new Sijainti(1, 7), 9);
        asd.add(olio);
        rakennettava.rakennaTorni();
        rakennettava.getTorni().etsiKohde(asd, rakennettava.getSijainti());
        assertEquals(rakennettava.getTorni().getKohde(), olio);
    }

    @Test
    public void torniAmpuu() {
        ArrayList<Hirvio> asd = new ArrayList<>();
        Hirvio olio = new Hirvio(new Sijainti(1, 7), 9);
        asd.add(olio);
        rakennettava.rakennaTorni();
        rakennettava.getTorni().etsiKohde(asd, rakennettava.getSijainti());
        int elama = olio.getElama();
        elama -= rakennettava.getTorni().getVoima();
        rakennettava.getTorni().ammu();
        assertEquals(olio.getElama(), elama);
    }

    @Test
    public void torniEiAmmuOlematonta() {
        ArrayList<Hirvio> asd = new ArrayList<>();
        Hirvio olio = new Hirvio(new Sijainti(1, 7), 9);
        asd.add(olio);
        rakennettava.rakennaTorni();
        int elama = olio.getElama();
        rakennettava.getTorni().ammu();
        assertEquals(olio.getElama(), elama);
    }
}
