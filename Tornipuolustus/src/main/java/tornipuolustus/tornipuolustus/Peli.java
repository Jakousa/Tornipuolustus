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
    private boolean pelaajanVuoro;
    private Pelaaja pelaaja;
    private int vaikeus;
    private int torninHinta;
    private Piirtoalusta piirtoalusta;

    public Peli(Scanner lukija) {
        super(1000, null);
        kentta = new Kentta(10);
        jatkuu = true;
        pelaajanVuoro = true;
        this.lukija = lukija;
        pelaaja = new Pelaaja();
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
     * Asetetaan vaikeustaso pelille
     */
    public void asetaVaikeustaso() {
        System.out.println("Valitse vaikeustaso: H helppo, K keskitaso, V vaikea");
        int haluttu;
        String vaikeustaso = lukija.nextLine();
        if (vaikeustaso.equalsIgnoreCase("V")) {
            haluttu = 3;
        } else if (vaikeustaso.equalsIgnoreCase("K")) {
            haluttu = 2;
        } else {
            if (!vaikeustaso.equalsIgnoreCase("H")) {
                System.out.println("Valintaa ei tunnistettu, improvisoidaan:");
            }
            System.out.println("Valitsit helpon vaikeustason");
            haluttu = 1;
        }
        setVaikeustaso(haluttu);
    }

    public void setVaikeustaso(int vaikeus) {
        this.vaikeus = vaikeus;
        pelaaja.aloitusElama(vaikeus);
        pelaaja.aloitusRahat(2); //???
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
            if (!pelaajanVuoro) {
                peliPaalle(kierrosnumero);
                kierrosnumero++;
                tilanne();
                pelaajanVuoro = true;
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

            System.out.println("");
            System.out.println("elamasi: " + pelaaja.getElama() + " pisteesi: " + pelaaja.getPisteet());
            try {
                Thread.sleep(400);
            } catch (Exception e) {
            }
        }
    }
}
