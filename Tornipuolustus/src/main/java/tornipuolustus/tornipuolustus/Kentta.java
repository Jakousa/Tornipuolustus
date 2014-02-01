package tornipuolustus.tornipuolustus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tornipuolustus.ruudut.*;
import tornipuolustus.toimijat.Hirvio;

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

    public List<Ruutu> getRuudukko() {
        List<Ruutu> ruudukko = new ArrayList<>();
        ruudukko.addAll(kuljettavat);
        ruudukko.addAll(rakennettavat);
        return ruudukko;
    }

    public List<Kuljettava> getKuljettavat() {
        return kuljettavat;
    }

    public boolean rakennaTorni(int x, int y) {
        boolean onnistuiko = false;
        for (Rakennettava ruutu : rakennettavat) {
            if (ruutu.getSijainti().equals(new Sijainti(x, y))) {
                onnistuiko = ruutu.rakennaTorni();
            }
        }
        return onnistuiko;
    }

    public boolean poistaTorni(int x, int y) {
        boolean onnistuiko = false;
        for (Rakennettava ruutu : rakennettavat) {
            if (ruutu.getSijainti().equals(new Sijainti(x, y))) {
                onnistuiko = ruutu.tuhoaTorni();
            }
        }
        return onnistuiko;
    }

    public void lisaaHirvio(int elama) {
        for (Kuljettava kuljettava : kuljettavat) {
            if (kuljettava.getSijainti().getX() == 0) {
                hirviot.add(new Hirvio(kuljettava.getSijainti(), 100));
            }
        }
    }
    
    public boolean paasikoLapi() {
        for (Hirvio hirvio : hirviot) {
            for (Kuljettava kuljettava : kuljettavat) {
                if (kuljettava.getSijainti().getX() == this.koko-1) {
                    if (hirvio.getSijainti().equals(kuljettava.getSijainti())) {
                        hirvio.setElama(0);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void liikutaHirvioita() {
        List<Hirvio> poistettavat = new ArrayList<>();
        for (Hirvio h : hirviot) {
            if (h.getElama() <= 0) {
                poistettavat.add(h);
            }
            h.liiku(kuljettavat);
        }
        hirviot.removeAll(poistettavat);
    }

    public void tayta() {     //Laajennus? lisätään parametrit ja ladataan jostakin millainen kenttä
        //tällä hetkellä riittäköön jokin default tyyppinen
        //muokkaaminen tulee vaikuttamaan testeihin myös
        for (int i = 0; i < this.koko; i++) {
            if (i == koko/2) {
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
            boolean morko = false;
            for (Hirvio hirvio : hirviot) { //väliaikainen
                if (ruutu.getSijainti().equals(hirvio.getSijainti())) {
                    morko = true;
                }
            }
            if (morko) {
                System.out.print("H");
            } else {
                System.out.print(ruutu.toString());
            }

            if (ruutu.getSijainti().getX() == this.koko - 1) {
                System.out.println("");
            }
        }
    }
}
