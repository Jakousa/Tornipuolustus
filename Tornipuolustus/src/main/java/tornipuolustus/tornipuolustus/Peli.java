package tornipuolustus.tornipuolustus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Timer;
import tornipuolustus.ui.Piirtoalusta;

public class Peli extends Timer implements ActionListener{

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
     * asetetaan vaikeustaso pelille
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
                System.out.println("Teksikäyttöliittymä too oldschool");
            }
            System.out.println("Valitsit helpon vaikeustason");
            this.vaikeus = 1;
        }

        pelaaja.aloitusElama(vaikeus);
        pelaaja.aloitusRahat(vaikeus);
    }
    
    public void lopetaVuoro() {
        omaVuoro = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        piirtoalusta.paivita();
        if (pelaaja.getRahat() < this.torninHinta) {
            omaVuoro = false;
        }
    }
        
    public void pelaa() {
        asetaVaikeustaso();
        this.start();
        kentta.tayta();
        int kierrosnumero = 1;
        
        while (jatkuu) {
            //kentta.piirra();
            piirtoalusta.paivita();
            
            //this.start();
            
           // System.out.println("Lisää torni: 1 / Jyrää torni: 2 / Aloita: 3 / Poistu: 0");
           // System.out.println("Rakennushinta: " + this.torninHinta + "     Rahasi: " + pelaaja.getRahat());
           // int valinta = Integer.parseInt(lukija.nextLine());

           // if (valinta == 0) {
//                break;
//            }
//            if (valinta == 1) {
//                tornia();
//            }
//            if (valinta == 2) {
//                jyraa();
//            }
            if (!omaVuoro) {
                peliPaalle(kierrosnumero);
                kierrosnumero++;
                System.out.println("Rakennushinta: " + this.torninHinta + "     Rahasi: " + pelaaja.getRahat());
            }
        }
        System.out.println("Peli päättyi");
    }

    public void tornia() {
        System.out.println("x koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.println("y koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());
        
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
    }
    
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
    }

    public void jyraa() {
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
    
    public void jyraa(int x, int y) {
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
