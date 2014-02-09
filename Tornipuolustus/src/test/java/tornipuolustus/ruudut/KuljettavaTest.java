/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ruudut;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tornipuolustus.toimijat.Hirvio;
import tornipuolustus.toimijat.Torni;
import tornipuolustus.tornipuolustus.Kentta;
import tornipuolustus.tornipuolustus.Sijainti;

/**
 *
 * @author Hatchy
 */
public class KuljettavaTest {
    Kuljettava kuljettava = new Kuljettava(0, 8);

    @Test
    public void kuljettavaPiirtyyOikein() {
        String piirros = " ";
        assertEquals(kuljettava.toString(), piirros);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
