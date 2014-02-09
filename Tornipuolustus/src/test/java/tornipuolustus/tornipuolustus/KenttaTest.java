/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.tornipuolustus;

import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.ruudut.Kuljettava;

/**
 *
 * @author Hatchy
 */
public class KenttaTest {

    Kentta kentta = new Kentta(10);

    @Test
    public void kenttaRakentelee() {
        kentta.tayta();
        Collections.sort(kentta.getRakennettavat());
        kentta.rakennaTorni(0, 0);
        assertEquals(kentta.getRakennettavat().get(0).toString(), "T");
    }

    @Test
    public void kenttaRakentelee2() {
        kentta.tayta();
        Collections.sort(kentta.getRakennettavat());
        kentta.rakennaTorni(0, 0);
        assertTrue(!kentta.rakennaTorni(0, 0));
    }

    @Test
    public void kenttaTuhoaaTornin() {
        kentta.tayta();
        kentta.rakennaTorni(0, 0);
        assertTrue(kentta.poistaTorni(0, 0));
    }

    @Test
    public void kenttaTuhoaaTornin2() {
        kentta.tayta();
        kentta.rakennaTorni(0, 0);
        kentta.poistaTorni(0, 0);
        assertTrue(!kentta.poistaTorni(0, 0));
    }

    @Test
    public void kenttaLuoHirvion() {
        kentta.tayta();
        Collections.sort(kentta.getRuudukko());
        kentta.lisaaHirvio(9);
        boolean totta = false;
        for (Kuljettava k : kentta.getKuljettavat()) {
            if (k.getSijainti() == kentta.getHirviot().get(0).getSijainti()) {
                totta = true;
            }
        }
        assertTrue(totta);
    }

    @Test
    public void kenttaLiikuttaa() {
        kentta.tayta();
        Collections.sort(kentta.getRuudukko());
        kentta.lisaaHirvio(9);
        Sijainti sijainti = null;
        boolean totta = false;
        int x = kentta.getHirviot().get(0).getSijainti().getX();
        kentta.liikutaHirvioita();
        if (kentta.getHirviot().get(0).getSijainti().getX() != x) {
            totta = true;
        }
        assertTrue(totta);
    }
}
