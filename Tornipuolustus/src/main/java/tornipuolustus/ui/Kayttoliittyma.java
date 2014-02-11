/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.ui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tornipuolustus.tornipuolustus.Peli;


public class Kayttoliittyma implements Runnable {

    
    private JFrame frame;
    private Peli tornipuolustus;
    private int sivunPituus;
    private Piirtoalusta alusta;

    public Kayttoliittyma(Peli tornipuolustus) {
        this.tornipuolustus = tornipuolustus;
        this.sivunPituus = 10;
    }

    @Override
    public void run() {
        frame = new JFrame("Tornipuolustus");
        int leveys = 30 * sivunPituus + 10;
        int korkeus = 30 * sivunPituus + 10;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        alusta = new Piirtoalusta(tornipuolustus, sivunPituus);
        container.add(alusta);
        frame.addKeyListener(new Nappaimistonkuuntelija(tornipuolustus.getPelaaja(), tornipuolustus));
    }

    public JFrame getFrame() {
        return frame;
    }
    
    public Piirtoalusta getAlusta() {
        return alusta;
    }
}
