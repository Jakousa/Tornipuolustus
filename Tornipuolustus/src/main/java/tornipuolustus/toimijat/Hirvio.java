/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.toimijat;

import java.util.ArrayList;
import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.tornipuolustus.Sijainti;

public class Hirvio {

    private int suunta;
    private Sijainti sijainti;
    private Sijainti edellinen;
    private int elama;

    public Hirvio(Sijainti sijainti, int suunta) {
        this.sijainti = sijainti;
        this.suunta = suunta;
        this.elama = 100;
        this.edellinen = sijainti;
    }

    public int getElama() {
        return elama;
    }
    
    public void teeSuunta() {
        
    }

    public void etsiPaamaara(ArrayList<Kuljettava> kuljettavat) { //Hyhhyh että on rumaa
        Kuljettava vaihtoehto1;
        double vaihtoehto1Etaisyys = 0;
        Kuljettava vaihtoehto2;
        double vaihtoehto2Etaisyys = 0;
        
        for (Kuljettava kuljettava : kuljettavat) {
            double kuljettavanEtaisyys = kuljettava.getSijainti().etaisyys(sijainti);
            
            if (this.sijainti.equals(kuljettava.getSijainti())) {
                continue;
            }
            
            if (kuljettavat.indexOf(kuljettava) == 0) {
                vaihtoehto1Etaisyys = kuljettava.getSijainti().etaisyys(sijainti);
                vaihtoehto1 = kuljettava;
                vaihtoehto2Etaisyys = kuljettava.getSijainti().etaisyys(sijainti);
                vaihtoehto2 = kuljettava;
            }
            
            if (kuljettavanEtaisyys <= vaihtoehto1Etaisyys) {
                if (kuljettavanEtaisyys < vaihtoehto2Etaisyys)
                vaihtoehto1 = kuljettava;
            }
        }
    }

    public void setElama(int elama) {
        this.elama = elama;
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
        edellinen = sijainti;
        if (suunta == 1) {
            sijainti.setY(sijainti.getY() - 1);
        } else if (suunta == 2) {
            sijainti.setX(sijainti.getX() + 1);
        } else if (suunta == 3) {
            sijainti.setY(sijainti.getY() + 1);
        } else {
            sijainti.setX(sijainti.getX() - 1);
        }
    }
}
