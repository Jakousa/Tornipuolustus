/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tornipuolustus.tornipuolustus.Pelaaja;


public class Nappaimistonkuuntelija implements KeyListener{

    private Pelaaja pelaaja;
    
    Nappaimistonkuuntelija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }
    
    /**
     * lisätään hieno kursorinliikutus nuolinäppäimillä
     * 
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
