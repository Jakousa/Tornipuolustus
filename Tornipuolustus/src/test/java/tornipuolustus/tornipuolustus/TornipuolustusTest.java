package tornipuolustus.tornipuolustus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.ruudut.*;
import tornipuolustus.toimijat.Hirvio;
import tornipuolustus.toimijat.Torni;

public class TornipuolustusTest {

    Kentta kentta = new Kentta(10);

    //HUOM! Seuraavia testejä muokattava
    @Test
    public void kentanTaytostaRuudukonAlkioidenMaaraKoonNelio() {
        kentta.tayta();
        assertEquals(kentta.getRuudukko().size(), 100);
    }
    
    @Test
    public void tornitAmpuvatKentalta() {
        kentta.tayta();
        for (Rakennettava rakennettava1 : kentta.getRakennettavat()) {
            if (rakennettava1.getSijainti().getX() == 3 || rakennettava1.getSijainti().getX() == 4) {
                rakennettava1.rakennaTorni();
            }
        }
        kentta.lisaaHirvio(3);
        kentta.liikutaHirvioita();
        kentta.lisaaHirvio(3);
        for (int i = 0; i < 10; i++) {
            kentta.liikutaHirvioita();            
            kentta.tornitAmpuvat();
        }
        boolean kaikkikuolleet = true;
        if (!kentta.getHirviot().isEmpty()) {
            kaikkikuolleet = false;
        }
        assertTrue(kaikkikuolleet);
    }

    @Test
    public void tayttoLisaaRakennettavia() {
        kentta.tayta();
        String testi1 = kentta.getRuudukko().get(1).toString();
        String testi2 = kentta.getRuudukko().get(49).toString();
        assertEquals(testi1, " ");
        assertEquals(testi2, "#");
    }
}
