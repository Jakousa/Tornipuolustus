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

    public boolean rakennaTorni() {
        if (torni == null) {
            torni = new Torni();
            return true;
        }
        return false;
    }

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
