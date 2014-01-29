/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.toimijat;

import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.tornipuolustus.Sijainti;


public class Hirvio {
    
    private int suunta;
    private Sijainti sijainti;
    private int elama;
    
    public Hirvio(Sijainti sijainti, int suunta) {
        this.sijainti = sijainti;
        this.suunta = suunta;
    }
    
    public void setSuunta(int suunta) {
        this.suunta = suunta;
    }
    
    public Sijainti getSijainti() {
        return sijainti;
    }
    
    public int getSuunta() {
        return suunta;
    }
    
    
    public void liiku() {
        if (suunta == 1) {
            sijainti.setY(sijainti.getY()-1);
        } else if (suunta == 2) {
            sijainti.setX(sijainti.getX()+1);
        } else if (suunta == 3) {
            sijainti.setY(sijainti.getY()+1);
        } else {
            sijainti.setX(sijainti.getX()-1);
        }
    }
}
