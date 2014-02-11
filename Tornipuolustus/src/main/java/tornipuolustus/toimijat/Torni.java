/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.toimijat;

import java.util.List;
import tornipuolustus.tornipuolustus.Sijainti;

public class Torni {

    private Hirvio kohde;
    private int voima;

    public Torni() {
        this.voima = 1;
    }

    /**
     * Vähentää kohteen (Hirviön) elämää.
     * Kentän vastuulla poistaa kuolleet.
     */
    public boolean ammu() {
        if (kohde != null) {
            kohde.setElama(kohde.getElama() - voima);
            return true;
        } else {
            return false;
        }
    }
    
    public int getVoima() {
        return voima;
    }
    
    public Hirvio getKohde() {
        return kohde;
    }

    
    /**
     * Etsii annetuista hirviöistä lähimmän vertaamalla omaa sijaintiaan
     * hirviöiden sijaintiin ja tallettaa kohteeseen lähimmän. 
     * Mikäli mahdollisia kohteita on useita valitsee viimeisimmän.
     * 
     * @param hirvoista Lista ammuttavista hirviöistä
     * @param sijainti Oma sijainti (Rakennettavan sijainti)
     */
    public void etsiKohde(List<Hirvio> hirvoista, Sijainti sijainti) {
        Hirvio lahin = null;
        double etaisyys = 10;
        for (Hirvio hirvio : hirvoista) {
            if (lahin == null) {
                lahin = hirvio;
                etaisyys = lahin.getSijainti().etaisyys(sijainti);
            } else if (lahin.getSijainti().etaisyys(sijainti) >= hirvio.getSijainti().etaisyys(sijainti)) {
                lahin = hirvio;
                etaisyys = lahin.getSijainti().etaisyys(sijainti);
            }
        }
        if (etaisyys < 4 || lahin == null) {
            kohde = lahin;
        }
    }
}
