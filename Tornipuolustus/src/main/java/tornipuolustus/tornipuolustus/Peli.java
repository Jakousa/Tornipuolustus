package tornipuolustus.tornipuolustus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Timer;
import tornipuolustus.ui.Piirtoalusta;

/**
 * Peli luokka ohjaa pelin kulkua
 *
 */
public class Peli extends Timer implements ActionListener {

    private Kentta kentta;
    private Scanner lukija;
    private boolean jatkuu;
    private boolean pelaajanVuoro;
    private Pelaaja pelaaja;
    private int vaikeus;
    private int torninHinta;
    private Piirtoalusta piirtoalusta;

    public Peli(Scanner lukija) {
        super(1000, null);
        kentta = new Kentta(10);
        jatkuu = true;
        pelaaja = new Pelaaja();
        pelaajanVuoro = true;
        this.lukija = lukija;
        torninHinta = 200;


        addActionListener(this);
        setInitialDelay(500);
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public boolean getPelaajanVuoro() {
        return pelaajanVuoro;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void setAlusta(Piirtoalusta alusta) {
        piirtoalusta = alusta;
    }

    /**
     * Asetetaan vaikeustaso pelille Tähän voidaan heittää numeroita jotta
     * selvitetään milloin peli on sopivan vaikea.
     */
    public void asetaVaikeustaso() {
        this.vaikeus = 2;
        pelaaja.aloitusElama(vaikeus);
        pelaaja.aloitusRahat(vaikeus);
    }

    /**
     * Asettaa pelaajan vuoron arvoksi false
     */
    public void lopetaVuoro() {
        pelaajanVuoro = false;
    }

    /**
     * Tulostaa pelin kannalta hyödyllistä informaatiota pelaajan nähtäville.
     */
    public String tilanne() {
        if (jatkuu) {
            return "Pisteet: " + this.pelaaja.getPisteet() + " Rahasi: " + pelaaja.getRahat() + " Elämäsi: " + this.pelaaja.getElama();
        } else {
            return "Peli päättyi, loppupisteesi ovat: " + this.pelaaja.getPisteet();
        }
    }

    /**
     * Ohjeet kahdessa osassa kun kahdelle riville kirjoittaminen epäonnistui
     *
     * @return palauttaa ohjeita joita käyttöliittymä piirtelee
     */
    public String ohjeet1() {
        return "Z-rakentaa / X-jyrää / ENTER-aloita";
    }

    /**
     * Ohjeet kahdessa osassa kun kahdelle riville kirjoittaminen epäonnistui
     * 
     * @return palauttaa ohjeita joita käyttöliittymä piirtelee
     */
    public String ohjeet2() {
        return "Tornin rakennushinta " + this.torninHinta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        piirtoalusta.paivita();
    }

    /**
     * Pelaajan vuoro tapahtuu tässä metodissa.
     */
    public void pelaa() {
        this.start();
        asetaVaikeustaso();
        kentta.tayta();
        int kierrosnumero = 1;

        while (jatkuu) {
            piirtoalusta.paivita();
            if (!pelaajanVuoro) {
                peliPaalle(kierrosnumero);
                kierrosnumero++;
                pelaajanVuoro = true;
            }
        }
//        System.out.println("Peli päättyi");
    }

    /**
     * Metodi tarkistaa riittääkö pelaajan rahat rakentamiseen ja mikäli tämä
     * onnistuu pyytää metodi kenttää rakentamaan tornin sijaintiin
     *
     * @param x haluttu tornin sijainti x
     * @param y haluttu tornin sijainti y
     */
    public void tornia(int x, int y) {
        boolean onnistui = false;
        if (pelaaja.getRahat() - this.torninHinta >= 0) {
            if (kentta.rakennaTorni(x, y)) {
                onnistui = true;
            }
        }
        if (onnistui) {
            pelaaja.rakenna(this.torninHinta);
//            System.out.println("Rakennus onnistui");
        } 
//        else {
//            System.out.println("Rakennus epäonnistui");
//        }
        tilanne();
    }

    /**
     * Metodi poistaa tornin ja lisää sen mukaisesti pelaajan rahoihin
     *
     * @param x haluttu purkutapahtuman sijainti x
     * @param y haluttu purkutapahtuman sijainti y
     */
    public void jyraa(int x, int y) {
        if (kentta.poistaTorni(x, y)) {
            System.out.println("Purku onnistui");
            pelaaja.tuhoaTorni(this.torninHinta);
        } 
        //else {
//            System.out.println("Purku epäonnistui");
//        }
        tilanne();
    }

    /**
     * Pelin toiminta, kun pelaaja on lopettanut vuoronsa
     */
    private void peliPaalle(int kierrosnumero) {
        pelaajanVuoro = true;
        for (int i = 0; i < kierrosnumero * 6; i++) {
            if (i % 3 == 0 && i < 15 + kierrosnumero * 6) {
                kentta.lisaaHirvio(4 * (kierrosnumero));
            }

            kentta.liikutaHirvioita();
            
            piirtoalusta.paivita();

            int osumista = kentta.tornitAmpuvat();
            if (pelaaja.getRahat() < 500) {
                pelaaja.tienaa(osumista);
            }

            if (kentta.paasikoLapi()) {
                pelaaja.otaOsumaa();
            }
            if (pelaaja.getElama() <= 0) {
                jatkuu = false;
                break;
            }

//            System.out.println("");
//            System.out.println("elamasi: " + pelaaja.getElama() + " pisteesi: " + pelaaja.getPisteet());
            try {
                Thread.sleep(400);
            } catch (Exception e) {
            }
        }
    }
}
