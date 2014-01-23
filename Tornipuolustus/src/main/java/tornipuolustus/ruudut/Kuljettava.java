/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tornipuolustus.ruudut;

public class Kuljettava extends Ruutu {

    public Kuljettava(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return ">";
    }

    @Override
    public int compareTo(Ruutu r) {
        return super.compareTo(r);
    }
}
