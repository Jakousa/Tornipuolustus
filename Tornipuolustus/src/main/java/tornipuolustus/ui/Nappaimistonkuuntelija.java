/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tornipuolustus.tornipuolustus.Pelaaja;
import tornipuolustus.tornipuolustus.Peli;

public class Nappaimistonkuuntelija implements KeyListener {

    private Pelaaja pelaaja;
    private Peli peli;

    Nappaimistonkuuntelija(Pelaaja pelaaja, Peli tornipuolustus) {
        this.pelaaja = pelaaja;
        this.peli = tornipuolustus;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (pelaaja.getKursori().getX() - 1 >= 0) {
                pelaaja.getKursori().setX(pelaaja.getKursori().getX() - 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (pelaaja.getKursori().getX() + 1 < 10) {
                pelaaja.getKursori().setX(pelaaja.getKursori().getX() + 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (pelaaja.getKursori().getY() - 1 >= 0) {
                pelaaja.getKursori().setY(pelaaja.getKursori().getY() - 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (pelaaja.getKursori().getY() + 1 < 10) {
                pelaaja.getKursori().setY(pelaaja.getKursori().getY() + 1);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            peli.tornia(pelaaja.getKursori().getX(), pelaaja.getKursori().getY());
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            peli.jyraa(pelaaja.getKursori().getX(), pelaaja.getKursori().getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
