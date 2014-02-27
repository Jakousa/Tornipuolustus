/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.ruudut.Rakennettava;
import tornipuolustus.toimijat.Hirvio;
import tornipuolustus.tornipuolustus.Peli;


public class Piirtoalusta extends JPanel  {

    private Peli tornipuolustus ;
    private int palanleveys;
    
    public Piirtoalusta(Peli tornipuolustus, int palanSivunPituus) {
        this.tornipuolustus = tornipuolustus;
        this.palanleveys = 20;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        for (Rakennettava r : tornipuolustus.getKentta().getRakennettavat()) {
            if (r.getTorni() != null) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.PINK);
            }
            g.fill3DRect(r.getSijainti().getX()*palanleveys, r.getSijainti().getY()*palanleveys, palanleveys, palanleveys, true);
        }
        g.setColor(Color.white);
        for (Kuljettava k : tornipuolustus.getKentta().getKuljettavat()) {
            if (k.getSijainti().getX() == 0 || k.getSijainti().getX() == tornipuolustus.getKentta().getKoko()-1) {
                g.setColor(Color.magenta);
            } else {
                g.setColor(Color.white);
            }
            g.fill3DRect(k.getSijainti().getX()*palanleveys, k.getSijainti().getY()*palanleveys, palanleveys, palanleveys, false);;
        }
        g.setColor(Color.red);
        for (Hirvio hirvio : tornipuolustus.getKentta().getHirviot()) {
            g.fillOval(hirvio.getSijainti().getX()*palanleveys, hirvio.getSijainti().getY()*palanleveys, palanleveys, palanleveys);
        }
        g.setColor(Color.GREEN);
        g.fill3DRect(tornipuolustus.getPelaaja().getKursori().getX()*palanleveys, 
                tornipuolustus.getPelaaja().getKursori().getY()*palanleveys, 
                palanleveys, palanleveys, true);
        g.setColor(Color.BLACK);
        g.drawString(tornipuolustus.tilanne(), 0, 10*palanleveys + 30);
    }

    public void paivita() {
        repaint();
    }
}
