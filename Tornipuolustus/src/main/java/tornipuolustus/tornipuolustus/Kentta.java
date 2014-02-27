package tornipuolustus.tornipuolustus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tornipuolustus.ruudut.*;
import tornipuolustus.toimijat.Hirvio;

/**
 * Luokka tallentaa itseensä hirviöt ja erityyppiset ruudut joita
 * pelissä käytetään
 */

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
    
    public int getKoko() {
        return koko;
    }

    /**
     * Lisää hetkelliseen ruudukkoon kaikki ruudut
     *
     * @return lista, jossa kaikki pelin ruudut
     */
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

    /**
     * Rakentaa tornin mikäli mahdollista
     *
     * @param x haluttu tornin sijainti x
     * @param y haluttu tornin sijainti y
     * @return boolean tosi mikäli tornin rakentaminen onnistui
     */
    public boolean rakennaTorni(int x, int y) {
        boolean onnistuiko = false;
        for (Rakennettava ruutu : rakennettavat) {
            if (ruutu.getSijainti().equals(new Sijainti(x, y))) {
                onnistuiko = ruutu.rakennaTorni();
            }
        }
        return onnistuiko;
    }

    /**
     * Poistaa tornin mikäli mahdollista
     *
     * @param x haluttu poistotapahtuman sijainti x
     * @param y haluttu poistotapahtuman sijainti y
     * @return boolean tosi mikäli tornin poistaminen onnistui
     */
    public boolean poistaTorni(int x, int y) {
        boolean onnistuiko = false;
        for (Rakennettava ruutu : rakennettavat) {
            if (ruutu.getSijainti().equals(new Sijainti(x, y))) {
                onnistuiko = ruutu.tuhoaTorni();
            }
        }
        return onnistuiko;
    }

    /**
     * Lisää hirviön ruudukon kaikkein vasemmanpuoleisimpaan ruutuun.
     *
     * @param elama Hirviön elämät aluksi
     */
    public void lisaaHirvio(int elama) {
        for (Kuljettava kuljettava : kuljettavat) {
            if (kuljettava.getSijainti().getX() == 0) {
                hirviot.add(new Hirvio(kuljettava.getSijainti(), elama));
            }
        }
    }

    /**
     * Tarkistaa kaikkien hirviöiden sijainnin, ja mikäli sen sijainti on sama
     * kuin kaikkein oikeanpuoleisin ruutu(-1) poistaa hirviön.
     *
     * @return palauttaa tosi jos hirviö pääsee läpi
     */
    public boolean paasikoLapi() {
        for (Hirvio hirvio : hirviot) {
            for (Kuljettava kuljettava : kuljettavat) {
                if (kuljettava.getSijainti().getX() == this.koko - 1) {
                    if (hirvio.getSijainti().equals(kuljettava.getSijainti())) {
                        hirvio.setElama(0);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Jokaista tornia pyydetään valitsemaan uusi kohde ja ampumaan sitä.
     */
    public int tornitAmpuvat() {
        int osumia = 0;
        for (Rakennettava rakennettava : rakennettavat) {
            if (rakennettava.getTorni() != null) {
                rakennettava.getTorni().etsiKohde(hirviot, rakennettava.getSijainti());
                if (rakennettava.getTorni().ammu()) {
                    osumia++;
                }
            }
        }
        return osumia;
    }

    /**
     * Käy jokaisen hirviön läpi; liikutta jokaista hirviötä poistaa jokaisen
     * jolla on elämät 0 ja palauttaa tapettujen määrän
     */
    public void liikutaHirvioita() {
        List<Hirvio> poistettavat = new ArrayList<>();
        for (Hirvio h : hirviot) {
            if (h.getElama() <= 0) {
                poistettavat.add(h);
            } else {
                h.liiku(kuljettavat);
            }
        }
        hirviot.removeAll(poistettavat);
    }

    /**
     * Kentän täyttö. Koon täytyy vastata annettua kokoa. 
     */
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
            rakennettavat.add(new Rakennettava(i, 0));
        }
        for (int i = 0; i < koko; i++) {
            if (i < koko / 2) {
                kuljettavat.add(new Kuljettava(i, 1));
            } else {
                rakennettavat.add(new Rakennettava(i, 1));
            }
        }
        for (int i = 0; i < koko; i++) {
            for (int j = 2; j < 7; j++) {
                if (i == koko / 2 - 1) {
                    kuljettavat.add(new Kuljettava(i, j));
                } else {
                    rakennettavat.add(new Rakennettava(i, j));
                }

            }
        }
        for (int i = 0; i < koko; i++) {
            if (i > koko / 2 - 2) {
                kuljettavat.add(new Kuljettava(i, 7));
            } else {
                rakennettavat.add(new Rakennettava(i, 7));
            }

        }
        for (int i = 8; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                rakennettavat.add(new Rakennettava(j, i));

            }
        }
    }
}
