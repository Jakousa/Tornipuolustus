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
    
    
    public List<Hirvio> getHirviot() {
        return hirviot;
    }

    
    public List<Kuljettava> getKuljettavat() {
        return kuljettavat;
    }
    
    public List<Rakennettava> getRakennettavat() {
        return rakennettavat;
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
                hirviot.add(new Hirvio(kuljettava.getSijainti(), elama));
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
    
    public void tornitAmpuvat() {
        for (Rakennettava rakennettava : rakennettavat) {
            if (rakennettava.getTorni() != null) {
                rakennettava.getTorni().etsiKohde(hirviot, rakennettava.getSijainti());
                rakennettava.getTorni().ammu();
            }
        }
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

    public void tayta() {
//        for (int i = 0; i < this.koko; i++) {
//            if (i == koko/2) {
//                for (int j = 0; j < this.koko; j++) {
//                    kuljettavat.add(new Kuljettava(j, i));
//                }
//            } else {
//                for (int j = 0; j < this.koko; j++) {
//                    rakennettavat.add(new Rakennettava(j, i));
//                }
//            }
//        }
        
        for (int i = 0; i < koko; i++) {
            rakennettavat.add(new Rakennettava(i,0));
        }
        for (int i = 0; i < koko; i++) {
            if (i < koko/2) {
                kuljettavat.add(new Kuljettava(i, 1));
            } else {
                rakennettavat.add(new Rakennettava(i,1));
            }
        }
        for (int i = 0; i < koko; i++) {
            for (int j = 2; j < 7; j++) {
                if (i == koko/2-1) {
                    kuljettavat.add(new Kuljettava(i, j));
                } else {
                    rakennettavat.add(new Rakennettava(i,j));
                }
                
            }
        }
        for (int i = 0; i < koko; i++) {
            if (i > koko/2 - 2) {
                kuljettavat.add(new Kuljettava(i,7)); 
            } else {
                rakennettavat.add(new Rakennettava(i,7));
            }
            
        }
        for (int i = 8; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                rakennettavat.add(new Rakennettava(j,i));
                
            }
        }
    }

    public void piirra() {
        List<Ruutu> ruudukko = new ArrayList<>();

        ruudukko.addAll(kuljettavat);
        ruudukko.addAll(rakennettavat);
        Collections.sort(ruudukko);
        for (Ruutu ruutu : ruudukko) {
            int morko = 0;
            for (Hirvio hirvio : hirviot) { //väliaikainen
                if (ruutu.getSijainti().equals(hirvio.getSijainti())) {
                    morko = hirvio.getElama();
                }
            }
            if (ruutu.getClass() == kuljettavat.get(0).getClass() && ruutu.getSijainti().getX() == koko-1 ) {
                System.out.print("P");
            } else if (morko != 0) {
                System.out.print(morko);
            } else {
                System.out.print(ruutu.toString());
            }

            if (ruutu.getSijainti().getX() == this.koko - 1) {
                System.out.println("");
            }
        }
    }
}
