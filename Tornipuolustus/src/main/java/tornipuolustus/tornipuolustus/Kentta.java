package tornipuolustus.tornipuolustus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tornipuolustus.ruudut.*;
import tornipuolustus.toimijat.Hirvio;
import tornipuolustus.toimijat.Torni;

public class Kentta {

    private List<Kuljettava> kuljettavat;
    private List<Rakennettava> rakennettavat;
    private List<Hirvio> hirviot;
    private int koko;

    public Kentta(int koko) {
        this.kuljettavat = new ArrayList<Kuljettava>();
        this.rakennettavat = new ArrayList<Rakennettava>();
        this.hirviot = new ArrayList<Hirvio>();
        this.koko = koko;
    }
    
    public void rakennaTorni(Sijainti sijainti){
        for (Rakennettava ruutu : rakennettavat) {
            if (sijainti.equals(ruutu.getSijainti())) {
                boolean onnistuiko = ruutu.rakennaTorni();
            }
        }
    }
    
    public void poistaTorni(Sijainti sijainti){
        for (Rakennettava ruutu : rakennettavat) {
            if (sijainti.equals(ruutu.getSijainti())) {
                boolean onnistuiko = ruutu.tuhoaTorni();
            }
        }
    }
    
    

    public void tayta() {     //Laajennus? lisätään parametrit ja ladataan jostakin millainen kenttä
                              //tällä hetkellä riittäköön jokin default tyyppinen
                              //muokkaaminen tulee vaikuttamaan testeihin myös
        for (int i = 0; i < this.koko; i++) {
            if (i == 5) {
                for (int j = 0; j < this.koko; j++) {
                    kuljettavat.add(new Kuljettava(j, i));
                }
            } else {
                for (int j = 0; j < this.koko; j++) {
                    rakennettavat.add(new Rakennettava(j, i));
                }
            }
        }
    }
    
    public void piirra() {
        List<Ruutu> ruudukko = new ArrayList<>();
        
        ruudukko.addAll(kuljettavat);
        ruudukko.addAll(rakennettavat);
        Collections.sort(ruudukko);
        for (Ruutu ruutu : ruudukko) {
            System.out.print(ruutu.toString());
            if (ruutu.getSijainti().getX() == this.koko-1) {
                System.out.println("");
            }
        }
    }
}
