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
    public void rakennettavaPiirtyyOikeinVapaana() {
        String piirros = "#";
        assertEquals(rakennettava.toString(), piirros);
    }

    @Test
    public void kuljettavaPiirtyyOikein() {
        String piirros = " ";
        assertEquals(kuljettava.toString(), piirros);
    }

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
    public void torniPiirtyy() {
        rakennettava.rakennaTorni();
        assertEquals(rakennettava.toString(), "T");
    }

    @Test
    public void hirvionElamat() {
        assertEquals(hirvio.getElama(), 9);
    }

    @Test
    public void hirvioLoytaaSuunnan(){
    ArrayList<Kuljettava> Kuljettavat = new ArrayList<>();
    Kuljettavat.add(hirvionKeski);
    Kuljettavat.add(hirvionOikea);
    hirvio.liiku(Kuljettavat);
    assertTrue(hirvio.getSijainti().equals(hirvionOikea.getSijainti()));
}
    
    

    //HUOM! Seuraavia testejä muokattava
    @Test
    public void kentanTaytostaRuudukonAlkioidenMaaraKoonNelio() {
        kentta.tayta();
        assertEquals(kentta.getRuudukko().size(), 100);
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
