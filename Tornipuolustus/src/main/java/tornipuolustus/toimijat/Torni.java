/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.toimijat;

import java.util.List;
import tornipuolustus.ruudut.Rakennettava;


public class Torni {

    private Hirvio kohde;
    private Rakennettava sijainti;
    
    public Torni(Rakennettava rakennettava) {
        this.sijainti = rakennettava;
    }
    
    public void etsiKohde(List<Hirvio> hirvoista) {
        Hirvio lahin = null;
        for (Hirvio hirvio : hirvoista) {
            if (lahin == null) {
                lahin = hirvio;
            } else if (lahin.getSijainti().etaisyys(sijainti.getSijainti()) >= hirvio.getSijainti().etaisyys(sijainti.getSijainti())) {
                lahin = hirvio;
            }
        }
        kohde = lahin;
    }
}
