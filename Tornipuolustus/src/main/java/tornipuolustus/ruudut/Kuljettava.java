package tornipuolustus.ruudut;

/**
 * Kuljettavat ovat ruutuja joita pitkin hirviöt kykynevät liikkumaan
 * 
 * 
 */
public class Kuljettava extends Ruutu {
    
    public Kuljettava(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public int compareTo(Ruutu r) {
        return super.compareTo(r);
    }
}
