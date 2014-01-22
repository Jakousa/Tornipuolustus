package tornipuolustus.tornipuolustus;

import java.util.ArrayList;
import java.util.List;
import tornipuolustus.ruudut.*;

public class Kentta {

    private List<Ruutu> ruudukko;
    private int koko;

    public Kentta(int koko) {
        this.ruudukko = new ArrayList<Ruutu>();
        this.koko = koko;
    }

    public void luo() { //lisätään parametrit ja ladataan jostakin millainen kenttä
        //tällä hetkellä riittäköön jokin default tyyppinen
        for (int i = 0; i < this.koko; i++) {
            if (i == 5) {
                for (int j = 0; j < this.koko; j++) {
                    ruudukko.add(new Kuljettava(j, i));
                }
            } else {
                for (int j = 0; j < this.koko; j++) {
                    ruudukko.add(new Rakennettava(j, i));
                }
            }
        }

    }
    
    public void piirra() {
        for (Ruutu ruutu : ruudukko) {
            ruutu.piirra();
            if (ruutu.getX() == this.koko-1) {
                System.out.println("");
            }
        }
    }
}
