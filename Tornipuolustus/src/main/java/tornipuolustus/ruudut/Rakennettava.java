package tornipuolustus.ruudut;

import tornipuolustus.toimijat.Torni;

public class Rakennettava extends Ruutu {

    private Torni torni;

    public Rakennettava(int x, int y) {
        super(x, y);
        torni = null;
    }

    public Torni getTorni() {
        return torni;
    }

    /**
     * Rakentaa tornin ruutuun. Joka tapauksessa torni sijaitsee ruudussa käytön
     * jälkeen.
     *
     * @return tosi, mikäli UUDEN tornin rakentaminen onnistui
     */
    public boolean rakennaTorni() {
        if (torni == null) {
            torni = new Torni();
            return true;
        }
        return false;
    }

    /**
     * Poistaa tornin ruudusta. Joka tapauksessa ruudussa ei sijaitse tornia
     * käytön jälkeen.
     *
     * @return tosi, mikäli torni poistettiin
     */
    public boolean tuhoaTorni() {
        if (torni != null) {
            torni = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (torni != null) {
            return "T";
        }
        return "#";
    }

    @Override
    public int compareTo(Ruutu r) {
        return super.compareTo(r);
    }
}
