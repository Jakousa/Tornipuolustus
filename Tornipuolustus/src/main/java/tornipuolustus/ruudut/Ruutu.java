/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ruudut;

import tornipuolustus.tornipuolustus.Sijainti;

public abstract class Ruutu implements Comparable<Ruutu> {

    private Sijainti sijainti;

    public Ruutu(int x, int y) {
        this.sijainti = new Sijainti(x,y);
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    @Override
    public int compareTo(Ruutu r) {
        if (r.getSijainti().getY() < this.getSijainti().getY()) {
            return 1;
        }
        if (r.getSijainti().getY() > this.getSijainti().getY()) {
            return -1;
        }
        if (r.getSijainti().getX() < this.getSijainti().getX()) {
            return 1;
        }
        return -1;
    }
}
