package tornipuolustus.ruudut;

import tornipuolustus.tornipuolustus.Sijainti;

public abstract class Ruutu implements Comparable<Ruutu> {

    private Sijainti sijainti;

    public Ruutu(int x, int y) {
        this.sijainti = new Sijainti(x, y);
    }

    public Sijainti getSijainti() {
        return sijainti;
    }

    /**
     * Vertailee vain sijainteja, riippumatta sisällöstä
     *
     * @param r Kuljettava tai Rakennettava
     * @return sijainnin compareto arvon
     */
    @Override
    public int compareTo(Ruutu r) {
        return this.sijainti.compareTo(r.getSijainti());
    }
}
