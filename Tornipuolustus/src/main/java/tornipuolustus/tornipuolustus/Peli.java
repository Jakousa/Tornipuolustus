package tornipuolustus.tornipuolustus;

import java.util.Scanner;

public class Peli {

    private Kentta kentta;
    private Scanner lukija;
    private boolean jatkuu;
    private Pelaaja pelaaja;
    private int vaikeus;
    private int torninHinta = 200;

    public Peli(Scanner lukija) {
        kentta = new Kentta(10);
        jatkuu = true;
        this.lukija = lukija;
        pelaaja = new Pelaaja();
    }

    public Kentta getKentta() {
        return kentta;
    }

    /**
     * asetetaan vaikeustaso pelille, sisältää paljon kovakoodattuja lukuja
     */
    public void asetaVaikeustaso() {
        System.out.println("Valitse vaikeustaso: H helppo, K keskitaso, V vaikea");
        String vaikeustaso = lukija.nextLine();

        if (vaikeustaso.equals("V")) {
            this.vaikeus = 3;
        } else if (vaikeustaso.equals("K")) {
            this.vaikeus = 2;
        } else {
            if (!vaikeustaso.equals("H")) {
                System.out.println("Teksikäyttöliittymä liian oldschool?");
            }
            System.out.println("Valitsit helpon vaikeustason");
            this.vaikeus = 1;
        }

        pelaaja.aloitusElama(vaikeus);
        pelaaja.aloitusRahat(vaikeus);
    }

    /**
     * Käyttältä kysytään toimenpidettä kunnes elämät loppuvat.
     */
    public void start() {
        asetaVaikeustaso();


        kentta.tayta();
        int kierrosnumero = 1;
        while (jatkuu) {
            kentta.piirra();

            System.out.println("Lisää torni: 1 / Jyrää torni: 2 / Aloita: 3 / Poistu: 0");
            System.out.println("Rakennushinta: " + this.torninHinta + "     Rahasi: " + pelaaja.getRahat());
            int valinta = Integer.parseInt(lukija.nextLine());

            if (valinta == 0) {
                break;
            }
            if (valinta == 1) {
                tornia();
            }
            if (valinta == 2) {
                jyraa();
            }
            if (valinta == 3) {
                peliPaalle(kierrosnumero);
                kierrosnumero++;
            }
        }
        System.out.println("Peli päättyi");
    }

    private void tornia() {
        System.out.println("x koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.println("y koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());
        
        boolean onnistui = false;;
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
    }

    private void jyraa() {
        System.out.println("x koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.println("y koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());
        if (kentta.poistaTorni(x, y)) {
            System.out.println("Purku onnistui");
            pelaaja.tuhoaTorni(this.torninHinta);
        } else {
            System.out.println("Purku epäonnistui");
        }
    }

    /**
     * Pelin toiminta
     */
    private void peliPaalle(int kierrosnumero) {
        for (int i = 0; i < 30 + kierrosnumero * 6; i++) {
            if (i % 3 == 0 && i < kierrosnumero * 6) {
                kentta.lisaaHirvio(4 * kierrosnumero * this.vaikeus);
            }

            kentta.liikutaHirvioita();

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
