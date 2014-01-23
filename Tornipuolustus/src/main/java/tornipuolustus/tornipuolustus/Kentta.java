package tornipuolustus.tornipuolustus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tornipuolustus.ruudut.*;

public class Kentta {

    private List<Ruutu> ruudukko;
    private int koko;

    public Kentta(int koko) {
        this.ruudukko = new ArrayList<Ruutu>();
        this.koko = koko;
    }

    public List<Ruutu> getRuudukko() {
        return ruudukko;
    }

    public int getKoko() {
        return koko;
    }

    public void tayta() {     //Laajennus? lisätään parametrit ja ladataan jostakin millainen kenttä
                              //tällä hetkellä riittäköön jokin default tyyppinen
                              //muokkaaminen tulee vaikuttamaan testeihin myös
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
        Collections.sort(ruudukko);
        for (Ruutu ruutu : ruudukko) {
            System.out.print(ruutu.toString());
            if (ruutu.getX() == this.koko-1) {
                System.out.println("");
            }
        }
    }
}
