package tornipuolustus.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
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
        int leveys = 32 * sivunPituus + 10;
        int korkeus = 32 * sivunPituus + 10;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);

        alusta = new Piirtoalusta(tornipuolustus, sivunPituus);
        container.add(alusta);
        frame.addKeyListener(new Nappaimistonkuuntelija(tornipuolustus.getPelaaja(), tornipuolustus));

        
        JLabel tilanne1;
        JLabel tilanne2;
        tilanne1 = new JLabel(tornipuolustus.ohjeet1());
        tilanne2 = new JLabel(tornipuolustus.ohjeet2());
        container.add(tilanne1);
        container.add(tilanne2);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Piirtoalusta getAlusta() {
        return alusta;
    }
}
