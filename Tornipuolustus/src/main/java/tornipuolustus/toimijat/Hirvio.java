/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.toimijat;

import java.util.ArrayList;
import java.util.List;
import tornipuolustus.ruudut.Kuljettava;
import tornipuolustus.tornipuolustus.Sijainti;

public class Hirvio {

    private int suunta;
    private Sijainti sijainti;
    private Sijainti edellinen;
    private int elama;

    public Hirvio(Sijainti sijainti, int elama) {
        this.sijainti = sijainti;
        this.elama = elama;
        this.edellinen = sijainti;
    }

    public int getElama() {
        return elama;
    }

    public void teeSuunta(Sijainti minne) {
        int y = minne.getY() - sijainti.getY();
        int x = minne.getX() - sijainti.getX();
        if (x == 0 && y == 0) {
            System.out.println("Jotain meni vikaan by: teeSuunta");
        }
        if (y == -1) {
            suunta = 1;
        } else if (x == 1) {
            suunta = 2;
        } else if (y == 1) {
            suunta = 3;
        } else if (x == -1) {
            suunta = 4;
        }
    }

    public void etsiPaamaara(List<Kuljettava> kuljettavat) { //Hyhhyh että on rumaa
        boolean syyta = false;
        for (Kuljettava kuljettava : kuljettavat) {
            if (kuljettava.getSijainti().equals(sijainti)) {
                syyta = true;
            }
        }
        if (syyta) {

            Kuljettava vaihtoehto1 = null;
            double vaihtoehto1Etaisyys = 50.0;
            Kuljettava vaihtoehto2 = null;
            double vaihtoehto2Etaisyys = 50.0;

            for (Kuljettava kuljettava : kuljettavat) {
                double kuljettavanEtaisyys = kuljettava.getSijainti().etaisyys(sijainti);

                if (this.sijainti.equals(kuljettava.getSijainti())) {
                    continue;
                }

                if (kuljettavanEtaisyys == 1.0) {
                    if (vaihtoehto1 == null) {
                        vaihtoehto1 = kuljettava;
                        vaihtoehto1Etaisyys = 1.0;
                    } else {
                        vaihtoehto2 = kuljettava;
                        vaihtoehto1Etaisyys = 1.0;
                    }

                }
            }

            if (vaihtoehto1 != null && vaihtoehto2 == null) {
                teeSuunta(vaihtoehto1.getSijainti());
            } else {

                double etaisyysEdelliseen1 = vaihtoehto1.getSijainti().etaisyys(edellinen);
                double etaisyysEdelliseen2 = vaihtoehto2.getSijainti().etaisyys(edellinen);
                if (etaisyysEdelliseen1 < etaisyysEdelliseen2) {
                    teeSuunta(vaihtoehto2.getSijainti());
                } else {
                    teeSuunta(vaihtoehto1.getSijainti());
                }
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

    public void liiku(List<Kuljettava> kuljettavat) {
        etsiPaamaara(kuljettavat);
        edellinen = sijainti;
        if (suunta == 1) {
            this.sijainti = new Sijainti(sijainti.getX(),sijainti.getY() - 1);
        } else if (suunta == 2) {
            this.sijainti = new Sijainti(sijainti.getX() + 1, sijainti.getY());
        } else if (suunta == 3) {
            this.sijainti = new Sijainti(sijainti.getX(),sijainti.getY() + 1);
        } else {
            this.sijainti = new Sijainti(sijainti.getX() - 1, sijainti.getY());
        }
    }
}
