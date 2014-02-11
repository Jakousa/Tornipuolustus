package tornipuolustus.tornipuolustus;

import java.util.Scanner;
import javax.swing.SwingUtilities;
import tornipuolustus.ui.Kayttoliittyma;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Peli tornipuolustus = new Peli(new Scanner(System.in));
        
        Kayttoliittyma kali = new Kayttoliittyma(tornipuolustus);
        SwingUtilities.invokeLater(kali);

        while (kali.getAlusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        tornipuolustus.setAlusta(kali.getAlusta());

        tornipuolustus.pelaa();
    }
}
