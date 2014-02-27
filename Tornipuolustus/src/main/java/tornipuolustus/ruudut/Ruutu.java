package tornipuolustus.ruudut;

import tornipuolustus.tornipuolustus.Sijainti;

/**
 * abstrakti luokka jota Rakennettava ja Kuljettava luokat toteuttavat
 * 
 */
public abstract class Ruutu implements Comparable<Ruutu> {

    private Sijainti sijainti;

    public Ruutu(int x, int y) {
        this.sijainti = new Sijainti(x, y);
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    @Override
    public int compareTo(Ruutu r) {
        return this.sijainti.compareTo(r.getSijainti());
    }
}
