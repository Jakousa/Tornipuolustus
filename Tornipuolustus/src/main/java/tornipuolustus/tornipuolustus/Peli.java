package tornipuolustus.tornipuolustus;

import java.util.Scanner;

public class Peli {

    private Kentta kentta;
    private Scanner lukija;
    private boolean jatkuu;
    private int elamasi;

    public Peli(Scanner lukija) {
        kentta = new Kentta(10);
        jatkuu = true;
        this.lukija = lukija;
        elamasi = 7;
    }

    public Kentta getKentta() {
        return kentta;
    }

    public void start() {
        kentta.tayta();
        while (jatkuu) {
            kentta.piirra();
            
            System.out.println("Lisää torni: 1 / Poista torni: 2 / Aloita: 3 / Poistu: 0");
            int valinta = Integer.parseInt(lukija.nextLine());

            if (valinta == 0) {
                break;
            }
            if (valinta == 1) {
                valinta1();
            }
            if (valinta == 2) {
                valinta2();
            }
            
            if (valinta == 3) {
                
            }
            
        }
        System.out.println("Peli päättyi");
    }

    private void valinta1() {
        System.out.println("x koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.println("y koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());
        if (kentta.rakennaTorni(x, y)) {
            System.out.println("Rakennus onnistui");
        } else {
            System.out.println("Rakennus epäonnistui");
        }
    }

    private void valinta2() {
        System.out.println("x koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.println("y koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());
        if (kentta.poistaTorni(x, y)) {
            System.out.println("Purku onnistui");
        } else {
            System.out.println("Purku epäonnistui");
        }
    }
}
