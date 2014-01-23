package tornipuolustus.tornipuolustus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.ruudut.*;

public class TornipuolustusTest {

    Kentta kentta = new Kentta(10);
    Rakennettava rakennettava = new Rakennettava(1, 8);
    Kuljettava kuljettava = new Kuljettava(0, 8);

    @Test
    public void kentanLuonnistaTyhjaRuudukko() {
        assertTrue(kentta.getRuudukko().isEmpty());
    }

    @Test
    public void kentanLuonnistaKoko() {
        assertEquals(kentta.getKoko(), 10);
    }

    @Test
    public void rakennettavaRuutuTyhja() {
        assertTrue(!rakennettava.getVaraus());
    }

    @Test
    public void rakennettavaPiirtyyOikeinVapaana() {
        String piirros = "O";
        assertEquals(rakennettava.toString(), piirros);
    }

    @Test
    public void rakennettavaPiirtyyOikeinVarattuna() {
        String piirros = "T";
        rakennettava.varaa();
        assertEquals(rakennettava.toString(), piirros);
    }

    @Test
    public void rakennettavanVoiVapauttaa() {
        rakennettava.varaa();
        rakennettava.vapauta();
        assertTrue(!rakennettava.getVaraus());
    }

    @Test
    public void kuljettavaPiirtyyOikein() {
        String piirros = ">";
        assertEquals(kuljettava.toString(), piirros);
    }

    @Test
    public void rakennettavanSijaintiOikein() {
        assertEquals(rakennettava.getX(), 1);
        assertEquals(rakennettava.getY(), 8);
    }

    @Test
    public void kuljettavanSijaintiOikein() {
        assertEquals(kuljettava.getX(), 0);
        assertEquals(kuljettava.getY(), 8);
    }
    
    @Test
    public void jarjestaminenOnnistuu() {
        Ruutu a = new Kuljettava(2,0);
        Ruutu b = new Kuljettava(0,0);
        Ruutu c = new Rakennettava(1,0);
        Ruutu d = new Rakennettava(0,2);
        Ruutu e = new Rakennettava(1,2);
        Ruutu f = new Rakennettava(0,1);
        kentta.getRuudukko().add(a);
        kentta.getRuudukko().add(b);
        kentta.getRuudukko().add(c);
        kentta.getRuudukko().add(d);
        kentta.getRuudukko().add(e);
        kentta.getRuudukko().add(f);
        Collections.sort(kentta.getRuudukko());
        assertEquals(kentta.getRuudukko().get(0), b);
        assertEquals(kentta.getRuudukko().get(1), c);
        assertEquals(kentta.getRuudukko().get(2), a);
        assertEquals(kentta.getRuudukko().get(3), f);
        assertEquals(kentta.getRuudukko().get(4), d);
        assertEquals(kentta.getRuudukko().get(5), e);
    }
    
    //HUOM! Seuraavia testejä ehkä muokattava jos lisätään
    //kyky luoda erilaisia kenttiä
    @Test
    public void kentanTaytostaRuudukonAlkioidenMaaraKoonNelio() {
        kentta.tayta();
        assertEquals(kentta.getRuudukko().size(), 100);
    }

    @Test
    public void tayttoLisaaKuljettaviaJaRakennettavia() {
        kentta.tayta();
        String rakennettavako = kentta.getRuudukko().get(1).toString();
        String kuljettavako = kentta.getRuudukko().get(59).toString();
        assertEquals(rakennettavako, "O");
        assertEquals(kuljettavako, ">");
    }

    @Test
    public void starttaaminenLuoJaTayttaaKentan() {
        Peli testi = new Peli();
        testi.start();
        assertEquals(testi.getKentta().getRuudukko().size(), 100);
    }
}
