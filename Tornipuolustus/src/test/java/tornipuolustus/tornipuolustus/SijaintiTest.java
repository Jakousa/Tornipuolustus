/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.tornipuolustus;

import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.ruudut.Rakennettava;

/**
 *
 * @author Hatchy
 */
public class SijaintiTest {
    
    Kentta kentta = new Kentta(10);
    Rakennettava rakennettava = new Rakennettava(1, 8);
    Kuljettava kuljettava = new Kuljettava(0, 8);

    @Test
    public void rakennettavanSijaintiOikein() {
        assertEquals(rakennettava.getSijainti().getX(), 1);
        assertEquals(rakennettava.getSijainti().getY(), 8);
    }

    @Test
    public void kuljettavanSijaintiOikein() {
        assertEquals(kuljettava.getSijainti().getX(), 0);
        assertEquals(kuljettava.getSijainti().getY(), 8);
    }

    @Test
    public void jarjestaminenOnnistuu() {
        Kuljettava a = new Kuljettava(2, 0);
        Kuljettava b = new Kuljettava(0, 0);
        Kuljettava c = new Kuljettava(1, 0);
        Kuljettava d = new Kuljettava(0, 2);
        Kuljettava e = new Kuljettava(1, 2);
        Kuljettava f = new Kuljettava(0, 1);
        kentta.getKuljettavat().add(a);
        kentta.getKuljettavat().add(b);
        kentta.getKuljettavat().add(c);
        kentta.getKuljettavat().add(d);
        kentta.getKuljettavat().add(e);
        kentta.getKuljettavat().add(f);
        Collections.sort(kentta.getKuljettavat());
        assertEquals(kentta.getKuljettavat().get(0), b);
        assertEquals(kentta.getKuljettavat().get(1), c);
        assertEquals(kentta.getKuljettavat().get(2), a);
        assertEquals(kentta.getKuljettavat().get(3), f);
        assertEquals(kentta.getKuljettavat().get(4), d);
        assertEquals(kentta.getKuljettavat().get(5), e);
    }

    @Test
    public void jarjestaminenRakennettavilla() {
        Rakennettava a = new Rakennettava(2, 0);
        Rakennettava b = new Rakennettava(0, 0);
        kentta.getRakennettavat().add(a);
        kentta.getRakennettavat().add(b);
        Collections.sort(kentta.getRakennettavat());
        assertEquals(kentta.getRakennettavat().get(0), b);
        assertEquals(kentta.getRakennettavat().get(1), a);
    }

}
