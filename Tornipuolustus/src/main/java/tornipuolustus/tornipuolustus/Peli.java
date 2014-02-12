package tornipuolustus.tornipuolustus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Timer;
import tornipuolustus.ui.Piirtoalusta;

public class Peli extends Timer implements ActionListener {

    private Kentta kentta;
    private Scanner lukija;
    private boolean jatkuu;
    private boolean omaVuoro;
    private Pelaaja pelaaja;
    private int vaikeus;
    private int torninHinta = 200;
    private Piirtoalusta piirtoalusta;

    public Peli(Scanner lukija) {
        super(1000, null);
        kentta = new Kentta(10);
        jatkuu = true;
        omaVuoro = true;
        this.lukija = lukija;
        pelaaja = new Pelaaja();

        addActionListener(this);
        setInitialDelay(500);
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void setAlusta(Piirtoalusta alusta) {
        piirtoalusta = alusta;
    }

    /**
     * asetetaan vaikeustaso pelille ja kutsuu pelaajaluokkaa
     */
    public void asetaVaikeustaso() {
        System.out.println("Valitse vaikeustaso: H helppo, K keskitaso, V vaikea");
        String vaikeustaso = lukija.nextLine();
        if (vaikeustaso.equalsIgnoreCase("V")) {
            this.vaikeus = 3;
        } else if (vaikeustaso.equalsIgnoreCase("K")) {
            this.vaikeus = 2;
        } else {
            if (!vaikeustaso.equalsIgnoreCase("H")) {
                System.out.println("Valintaa ei tunnistettu, improvisoidaan:");
            }
            System.out.println("Valitsit helpon vaikeustason");
            this.vaikeus = 1;
        }

        pelaaja.aloitusElama(vaikeus);
        pelaaja.aloitusRahat(vaikeus);
    }

    /**
     * asettaa (pelaajan) omanvuoron arvoksi false
     */
    public void lopetaVuoro() {
        omaVuoro = false;
    }
    

    /**
     * Tulostaa pelin kannalta hyödyllistä informaatiota pelaajan nähtäville.
     */
    public void tilanne() {
        System.out.println("Rakennushinta: " + this.torninHinta + "     Rahasi: " + pelaaja.getRahat());
        System.out.println("Lisää torni: Z / Jyrää torni: X / Aloita: ENTER");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        piirtoalusta.paivita();
    }

    /**
     * Pelaajan vuoro tapahtuu tässä metodissa.
     */
    public void pelaa() {
        asetaVaikeustaso();
        this.start();
        kentta.tayta();
        int kierrosnumero = 1;
        tilanne();

        while (jatkuu) {
            piirtoalusta.paivita();
            if (!omaVuoro) {
                peliPaalle(kierrosnumero);
                kierrosnumero++;
                tilanne();
                omaVuoro = true;
            }
        }
        System.out.println("Peli päättyi");
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
            System.out.println("Rakennus onnistui");
        } else {
            System.out.println("Rakennus epäonnistui");
        }
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
        } else {
            System.out.println("Purku epäonnistui");
        }
        tilanne();
    }

    /**
     * Pelin toiminta, kun pelaaja on lopettanut vuoronsa
     */
    private void peliPaalle(int kierrosnumero) {
        omaVuoro = true;
        for (int i = 0; i < kierrosnumero * 6; i++) {
            if (i % 3 == 0 && i < 15 + kierrosnumero * 6) {
                kentta.lisaaHirvio(4 * kierrosnumero * this.vaikeus);
            }

            kentta.liikutaHirvioita();

            piirtoalusta.paivita();
            kentta.piirra();
            int osumista = kentta.tornitAmpuvat();
            pelaaja.tienaa(osumista);

            if (kentta.paasikoLapi()) {
                pelaaja.otaOsumaa();
            }
            System.out.println("");
            System.out.println("elamasi: " + pelaaja.getElama() + " pisteesi: " + pelaaja.getPisteet());
            try {
                Thread.sleep(400);
            } catch (Exception e) {
            }

            if (pelaaja.getElama() <= 0) {
                jatkuu = false;
                break;
            }
        }
    }
}
