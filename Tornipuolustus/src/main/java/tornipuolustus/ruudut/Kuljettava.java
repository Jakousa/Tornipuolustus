/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tornipuolustus.ruudut;


public class Kuljettava extends Ruutu {

    public Kuljettava(int x, int y) {
        super(x,y);
    }
    
    @Override
    public void piirra() {
        System.out.print(">");
    }

}
