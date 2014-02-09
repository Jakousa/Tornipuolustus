/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ruudut;

import org.junit.Test;
import static org.junit.Assert.*;

public class KuljettavaTest {
    Kuljettava kuljettava = new Kuljettava(0, 8);

    @Test
    public void kuljettavaPiirtyyOikein() {
        String piirros = " ";
        assertEquals(kuljettava.toString(), piirros);
    }

}
